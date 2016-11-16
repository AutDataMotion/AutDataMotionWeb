package datamotion.mvc.t6_dwnloadfile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import oracle.net.aso.s;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import csuduc.platform.util.JsonUtils;
import csuduc.platform.util.generID.UUIDGener;
import datamotion.backup.TaskCallBackBackup;
import datamotion.common.MdlFileEvent;
import datamotion.constant.ConstantInitMy;
import datamotion.ftpdownload.TaskCallBackDownload;
import datamotion.mvc.mdlcomm.MdlClientCheckout;
import datamotion.mvc.mdlcomm.MdlClientDownLoad;
import datamotion.mvc.mdlcomm.MdlControlPanel;
import datamotion.mvc.t7_backupfile.T7_backupfile;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/datamotion/t6_dwnloadfile
 * /jf/datamotion/t6_dwnloadfile/save
 * /jf/datamotion/t6_dwnloadfile/edit
 * /jf/datamotion/t6_dwnloadfile/update
 * /jf/datamotion/t6_dwnloadfile/view
 * /jf/datamotion/t6_dwnloadfile/delete
 * /datamotion/t6_dwnloadfile/add.html
 * 
 */
//@Controller(controllerKey = "/jf/datamotion/t6_dwnloadfile")
public class T6_dwnloadfileController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6_dwnloadfileController.class);

	public static final String pthc = "/jf/datamotion/t6_dwnloadfile/";
	public static final String pthv = "/datamotion/t6_dwnloadfile/";
	public static final String pthvf = "/datamotion/f/";
	
	//测试后台代码TaskCallBackDownload/add
	@Clear
	public void testAdd(){
		MdlFileEvent mdlFileEvent = new MdlFileEvent();
		mdlFileEvent.namesrc = "TS_TG02_QKDS_PRD1_ENG_20161027170746_20161027170746_20161028165342_000_0C.csv";
		mdlFileEvent.pathsrc = "//testData";
		mdlFileEvent.pathdest = "D:\\test";
		mdlFileEvent.timedo = new Timestamp(System.currentTimeMillis());
		mdlFileEvent.filesize = (long) 3333;
		mdlFileEvent.status_ = 0;//未下载

		mdlFileEvent.initProperties();
		TaskCallBackDownload taskCallBackDownload = new TaskCallBackDownload();
		taskCallBackDownload.dbAddFileInfo(mdlFileEvent);
		renderWithPath(pthc+"dwnloadfile.html");
	}
	
	//测试后台代码TaskCallBackDownload/update
	@Clear
	public void testUpdate(){
		MdlFileEvent mdlFileEvent = new MdlFileEvent();
//		mdlFileEvent.namesrc = "TS_TG02_QKDS_PRD1_ENG_20161027170746_20161027170746_20161028165342_000_0C.csv";
//		mdlFileEvent.pathsrc = "//testData";
//		mdlFileEvent.pathdest = "D:\\test";
//		mdlFileEvent.timedo = new Timestamp(System.currentTimeMillis());
//		mdlFileEvent.filesize = (long) 3333;
		mdlFileEvent.status_ = 1;//未下载
		mdlFileEvent.id = 1;
//		mdlFileEvent.initProperties();
		TaskCallBackDownload taskCallBackDownload = new TaskCallBackDownload();
		taskCallBackDownload.dbUpdateFileInfo(mdlFileEvent);
		renderWithPath(pthc+"dwnloadfile.html");
	}	
	
	@Clear
	public String getPagedata(List<T6_dwnloadfile> list)
	{
		JSONObject jsonObject = new JSONObject();
		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		for(int i=0;i<list.size();i++)
		{
			ArrayList<String> subdata = new ArrayList<String>();
			subdata.add(list.get(i).get("id").toString());
			subdata.add(list.get(i).get("pathsrc").toString());
			subdata.add(list.get(i).get("namesrc").toString());
			subdata.add(list.get(i).get("pathdest").toString());
			subdata.add(list.get(i).get("timedo").toString());
			subdata.add(list.get(i).get("filesize").toString());
			subdata.add(list.get(i).get("station").toString());
			subdata.add(list.get(i).get("aircraft").toString());
			subdata.add(list.get(i).get("sensor").toString());
			subdata.add(list.get(i).get("datatype").toString());
			subdata.add(list.get(i).get("datalevel").toString());
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add(list.get(i).get("status_").toString());
			subdata.add("");
			subdata.add("buttons");
			//subdata.add(sublist.get(0).get("labelids").toString());
			data.add(subdata);
		}
		jsonObject.put("data", data);
		
		return jsonObject.toString();
	}
	
	@Clear
	public void getDataFromDatabase()
	{
		// 获得参数
//		String draw=getPara("draw");
//		String start=getPara("start");
//		String length=getPara("length");
		
		List<T6_dwnloadfile> list = T6_dwnloadfile.dao.find(
				"select * from T6_dwnloadfile order by id desc");
		String Jsondata = getPagedata(list);
		renderJson(Jsondata);
	}	
	/*
	 * 重新下载
	 */
	@Clear
	public void reDownLoad()
	{
		// 获得参数
//		String id=getPara("id");
		String pathsrc=getPara("pathsrc");
		String namesrc=getPara("namesrc");
//		List<T6_dwnloadfile> list = T6_dwnloadfile.dao.find(
//				"select * from T6_dwnloadfile where id = " + id);
//		System.out.println(list.toString());
		//初始化参数
		MdlFileEvent amdl = new MdlFileEvent(pathsrc,namesrc);
		amdl.initProperties();
		//下载文件
		TaskCallBackDownload taskCallBackDownload = new TaskCallBackDownload();
		taskCallBackDownload.doWork(amdl);
//		String Jsondata = getPagedata(list);
//		renderJson(Jsondata);
	}	
	/**
	 * 列表
	 */
	@Clear
	public void index() {
		/*paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T6_dwnloadfile.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");*/
		
		String sql = "select * from t6_dwnloadfile order by id desc limit ?";
		List<T6_dwnloadfile> list = T6_dwnloadfile.dao.find(sql, 100);//取数据库的前100条记录
		
		setAttr("list", list);
		renderWithPath(pthv+"dwnloadfile.html");
	}
	
	// 查询
	@Clear
	public void search() {
		System.out.println("running here!");
		// 获得参数
		String strvalue = getPara("v");
		if (null == strvalue || strvalue.isEmpty()) {
			renderText("-1");//错误
		}
		try {
			MdlClientDownLoad mdlClient = JsonUtils.deserialize(strvalue, MdlClientDownLoad.class);
			if (null == mdlClient) {
renderText("-1");//错误
				return;
			}
			
			// 遍历树结构，拼接SQL语句
			String strSQL = mdlClient.getSQLStr("t6_dwnloadfile");

			// 数据库查询
			List<T6_dwnloadfile> res = T6_dwnloadfile.dao.find(strSQL);//取数据库的前100条记录
			
			// 返回结果
			if (null == res || res.size() <= 0) {
				renderText("-1");
				return;
			}else {
				String Jsondata = getPagedata(res);
				renderJson(Jsondata);
				return ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			renderText("-1");//错误
			return;
		}
	}	

	@Clear
	public void download() {

		return;
	}

	// 全部下载本地
	@Clear
	public void downloadAll() {

		// renderJson(null);
	}
	
	@Clear
	public void add(){
		String pathsrc="D://MWI//";
		String namesrc="TT_TT02_MWI_VNI_IMG_20161031000000_20161021000000_20161030000000_000_0C.csv";
		String[] namesStrings = namesrc.split("_");
		String pathdest="E://MWI//";
//		String timedo=getPara("datatype");
		int filesize= 3333;
		String station=namesStrings[0];
		String aircraft=namesStrings[1];
		String sensor=namesStrings[2];
		String datatype=namesStrings[3];
		String datalevel=namesStrings[9];
		String camera=namesStrings[4];
		Timestamp timerecive=new Timestamp(System.currentTimeMillis());
//		String timecollectstart=getPara("patharchive");
//		String timecollectend=getPara("ischeckout");
		String suffix=".csv";
		int status_=1;
//		String timeadd=getPara("namemdldes");
		Record t6_dwnloadfileRecord=new Record()
		.set("key_", UUIDGener.getUUIDShort())
		.set("pathsrc", pathsrc)
		.set("namesrc", namesrc)
		.set("pathdest", pathdest)
		.set("timedo", new Timestamp(System.currentTimeMillis()))
		.set("filesize", filesize)
		.set("station", station)
		.set("aircraft", aircraft)
		.set("sensor", sensor)
		.set("datatype", datatype)
		.set("datalevel", datalevel)
		.set("camera", camera)
		.set("timerecive", timerecive)
		.set("timecollectstart", new Timestamp(System.currentTimeMillis()))
		.set("timecollectend", new Timestamp(System.currentTimeMillis()))
		.set("suffix", suffix)
		.set("status_", status_)
		.set("timeadd", new Timestamp(System.currentTimeMillis()));
		Db.use(ConstantInitMy.db_dataSource_main)
		  .save("t6_dwnloadfile", t6_dwnloadfileRecord);
	}
	
	@Clear
	public void viewControlPanel() {
		renderWithPath(pthvf+"controlpanel.html");
	}

	/**
	 * 保存
	 */
	@Before(T6_dwnloadfileValidator.class)
	public void save() {
		T6_dwnloadfile t6_dwnloadfile = getModel(T6_dwnloadfile.class);
		//other set 
		
		//t6_dwnloadfile.save();		//guiid
		t6_dwnloadfile.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T6_dwnloadfile t6_dwnloadfile = T6_dwnloadfile.dao.findById(getPara());	//guuid
		T6_dwnloadfile t6_dwnloadfile = T6_dwnloadfileService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t6_dwnloadfile", t6_dwnloadfile);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T6_dwnloadfileValidator.class)
	public void update() {
		getModel(T6_dwnloadfile.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T6_dwnloadfile t6_dwnloadfile = T6_dwnloadfile.dao.findById(getPara());	//guuid
		T6_dwnloadfile t6_dwnloadfile = T6_dwnloadfileService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t6_dwnloadfile", t6_dwnloadfile);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T6_dwnloadfileService.service.delete("t6_dwnloadfile", getPara() == null ? ids : getPara());	//guuid
		T6_dwnloadfileService.service.deleteById("t6_dwnloadfile", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
	//------------------start 控制面板---------------------//

	//初始化控制面板数据
	@Clear
	public void initControlPanel(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		MdlControlPanel mdlControlPanelDownload = new MdlControlPanel();
		mdlControlPanelDownload.setUndoNum_download(10);
		mdlControlPanelDownload.setDosucNum_download(1000);
		mdlControlPanelDownload.setDofailNum_download(100);
		mdlControlPanelDownload.setTimeStart_download(df.format(new Date()));
		mdlControlPanelDownload.setStatus_download(0);
		
		mdlControlPanelDownload.setUndoNum_backup(10);
		mdlControlPanelDownload.setDosucNum_backup(1000);
		mdlControlPanelDownload.setDofailNum_backup(100);
		mdlControlPanelDownload.setTimeStart_backup(df.format(new Date()));
		mdlControlPanelDownload.setStatus_backup(0);
		
		mdlControlPanelDownload.setUndoNum_archive(10);
		mdlControlPanelDownload.setDosucNum_archive(1000);
		mdlControlPanelDownload.setDofailNum_archive(100);
		mdlControlPanelDownload.setTimeStart_archive(df.format(new Date()));
		mdlControlPanelDownload.setStatus_archive(0);
		
		mdlControlPanelDownload.setUndoNum_checkout(10);
		mdlControlPanelDownload.setDosucNum_checkout(1000);
		mdlControlPanelDownload.setDofailNum_checkout(100);
		mdlControlPanelDownload.setTimeStart_checkout(df.format(new Date()));
		mdlControlPanelDownload.setStatus_checkout(0);
		
		String jsonString = JsonUtils.serialize(mdlControlPanelDownload);
		if (null == jsonString) {
			renderText("-1");//错误
			return;
			}
		renderJson(jsonString);
		System.out.println("returnJson=="+jsonString);
		return ;
	}
	//------------------start 下载---------------------//
	//启动下载线程
	@Clear
	public void start_Download() {
		TaskCallBackDownload download = new TaskCallBackDownload();
		download.start();
	}
	//重启下载线程
	@Clear
	public void restart_Download() {
		TaskCallBackDownload download = new TaskCallBackDownload();
		download.restart();
	}
	//停止下载线程
	@Clear
	public void stop_Download() {
		TaskCallBackDownload download = new TaskCallBackDownload();
		download.stop();
	}
	//------------------end 下载---------------------//
	
	//------------------start 备份---------------------//
	//启动 备份线程
	@Clear
	public void start_BackUp() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.start();
	}
	//重启 备份线程
	@Clear
	public void restart_BackUp() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.restart();
	}
	//停止 备份线程
	@Clear
	public void stop_BackUp() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.stop();
	}
	//------------------end 备份---------------------//
	
	//------------------start 归档---------------------//
	//启动归档线程
	@Clear
	public void start_ArchiveFile() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.start();
	}
	//重启归档线程
	@Clear
	public void restart_ArchiveFile() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.restart();
	}
	//停止归档线程
	@Clear
	public void stop_ArchiveFile() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.stop();
	}
	//------------------end 归档---------------------//
	
	//------------------start 检出---------------------//
	//启动检出线程
	@Clear
	public void start_CheckOutFile() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.start();
	}
	//重启检出线程
	@Clear
	public void restart_CheckOutFile() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.restart();
	}
	//停止检出线程
	@Clear
	public void stop_CheckOutFile() {
		TaskCallBackBackup backBackup = new TaskCallBackBackup();
		backBackup.stop();
	}
	//------------------end 检出---------------------//
	//------------------end 控制面板---------------------//
}
