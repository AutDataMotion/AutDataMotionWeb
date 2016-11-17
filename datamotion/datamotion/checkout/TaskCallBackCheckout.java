/**
 * <p>title:TaskCallBackCheckout.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月16日下午2:30:02
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.checkout;

import datamotion.common.AbsTaskThread;
import datamotion.common.MdlFileEvent;
import datamotion.constant.StatusMy;

/**  
 * 创建时间：2016年11月16日 下午2:30:02  
 * 项目名称：AutDataMotion   
 * 文件名称：TaskCallBackCheckout.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月16日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: TaskCallBackCheckout<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年11月16日
 */
public class TaskCallBackCheckout extends AbsTaskThread<MdlFileEvent>{

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param amdl
	 * @return
	 * @see datamotion.common.InfTaskThread#doWork(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean doWork(MdlFileEvent amdl) {
		// TODO Auto-generated method stub
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
		return false;
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



	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see datamotion.common.AbsTaskThread#getFlowStatus()
	 */
	@Override
	public StatusMy getFlowStatus() {
		// TODO Auto-generated method stub
		
		return StatusMy.FLOW_CHECKOUT;
	}



}
