/**
 * <p>title:TaskCallBackDownload.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月4日下午3:25:59
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.ftpdownload;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import csuduc.platform.util.StringUtil;
import csuduc.platform.util.generID.UUIDGener;
import datamotion.common.AbsTaskThread;
import datamotion.common.MdlFileEvent;
import datamotion.common.MdlFileEvent.NAMETOKE;
import datamotion.constant.ConstantInitMy;
import datamotion.mvc.t6_dwnloadfile.T6_dwnloadfile;
import datamotion.mvc.t6_dwnloadfile.T6_dwnloadfileController;

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
	
	private static Logger log = Logger.getLogger(TaskCallBackDownload.class);
	FtpUtils_QM ftpUtils = new FtpUtils_QM();
	private String ftpHost = "192.168.199.204";
	private int ftpPort = 21;
	private String ftpNameString = "anonymous";
	private String ftpPasswordString = "anonymous";
	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param amdl
	 * @return
	 * @see datamotion.common.InfTaskThread#addWork(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean addWork(MdlFileEvent amdl) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param amdl
	 * @return
	 * @see datamotion.common.InfTaskThread#doWork(datamotion.common.MdlFileEvent)
	 */
	@Override
	public boolean doWork(MdlFileEvent amdl) {
		// TODO Auto-generated method stub
		try {
			ftpUtils.connectFtpByPool(ftpHost, ftpPort, ftpNameString, ftpPasswordString);
			ftpUtils.downloadFile(amdl.pathsrc, amdl.namesrc, amdl.pathdest, amdl.namesrc);
			ftpUtils.disconnectFtpByPool();
			System.out.println(amdl.namesrc + "下载成功");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(amdl.namesrc + "下载失败");
			return false;
		}
		
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
		//修改afile.status_的状态字段， 1表示已下载
		afile.status_ = 1;
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
		//修改afile.status_的状态字段， 2表示下载失败
		afile.status_ = 2;
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
		try {
			T6_dwnloadfile mdl = new T6_dwnloadfile();
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
			
//			操作数据库方式一：
//			new T6_dwnloadfile()
//			.set("key_", UUIDGener.getUUIDShort())
//			.set("pathsrc", afile.pathsrc)
//			.set("namesrc", afile.namesrc)
//			.set("pathdest", afile.pathdest)
//			.set("timedo", afile.timedo)
//			.set("filesize", afile.filesize)
//			.set("station", afile.station)
//			.set("aircraft", afile.aircraft)
//			.set("sensor", afile.sensor)
//			.set("datatype", afile.datatype)
//			.set("datalevel", afile.datalevel)
//			.set("camera", afile.camera)
//			.set("timerecive", afile.timerecive)
//			.set("timecollectstart", afile.timecollectstart)
//			.set("timecollectend", afile.timecollectend)
//			.set("suffix", afile.suffix)
//			.set("status_", afile.status_)
//			.set("timeadd", System.currentTimeMillis())
//			.saveGenIntId();

//			操作数据库方式二：
//			Record t6_dwnloadfile=new Record()
//			.set("key_", UUIDGener.getUUIDShort())
//			.set("pathsrc", afile.pathsrc)
//			.set("namesrc", afile.namesrc)
//			.set("pathdest", afile.pathdest)
//			.set("timedo", afile.timedo)
//			.set("filesize", afile.filesize)
//			.set("station", afile.station)
//			.set("aircraft", afile.aircraft)
//			.set("sensor", afile.sensor)
//			.set("datatype", afile.datatype)
//			.set("datalevel", afile.datalevel)
//			.set("camera", afile.camera)
//			.set("timerecive", afile.timerecive)
//			.set("timecollectstart", afile.timecollectstart)
//			.set("timecollectend", afile.timecollectend)
//			.set("suffix", afile.suffix)
//			.set("status_", afile.status_)
//			.set("timeadd", new Timestamp(System.currentTimeMillis()));
//			Db.use(ConstantInitMy.db_dataSource_main)
//			.save("t6_dwnloadfile", t6_dwnloadfile);

			
//			String format = "yyyyMMddHHmmss";
//			String sql = "insert into tg2datastore.t6_dwnloadfile values('"+UUIDGener.getUUIDShort()+"','"
//					+afile.pathsrc+"','"+afile.namesrc+"','"+afile.pathdest+"','"+afile.timedo+"',"
//							+afile.filesize+",'"+afile.station+"','"+afile.aircraft+"','"+afile.sensor+"','"
//					+afile.datatype+"','"+afile.datalevel+"','"+afile.camera+"','"+afile.timerecive+"','"
//							+afile.timecollectstart+"','"+afile.timecollectend+"','"+afile.suffix+"',"
//					+afile.status_+",'"+afile.timerecive+"') ";

			System.out.println("数据库插入成功");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("数据库插入失败");
			return false;
		}
		
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
		try {
			//更新数据库信息
			String sql = "update t6_dwnloadfile set status_ = " + afile.status_ + " where id = " + afile.id;
			Db.use(ConstantInitMy.db_dataSource_main).update(sql);
			System.out.println("数据库更新成功");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("数据库更新失败");
			return false;
		}
		
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
		MdlFileEvent mdlFileEvent = new MdlFileEvent();
		mdlFileEvent.namesrc = "TS_TG02_QKDS_PRD1_ENG_20161027170746_20161027170746_20161028165342_000_0C.csv";
		mdlFileEvent.pathsrc = "//testData";
		mdlFileEvent.pathdest = "D:\\test";
		mdlFileEvent.timedo = new Timestamp(System.currentTimeMillis());
		mdlFileEvent.filesize = (long) 3333;
		mdlFileEvent.status_ = 0;//未下载

		mdlFileEvent.initProperties();
		TaskCallBackDownload taskCallBackDownload = new TaskCallBackDownload();
//		taskCallBackDownload.dbAddFileInfo(mdlFileEvent);
		taskCallBackDownload.doWork(mdlFileEvent);
	}


}
