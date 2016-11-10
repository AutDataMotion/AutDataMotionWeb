package datamotion.backup;

import datamotion.common.AbsTaskThread;
import datamotion.common.MdlFileEvent;

/*
 * by lyf
 * */
 public class TaskCallBackBackup extends AbsTaskThread<MdlFileEvent>{

	/* (non-Javadoc)
	 * @see datamotion.common.InfTaskThread#addWork(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean addWork(MdlFileEvent amdl) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.InfTaskThread#doWork(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean doWork(MdlFileEvent amdl) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.AbsTaskThread#isCorrectFile(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean isCorrectFile(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.AbsTaskThread#doWorkSucAfter(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean doWorkSucAfter(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.AbsTaskThread#doWorkFailAfter(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean doWorkFailAfter(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.AbsTaskThread#dbAddFileInfo(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean dbAddFileInfo(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.AbsTaskThread#dbUpdateFileInfo(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean dbUpdateFileInfo(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.AbsTaskThread#notifyOthers(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean notifyOthers(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.AbsTaskThread#clearData()
	 */
	@Override
	protected boolean clearData() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see datamotion.common.AbsTaskThread#reDoFailedWorks(datamotion.common.MdlFileEvent)
	 */
	@Override
	protected boolean reDoFailedWorks(MdlFileEvent amdlWork) {
		// TODO Auto-generated method stub
		return false;
	}
}