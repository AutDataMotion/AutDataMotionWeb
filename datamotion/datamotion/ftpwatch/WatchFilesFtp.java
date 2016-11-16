/**
 * <p>title:WatchFilesFtp.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月2日下午9:15:21
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.ftpwatch;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import csuduc.platform.util.ComUtil;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.networkCom.FTPClientPool;
import datamotion.common.MdlFileEvent;
import datamotion.common.MdlTreeProperty;
import datamotion.common.MdlFileEvent.NAMETOKE;
import datamotion.config.ConfMain;
import datamotion.config.RunMain;
import datamotion.constant.ConstantInitMy;
import datamotion.mvc.t11_initfoldertree.T11_initfoldertree;
import datamotion.mvc.t12_initmodule.T12_initmodule;

/**  
 * 创建时间：2016年11月2日 下午9:15:21  
 * 项目名称：AutDataMotion   
 * 文件名称：WatchFilesFtp.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月2日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: WatchFilesFtp<／p>
 * <p>
 * Description: 支持定时扫描FTP文件和手工扫描FTP文件 <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年11月2日
 */
public class WatchFilesFtp {
	private static Logger log = Logger.getLogger(WatchFilesFtp.class);
	// 未分发的文件map filename 为 key
	volatile protected ConcurrentHashMap<String, MdlFileEvent> mapFilesFound = new ConcurrentHashMap<String, MdlFileEvent>();
	// 已分发的文件map filename 为 key
	volatile protected ConcurrentHashMap<String, MdlFileEvent> mapfilesDistributed = new ConcurrentHashMap<String, MdlFileEvent>();

	//生成的树形结构
	public static MdlTreeProperty treeRoot = ConfMain.treeRoot;
	//目录树配置map
	public static Map<String, MdlTreeProperty> mapFilterProperty = ConfMain.mapFilterProperty;
	
	//ftp Pool
	private FTPClientPool ftpPool = ConfMain.getInstance().ftpPool;
	

	public void watchFTP(String aPathFTP){
		FTPClient ftpClient = null;
		try {
			ftpClient = ftpPool.borrowObject();
			
			//以互易Path开始扫描
			for (Map.Entry<String, Map<String, MdlTreeProperty>> iPathNodes : ConfMain.mapPathProperty.entrySet()) {
				String pathFtp = iPathNodes.getKey();
				log.debug(pathFtp);
				Map<String, MdlTreeProperty> mapNodes = iPathNodes.getValue();
				
				//在pathFtp目录下 递归扫描所有文件/文件夹
				//保留需要处理的文件
				//跳转到固定目录
				ftpClient.changeWorkingDirectory(pathFtp);
				FTPFile[] remoteFiles = ftpClient.listFiles();
				if (null == remoteFiles) {
					log.error("null == remoteFiles");
					return ;
				}
				for (FTPFile ftpFile : remoteFiles) {
					String fileName = ftpFile.getName();
					log.debug(String.format("---ftpFile or director:%s", fileName));
					if(ftpFile.isFile()){
						List<String> nameTokens = StringUtil.split(fileName, MdlFileEvent.split);
						String filterName = MdlFileEvent.getFileNameFilterStr(nameTokens);
						if (null == filterName) {
							log.error(String.format("file:%s not correct!", fileName));
							continue;
						}
						//判断是否需要处理的文件
						MdlTreeProperty filterMdlTreeProperty = mapNodes.get(filterName);
						if (null ==  filterMdlTreeProperty) {
							log.error(String.format("file:%s has been filted!", fileName));
							continue;
						}
	                    //需要处理的文件--是否已经有记录
						MdlFileEvent mdlFileEvent = mapFilesFound.get(fileName);
						if(null == mdlFileEvent){
							//未分发中没有 则在已发送中查找
							mdlFileEvent = mapfilesDistributed.get(fileName);
							if (null == mdlFileEvent) {
								//已分发中也没有 则是新发现的文件
								log.debug("new ftpFile:"+fileName);
								mdlFileEvent = new MdlFileEvent(pathFtp
										, fileName
										, filterMdlTreeProperty.property.getPathdwnload().toString()
										, fileName);
								if (!mdlFileEvent.initProperties(nameTokens, filterMdlTreeProperty)) {
									//文件名结构初始化失败 则不能再继续处理
									log.error(String.format("error:file:%s MdlFileEvent initProperties failed!", fileName));
									mdlFileEvent = null;
									continue;
								}
								//设置
								mdlFileEvent.flagWatch = 0;
								mdlFileEvent.filesize = ftpFile.getSize();
								mapFilesFound.put(fileName, mdlFileEvent);
								continue;
								
							}else {
								//已经分发的文件 则忽略，这里不再处理
								log.debug(String.format("ftpFile:%s has been distributed!",	 fileName));
								continue;
							}
						}
						//通过文件大小检查文件是否完整，可以进行处理
						long fileSizeNew = ftpFile.getSize();
						if (mdlFileEvent.filesize < fileSizeNew) {
							//文件可能还在变化 则继续等待到文件不变
							log.debug("file is changing...");
							mdlFileEvent.filesize = fileSizeNew;
							mdlFileEvent.flagWatch = 0;
							continue;
							
						}else if(mdlFileEvent.filesize == fileSizeNew){
							log.debug("file size is equal");
							//此时认为文件不再变化
							mdlFileEvent.flagWatch = 1;
							
							//通知下载线程可以下载该文件
							if(RunMain.downloadWorker.addWork(mdlFileEvent)){
								mdlFileEvent.flagWatch = 2;
								mapfilesDistributed.put(fileName, mdlFileEvent);
								mapFilesFound.remove(fileName);
							}else {
								//失败 则等待下一次
								continue;
							}

						}
						
	                    
	                }else if(ftpFile.isDirectory()){  
	                	 log.debug(ftpFile.getName()); 
	                }  	
				}
				
			}
			for (Map.Entry<String, MdlTreeProperty> item : mapFilterProperty.entrySet()) {
				MdlTreeProperty property = item.getValue();
				T12_initmodule proModule = property.property;
				if (!((boolean) proModule.getIsdwnload())) {
					log.debug(String.format("%s fileWatch download escape", property.self.getNamechi()));
					continue;
				}
				//跳转到固定目录
				ftpClient.changeWorkingDirectory(proModule.getPathftp().toString());
				
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}finally{
			try {
				ftpPool.returnObject(ftpClient);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	//定时扫描FTP 检查是否有新文件到来 和 文件拷贝完成
	class TimerWatchFTP extends Thread {
		private volatile boolean flagRun = true;

		@Override
		public void run() {
			while (flagRun) {
				try {

					Thread.sleep(ConstantInitMy.timeSpanMinute * 30);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//__RedoFailedWorks();
			}

		}

		public void setStop() {
			flagRun = false;
		}

	}

}
