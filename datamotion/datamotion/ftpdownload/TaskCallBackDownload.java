/**
 * <p>title:TaskCallBackDownload.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月4日下午3:25:59
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.ftpdownload;

import java.io.File;

import datamotion.common.AbsTaskThread;
import datamotion.common.MdlFileEvent;

/**  
 * 创建时间：2016年11月4日 下午3:25:59  
 * 项目名称：AutDataMotion   
 * 文件名称：TaskCallBackDownload.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月4日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: TaskCallBackDownload<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年11月4日
 */
public class TaskCallBackDownload extends AbsTaskThread<MdlFileEvent>{

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param amdl
	 * @return
	 * @see datamotion.common.InfTaskThread#addWork(java.lang.Object)
	 */
	@Override
	public <F> boolean addWork(F amdl) {
		// TODO Auto-generated method stub
		//往队列里添加一个任务
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param amdl
	 * @return
	 * @see datamotion.common.InfTaskThread#doWork(java.lang.Object)
	 */
	@Override
	public <F> boolean doWork(F amdl) {
		// TODO Auto-generated method stub
		//添加下载的代码
		Class<? extends Object> file = amdl.getClass();
		System.out.println(file.getName().toString());
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param afile
	 * @return
	 * @see datamotion.common.AbsTaskThread#isCorrectFile(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean isCorrectFile(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		//直接返回true
		
		return true;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param afile
	 * @return
	 * @see datamotion.common.AbsTaskThread#doWorkSucAfter(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean doWorkSucAfter(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param afile
	 * @return
	 * @see datamotion.common.AbsTaskThread#doWorkFailAfter(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean doWorkFailAfter(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param afile
	 * @return
	 * @see datamotion.common.AbsTaskThread#dbAddFileInfo(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean dbAddFileInfo(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param afile
	 * @return
	 * @see datamotion.common.AbsTaskThread#dbUpdateFileInfo(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean dbUpdateFileInfo(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param afile
	 * @return
	 * @see datamotion.common.AbsTaskThread#notifyOthers(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean notifyOthers(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		//稍后在做
		return false;
	}


	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see datamotion.common.AbsTaskThread#clearData()
	 */
	@Override
	protected boolean clearData() {
		// TODO Auto-generated method stub
		//不用管
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param amdlWork
	 * @return
	 * @see datamotion.common.AbsTaskThread#reDoFailedWorks(datamotion.common.MdlFileEvent)
	 */
	@Override
	protected boolean reDoFailedWorks(MdlFileEvent amdlWork) {
		// TODO Auto-generated method stub
		return false;
	}

	public static <F> void main(String[] args) {
		File file = new File("D:\\test\\TS_TG02_QKDS_PRD1_ENG_20161027170746_20161027170746_20161028165342_0C.csv");
		TaskCallBackDownload taskCallBackDownload = new TaskCallBackDownload();
		taskCallBackDownload.doWork(file);// doWork(file);
		
	}
}
