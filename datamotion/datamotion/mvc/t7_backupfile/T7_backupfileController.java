package datamotion.mvc.t7_backupfile;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.JsonUtils;
import datamotion.mvc.mdlcomm.MdlClientCheckout;
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
		/*
		// 获得参数
		String sEcho=getPara("sEcho");
		String iDisplayStart=getPara("iDisplayStart");
		String iDisplayLength=getPara("iDisplayLength");
		//JSONArray jsonarray=new JSONArray(jsondata);
		
		log.debug(sEcho+","+iDisplayStart+","+iDisplayLength);
		List<T7_backupfile> list = T7_backupfile.dao.find(
				"select * from t7_backupfile order by id desc");
		List<T7_backupfile> sublist = list.subList(Integer.parseInt(iDisplayStart), Integer.parseInt(iDisplayStart)+Integer.parseInt(iDisplayLength));
		int count = list.size();
		log.debug(sublist);
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("sEcho", sEcho);
		jsonObject2.put("iTotalRecords", count);
		jsonObject2.put("iTotalDisplayRecords", count);
		jsonObject2.put("aaData", sublist.toString());
		//String json = "{\"sEcho\":"+sEcho+",\"iTotalRecords\":"+count+",\"iTotalDisplayRecords\":"+count+",\"aaData\":"+sublist+"}";  
		*/
		// 获得参数
		String draw=getPara("draw");
		String start=getPara("start");
		String length=getPara("length");
		
		List<T7_backupfile> list = T7_backupfile.dao.find(
				"select * from t7_backupfile order by id desc");
		int toindex = Integer.parseInt(start)+Integer.parseInt(length);
		List<T7_backupfile> sublist=null;
		if(toindex<=list.size())
		{
			sublist = list.subList(Integer.parseInt(start), Integer.parseInt(start)+Integer.parseInt(length));
		}
		else{
			sublist = list.subList(Integer.parseInt(start), list.size());
		}
		
		int count = list.size();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("draw", draw);
		jsonObject.put("recordsTotal", count);
		jsonObject.put("recordsFiltered", count);
		
		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		for(int i=0;i<sublist.size();i++)
		{
			ArrayList<String> subdata = new ArrayList<String>();
			subdata.add(sublist.get(i).get("id").toString());
			subdata.add(sublist.get(i).get("pathsrc").toString());
			subdata.add(sublist.get(i).get("namesrc").toString());
			subdata.add(sublist.get(i).get("pathdest").toString());
			subdata.add(sublist.get(i).get("timedo").toString());
			subdata.add(sublist.get(i).get("filesize").toString());
			subdata.add(sublist.get(i).get("station").toString());
			subdata.add(sublist.get(i).get("aircraft").toString());
			subdata.add(sublist.get(i).get("sensor").toString());
			subdata.add(sublist.get(i).get("datatype").toString());
			subdata.add(sublist.get(i).get("datalevel").toString());
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add("");
			//subdata.add(sublist.get(0).get("labelids").toString());
			data.add(subdata);
		}
		jsonObject.put("data", data);
		log.debug(jsonObject);
		renderJson(jsonObject.toString());
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
	public void addData2Database()
	{
		
	}
	// 查询
	@Clear
	public void doQuery() {
		
		// 获得参数
		String info = getPara("info");
		if (null == info || info.isEmpty()) {
			renderText("-1");//错误
		}
		//log.debug(strvalue);
		try {
			MdlClientCheckout mdlClient = JsonUtils.deserialize(info, MdlClientCheckout.class);
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

		
		//遍历树结构，拼接SQL语句
		
		//数据库查询
		
		//返回结果
		
		// renderJson(null);
	}
	//全部本地下载
	@Clear
	public void doAllLocalDownload() {
		
		
	}
	//全部重新备份
	@Clear
	public void doAllNewBackup() {
		
		
	}

}
