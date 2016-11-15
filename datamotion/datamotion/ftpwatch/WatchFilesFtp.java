/**
 * <p>title:WatchFilesFtp.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月2日下午9:15:21
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.ftpwatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import csuduc.platform.util.networkCom.FTPClientPool;
import datamotion.common.MdlFileEvent;
import datamotion.common.MdlTreeProperty;
import datamotion.config.ConfMain;
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
	public static Map<String, MdlTreeProperty> mapTreeProperty = ConfMain.mapTreeProperty;
	
	//ftp Pool
	private FTPClientPool ftpPool = ConfMain.getInstance().ftpPool;
	
	public void watchFTP(String aPathFTP){
		FTPClient ftpClient = null;
		try {
			ftpClient = ftpPool.borrowObject();
			for (Map.Entry<String, MdlTreeProperty> item : mapTreeProperty.entrySet()) {
				MdlTreeProperty property = item.getValue();
				T12_initmodule proModule = property.property;
				
			}
			ftpClient.changeWorkingDirectory(aPathFTP);
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (null == remoteFiles) {
				log.error("null == remoteFiles");
				return ;
			}
			for (FTPFile ftpFile : remoteFiles) {
				if(ftpFile.isFile()){  
                    log.debug(ftpFile.getName()); 
                }else if(ftpFile.isDirectory()){  
                	 log.debug(ftpFile.getName()); 
                }  	
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
