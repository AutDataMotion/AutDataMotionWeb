package datamotion.mvc.t7_backupfile;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.JsonUtils;
import datamotion.backup.TaskCallBackBackup;
import datamotion.common.MdlFileEvent;
import datamotion.constant.ConstantInitMy;
import datamotion.ftpdownload.FtpUtils_QM;
import datamotion.mvc.mdlcomm.MdlClientCheckout;
import datamotion.mvc.mdlcomm.MdlClientDownLoad;
import datamotion.mvc.mdlcomm.MdlKeys;
import datamotion.mvc.t9_checkoutfiles.T9_checkoutfiles;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/datamotion/t7_backupfile
 * /jf/datamotion/t7_backupfile/save
 * /jf/datamotion/t7_backupfile/edit
 * /jf/datamotion/t7_backupfile/update
 * /jf/datamotion/t7_backupfile/view
 * /jf/datamotion/t7_backupfile/delete
 * /datamotion/t7_backupfile/add.html
 * 
 */
//@Controller(controllerKey = "/jf/datamotion/t7_backupfile")
public class T7_backupfileController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T7_backupfileController.class);

	public static final String pthc = "/jf/datamotion/t7_backupfile/";
	public static final String pthv = "/datamotion/t7_backupfile/";
	public static final String pthvf = "/datamotion/f/";
	
	public static final String pthv10 = "/datamotion/t10_datastatistics/";
	
	FtpUtils_QM ftpUtils = new FtpUtils_QM();
	
	/**
	 * 列表
	 */
	@Clear
	public void index() {
//		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T7_backupfile.sqlId_splitPage_from);
//		renderWithPath(pthv+"list.html");

		//renderWithPath(pthv+"backupfile.html");
		//renderWithPath(pthv10+"datastatistics.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T7_backupfileValidator.class)
	public void save() {
		T7_backupfile t7_backupfile = getModel(T7_backupfile.class);
		//other set 
		
		//t7_backupfile.save();		//guiid
		
		t7_backupfile.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
		
//		T7_backupfile t7_backupfile1 = new T7_backupfile();
//		t7_backupfile1.setKey_("");
//		t7_backupfile1.saveGenIntId();
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T7_backupfile t7_backupfile = T7_backupfile.dao.findById(getPara());	//guuid
		T7_backupfile t7_backupfile = T7_backupfileService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t7_backupfile", t7_backupfile);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T7_backupfileValidator.class)
	public void update() {
		getModel(T7_backupfile.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T7_backupfile t7_backupfile = T7_backupfile.dao.findById(getPara());	//guuid
		T7_backupfile t7_backupfile = T7_backupfileService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t7_backupfile", t7_backupfile);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T7_backupfileService.service.delete("t7_backupfile", getPara() == null ? ids : getPara());	//guuid
		T7_backupfileService.service.deleteById("t7_backupfile", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	@Clear
	public void getDataFromDatabase()
	{
		
		// 获得参数
//		String draw=getPara("draw");
//		String start=getPara("start");
//		String length=getPara("length");
		
		List<T7_backupfile> list = T7_backupfile.dao.find(
				"select * from t7_backupfile order by id desc limit 200");
		String Jsondata = getPagedata(list);
		renderJson(Jsondata);
	}
	@Clear
	public String getPagedata(List<T7_backupfile> list)
	{
//		int toindex = Integer.parseInt(start)+Integer.parseInt(length);
//		List<T7_backupfile> sublist=null;
//		if(toindex<=list.size())
//		{
//			sublist = list.subList(Integer.parseInt(start), Integer.parseInt(start)+Integer.parseInt(length));
//		}
//		else{
//			sublist = list.subList(Integer.parseInt(start), list.size());
//		}
		
		//int count = list.size();
		JSONObject jsonObject = new JSONObject();
		
//		jsonObject.put("draw", draw);
//		jsonObject.put("recordsTotal", count);
//		jsonObject.put("recordsFiltered", count);
//		
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
			subdata.add(list.get(i).get("camera").toString());
			subdata.add(list.get(i).get("timerecive").toString());
			subdata.add(list.get(i).get("timecollectstart").toString());
			subdata.add(list.get(i).get("timecollectend").toString());
			subdata.add(list.get(i).get("status_").toString());
			subdata.add(list.get(i).get("timeadd").toString());
			subdata.add("buttons");
			//subdata.add(sublist.get(0).get("labelids").toString());
			data.add(subdata);
		}
		jsonObject.put("data", data);
		
		return jsonObject.toString();
	}
	@Clear
	public void backup()
	{
		// 获得参数
//		String jsondata = getPara("aoData");
//				
//		List<T7_backupfile> list = T7_backupfile.dao.find(
//				"select * from t7_backupfile order by id desc limit ?", 100);
//		setAttr("list", list);
		
		//addData2Database();
		
		renderWithPath(pthv+"backupfile.html");
		
	}
	@Clear
	public void datastatistics()
	{
		
		renderWithPath(pthv10+"datastatistics.html");
	}
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	@Clear
	public void addData2Database()//测试后台自动备份
	{
		MdlFileEvent mdlFileEvent = new MdlFileEvent();
		mdlFileEvent.namesrc = "TS_TG02_QKDS_PRD1_ENG_20161027170746_20161027170746_20161028165342_000_0C.csv";
		mdlFileEvent.pathsrc = "//testData";
		mdlFileEvent.pathdest = "D:\\test";
		mdlFileEvent.timedo = new Timestamp(System.currentTimeMillis());
		mdlFileEvent.filesize = (long) 3333;
		mdlFileEvent.status_ = 0;//未备份
		
		mdlFileEvent.initProperties();
		TaskCallBackBackup taskCallBackBackup = new TaskCallBackBackup();
		taskCallBackBackup.dbAddFileInfo(mdlFileEvent);
		
	}
	// 查询
	@Clear
	public void doQuery() {
		
		// 获得参数
				String strvalue = getPara("v");
				if (null == strvalue || strvalue.isEmpty()) {
					renderText("-1");//错误
				}
				log.debug(strvalue);
				MdlClientDownLoad mdlClient = null;
				try {
					mdlClient = JsonUtils.deserialize(strvalue, MdlClientDownLoad.class);
					if (null == mdlClient) {
						renderText("-1");//错误
						return;
					}
					log.debug(JsonUtils.serialize(mdlClient));
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					renderText("-1");//错误
					return;
				}

				// 遍历树结构，拼接SQL语句
				String strSQL = mdlClient.getSQLStr("t7_backupfile");
				
				// 数据库查询
				List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
				log.debug(strSQL);
				// 返回结果
				if (null == res || res.size() <= 0) {
					renderText("-1");
					return;
				}else {
					String Jsondata = getPagedata(res);
					renderJson(Jsondata);
					 return ;
				}
	}
	//全部本地下载
	@Clear
	public void doAllLocalDownload() {
		
		// 获得参数
		String ids = getPara("keys");
		if (null == ids || ids.isEmpty()) {
			renderText("-1");//错误
		}
		log.debug(ids);
		MdlKeys mdlKeys = null;
		try {
			//更新数据库信息
			mdlKeys = JsonUtils.deserialize(ids, MdlKeys.class);
			if (null == mdlKeys) {
				renderText("-1");//错误
				return;
			}
			log.debug(JsonUtils.serialize(mdlKeys));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//return false;
		}
		// 遍历树结构，拼接SQL语句
		String strSQL = mdlKeys.getSQLStr("t7_backupfile");
		
		// 数据库查询
		List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
		log.debug(strSQL);
		// 返回结果
		if (null == res || res.size() <= 0) {
			renderText("-1");
			return;
		}else {
			
//			String Jsondata = getPagedata(res);
//			renderJson(Jsondata);
			
			//执行全部本地下载代码
			for(int i=0;i<res.size();i++)
			{
				res.get(i).get("id").toString();
			}
			renderText("1");
		}
	}
	//全部重新备份
	@Clear
	public void doAllNewBackup() {
		// 获得参数
		String ids = getPara("keys");
		if (null == ids || ids.isEmpty()) {
			renderText("-1");//错误
		}
		log.debug(ids);
		MdlKeys mdlKeys = null;
		try {
			//更新数据库信息
			mdlKeys = JsonUtils.deserialize(ids, MdlKeys.class);
			if (null == mdlKeys) {
				renderText("-1");//错误
				return;
			}
			log.debug(JsonUtils.serialize(mdlKeys));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//return false;
		}
		// 遍历树结构，拼接SQL语句
		String strSQL = mdlKeys.getSQLStr("t7_backupfile");
		
		// 数据库查询
		List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
		log.debug(strSQL);
		// 返回结果
		if (null == res || res.size() <= 0) {
			renderText("-1");
			return;
		}else {
			
//			String Jsondata = getPagedata(res);
//			renderJson(Jsondata);
			
			//执行全部重新备份代码
			for(int i=0;i<res.size();i++)
			{
				res.get(i).get("id").toString();
			}
			renderText("1");
		}
	}
	//选中的单条记录－本地下载
	@Clear
	public void doLocalDownload() {//选中的单条记录－本地下载
		String id = getPara("id");
		if (null == id || id.isEmpty()) {
			renderText("-1");//错误
		}
		log.debug(id);
		try {
			//拼接SQL语句
			String strSQL = "select * from t7_backupfile where id = "+id;
			// 数据库查询
			List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
			// 返回结果
			if (null == res || res.size() <= 0) {
				renderText("-1");
				return;
			}else {
				//执行本地下载
				res.get(0).get("id").toString();
				
				renderText("1");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//return false;
		}
		
	}
	//选中的单条记录－重新备份
	@Clear
	public void doNewBackup() {//选中的单条记录－重新备份
		String id = getPara("id");
		if (null == id || id.isEmpty()) {
			renderText("-1");//错误
		}
		log.debug(id);
		try {
			//拼接SQL语句
			String strSQL = "select * from t7_backupfile where id = "+id;
			// 数据库查询
			List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
			// 返回结果
			if (null == res || res.size() <= 0) {
				renderText("-1");
				return;
			}else {
				//执行重新备份
				String pathsrc=res.get(0).get("pathsrc").toString();//源文件路径
				String namesrc=res.get(0).get("namesrc").toString();//备份文件名称
				String pathdest=res.get(0).get("pathdest").toString();//备份文件路径
				
				//ftpUtils.copyFile("oldPath", "newPath");
				ftpUtils.copyFile(pathsrc+namesrc, pathdest+namesrc);
				
				renderText("1");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//return false;
		}
		
	}
	//选中的单条记录－删除
	@Clear
	public void doDeleteSeleted() {//选中的记录删除（修改status_＝3）
		
		// 获得参数
		String id = getPara("id");
		if (null == id || id.isEmpty()) {
			renderText("-1");//错误
		}
		log.debug(id);
		try {
			//更新数据库信息
//			String sql = "update t7_backupfile set status_ = 3"+ " where id = " +id;
//			Db.use(ConstantInitMy.db_dataSource_main).update(sql);
			//拼接SQL语句
			String strSQL = "select * from t7_backupfile where id = "+id;
			// 数据库查询
			List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
			// 返回结果
			if (null == res || res.size() <= 0) {
				renderText("-1");
				return;
			}else {
				//执行删除文件操作
				String pathsrc=res.get(0).get("pathsrc").toString();//源文件路径
				String namesrc=res.get(0).get("namesrc").toString();//备份文件名称
				String pathdest=res.get(0).get("pathdest").toString();//备份文件路径
				
				//删除文件;
				//删除文件;
				boolean result = deleteFile(pathdest, namesrc);
				if(result){
					//更新数据库信息
					String sql = "update t7_backupfile set status_ = 3"+ " where id = " +id;
					Db.use(ConstantInitMy.db_dataSource_main).update(sql);
					renderText("1");
				}
				else{
					renderText("-1");//失败
				}

				
				renderText("1");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//return false;
		}
	}

	/** 
	  * 删除单个文件 
	  *  
	  * @param fileName 
	  *            要删除的文件的文件名 
	  * @return 单个文件删除成功返回true，否则返回false 
	  */  
	 public static boolean deleteFile(String path,String fileName) {  
	  File file = new File(path+fileName);  
	  // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除  
	  if (file.exists() && file.isFile()) {  
	   if (file.delete()) {  
	    System.out.println("删除单个文件" + fileName + "成功！");  
	    return true;  
	   } else {  
	    System.out.println("删除单个文件" + fileName + "失败！");  
	    return false;  
	   }  
	  } else {  
	   System.out.println("删除单个文件失败：" + fileName + "不存在！");  
	   return false;  
	  }  
	 }  

}
