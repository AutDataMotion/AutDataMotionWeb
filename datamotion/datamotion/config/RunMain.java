/**
 * <p>title:RunMain.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月2日下午9:09:59
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.config;

import org.apache.log4j.Logger;

import com.platform.interf.InfMainConf;

import csuduc.platform.util.JsonUtils;
import datamotion.archive.TaskCallBackArchive;
import datamotion.backup.TaskCallBackBackup;
import datamotion.checkout.TaskCallBackCheckout;
import datamotion.ftpdownload.TaskCallBackDownload;
import datamotion.ftpwatch.WatchFilesFtp;

/**  
 * 创建时间：2016年11月2日 下午9:09:59  
 * 项目名称：AutDataMotion   
 * 文件名称：RunMain.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月2日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: RunMain<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年11月2日
 */
public class RunMain implements InfMainConf{
	private static Logger log = Logger.getLogger(RunMain.class);
	private final static RunMain INSTANCE = new RunMain();
	
	//================FTP Watch
	public static WatchFilesFtp ftpWatch = new WatchFilesFtp();
	//=================下载
	public static TaskCallBackDownload downloadWorker = new TaskCallBackDownload();

	//=================备份
	public static TaskCallBackBackup backupWorker = new TaskCallBackBackup();
	
	//==================归档
	public static TaskCallBackArchive archiveWorker  = new TaskCallBackArchive();
	
	//==================检出
	public static TaskCallBackCheckout checkoutWorker = new TaskCallBackCheckout();
	
	 /**
	 * 
	 */
	private RunMain() {
		// TODO Auto-generated constructor stub
	}
	public static RunMain GetInstance(){
		return INSTANCE;
	}
	
	
	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see com.platform.interf.InfMainConf#init()
	 */
	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		//加载数据库的配置信息
		if (!ConfMain.buildProperties()) {
			log.error("error: RunMain ConfMain.buildProperties ");
			return false;
		}
		//log.debug(ConfMain.treeRoot);
		//
		return true;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see com.platform.interf.InfMainConf#start()
	 */
	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		//启动各自的工作线程
		downloadWorker.start();
		backupWorker.start();
		archiveWorker.start();
		checkoutWorker.start();
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see com.platform.interf.InfMainConf#refresh()
	 */
	@Override
	public boolean refresh() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see com.platform.interf.InfMainConf#stop()
	 */
	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		checkoutWorker.stop();
		archiveWorker.stop();
		backupWorker.stop();
		downloadWorker.stop();
		return false;
	}

}
