package datamotion.backup;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;

import csuduc.platform.util.FileUtils;
import csuduc.platform.util.generID.UUIDGener;
import datamotion.common.AbsTaskThread;
import datamotion.common.MdlFileEvent;
import datamotion.config.RunMain;
import datamotion.constant.ConstantInitMy;
import datamotion.constant.StatusMy;
import datamotion.ftpdownload.FtpUtils_QM;
import datamotion.ftpdownload.TaskCallBackDownload;
import datamotion.mvc.t7_backupfile.T7_backupfile;

/*
 * by lyf
 * */
public class TaskCallBackBackup extends AbsTaskThread<MdlFileEvent> {
	private static Logger log = Logger.getLogger(TaskCallBackBackup.class);
	FtpUtils_QM ftpUtils = new FtpUtils_QM();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * datamotion.common.InfTaskThread#doWork(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean doWork(MdlFileEvent amdl) {
		// TODO Auto-generated method stub
		try {
			if (FileUtils.copyFile(amdl.pathsrc, amdl.namesrc, amdl.pathdest,
					amdl.namesrc, true)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(amdl.namesrc + "备份失败");
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * datamotion.common.AbsTaskThread#doWorkSucAfter(datamotion.common.MdlFileEvent
	 * )
	 */
	@Override
	public boolean doWorkSucAfter(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		afile.status_ = 1;// 备份成功
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see datamotion.common.AbsTaskThread#doWorkFailAfter(datamotion.common.
	 * MdlFileEvent)
	 */
	@Override
	public boolean doWorkFailAfter(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		afile.status_ = 2;// 备份失败
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * datamotion.common.AbsTaskThread#dbAddFileInfo(datamotion.common.MdlFileEvent
	 * )
	 */
	@Override
	public boolean dbAddFileInfo(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		try {
			T7_backupfile mdl = new T7_backupfile();
			mdl.setKey_(UUIDGener.getUUIDShort().toString());
			mdl.setPathsrc(afile.pathsrc);
			mdl.setNamesrc(afile.namesrc);
			mdl.setPathdest(afile.pathdest);
			mdl.setTimedo(afile.timedo);
			mdl.setFilesize(afile.filesize);
			mdl.setStation(afile.station);
			mdl.setAircraft(afile.aircraft);
			mdl.setSensor(afile.sensor);
			mdl.setDatatype(afile.datatype);
			mdl.setDatalevel(afile.datalevel);
			mdl.setCamera(afile.camera);
			mdl.setTimerecive(afile.timerecive);
			mdl.setTimecollectstart(afile.timecollectstart);
			mdl.setTimecollectend(afile.timecollectend);
			mdl.setSuffix(afile.suffix);
			mdl.setStatus_(afile.status_);
			mdl.setTimeadd(new Timestamp(System.currentTimeMillis()));
			mdl.saveGenIntId();
			afile.id = mdl.getId();
			System.out.println("数据库插入成功");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("数据库插入失败");
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see datamotion.common.AbsTaskThread#dbUpdateFileInfo(datamotion.common.
	 * MdlFileEvent)
	 */
	@Override
	public boolean dbUpdateFileInfo(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		try {
			// 更新数据库信息
			String sql = "update t7_backupfile set status_ = " + afile.status_
					+ " where id = " + afile.id;
			Db.use(ConstantInitMy.db_dataSource_main).update(sql);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * datamotion.common.AbsTaskThread#notifyOthers(datamotion.common.MdlFileEvent
	 * )
	 */
	@Override
	public boolean notifyOthers(MdlFileEvent afile) {
		// TODO Auto-generated method stub
		RunMain.archiveWorker.addWork(afile);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see datamotion.common.AbsTaskThread#clearData()
	 */
	@Override
	protected boolean clearData() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see datamotion.common.AbsTaskThread#reDoFailedWorks(datamotion.common.
	 * MdlFileEvent)
	 */
	@Override
	protected boolean reDoFailedWorks(MdlFileEvent amdlWork) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc) <p>Description: <／p>
	 * 
	 * @return
	 * 
	 * @see datamotion.common.AbsTaskThread#getFlowStatus()
	 */
	@Override
	public StatusMy getFlowStatus() {
		// TODO Auto-generated method stub

		return StatusMy.FLOW_BACKUP;
	}

}