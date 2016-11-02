package datamotion.mvc.t12_initmodule;

import java.sql.Timestamp;
import java.util.List;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import csuduc.platform.util.generID.UUIDGener;
import datamotion.constant.ConstantInitMy;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/datamotion/t12_initmodule
 * /jf/datamotion/t12_initmodule/save
 * /jf/datamotion/t12_initmodule/edit
 * /jf/datamotion/t12_initmodule/update
 * /jf/datamotion/t12_initmodule/view
 * /jf/datamotion/t12_initmodule/delete
 * /datamotion/t12_initmodule/add.html
 * 
 */
//@Controller(controllerKey = "/jf/datamotion/t12_initmodule")
public class T12_initmoduleController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T12_initmoduleController.class);

	public static final String pthc = "/jf/datamotion/t12_initmodule/";
	public static final String pthv = "/datamotion/t12_initmodule/";

	@Clear
	public void getModelInfo(){
		String fkey=getPara("key_");
		List<Record> res = Db.use(ConstantInitMy.db_dataSource_main)
			.find("select * from t12_initmodule where fkeystreenode=?",fkey);
		renderJson(res);
	}
	
	@Clear
	public void saveModelInfo(){
		String fkeystreenode=getPara("fkeystreenode");
		String aircraft=getPara("aircraft");
		String sensor=getPara("sensor");
		String datatype=getPara("datatype");
		String camera=getPara("camera");
		String datalevel=getPara("datalevel");
		String isdwnload=getPara("isdwnload");
		String pathftp=getPara("pathftp");
		String pathdwnload=getPara("pathdwnload");
		String isbackup=getPara("isbackup");
		String pathbackup=getPara("pathbackup");
		String isarchive=getPara("isarchive");
		String patharchive=getPara("patharchive");
		String ischeckout=getPara("ischeckout");
		String pathcheckout=getPara("pathcheckout");
		String namemdlsrc=getPara("namemdlsrc");
		String namemdldes=getPara("namemdldes");
		String ishavaaux=getPara("ishavaaux");
		String auxfiletypes=getPara("auxfiletypes");
		log.debug(Boolean.valueOf(isdwnload)+"ddd");
		List<Record> res = Db.use(ConstantInitMy.db_dataSource_main)
				.find("select * from t12_initmodule where fkeystreenode=?",fkeystreenode);
		
		if(res.size()==0||res.isEmpty()){
			Record t12_initmodule=new Record()
			.set("key_", UUIDGener.getUUIDShort())
			.set("fkeystreenode", fkeystreenode)
			.set("aircraft", aircraft)
			.set("sensor", sensor)
			.set("datatype", datatype)
			.set("camera", camera)
			.set("datalevel", datalevel)
			.set("isdwnload", Boolean.valueOf(isdwnload))
			.set("pathftp", pathftp)
			.set("pathdwnload", pathdwnload)
			.set("isbackup", Boolean.valueOf(isbackup))
			.set("pathbackup", pathbackup)
			.set("isarchive", Boolean.valueOf(isarchive))
			.set("patharchive", patharchive)
			.set("ischeckout", Boolean.valueOf(ischeckout))
			.set("pathcheckout", pathcheckout)
			.set("namemdlsrc", namemdlsrc)
			.set("namemdldes", namemdldes)
			.set("ishavaaux", Boolean.valueOf(ishavaaux))
			.set("auxfiletypes", auxfiletypes)
			.set("timeadd", new Timestamp(System.currentTimeMillis()));
			Db.use(ConstantInitMy.db_dataSource_main)
			  .save("t12_initmodule", t12_initmodule);
		}else {
			String key_=res.get(0).get("key_");
			Db.use(ConstantInitMy.db_dataSource_main)
			.update("update t12_initmodule set aircraft='"+aircraft+
			"',sensor='"+sensor+"',datatype='"+datatype+
			"',camera='"+camera+"',datalevel='"+datalevel+
			"',isdwnload="+Boolean.valueOf(isdwnload)+",pathftp='"+pathftp+
			"',pathdwnload='"+pathdwnload+"',isbackup="+Boolean.valueOf(isbackup)+
			",pathbackup='"+pathbackup+"',isarchive="+Boolean.valueOf(isarchive)+
			",patharchive='"+patharchive+"',ischeckout="+Boolean.valueOf(ischeckout)+
			",pathcheckout='"+pathcheckout+"',namemdlsrc='"+namemdlsrc+
			"',namemdldes='"+namemdldes+"',ishavaaux="+Boolean.valueOf(ishavaaux)+
			",auxfiletypes='"+auxfiletypes+
			"',timeupdate='"+new Timestamp(System.currentTimeMillis())+
			"' where key_=?",key_);
		}
		renderText("operate succeed");
	}
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T12_initmodule.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T12_initmoduleValidator.class)
	public void save() {
		T12_initmodule t12_initmodule = getModel(T12_initmodule.class);
		//other set 
		
		//t12_initmodule.save();		//guiid
		t12_initmodule.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T12_initmodule t12_initmodule = T12_initmodule.dao.findById(getPara());	//guuid
		T12_initmodule t12_initmodule = T12_initmoduleService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t12_initmodule", t12_initmodule);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T12_initmoduleValidator.class)
	public void update() {
		getModel(T12_initmodule.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T12_initmodule t12_initmodule = T12_initmodule.dao.findById(getPara());	//guuid
		T12_initmodule t12_initmodule = T12_initmoduleService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t12_initmodule", t12_initmodule);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T12_initmoduleService.service.delete("t12_initmodule", getPara() == null ? ids : getPara());	//guuid
		T12_initmoduleService.service.deleteById("t12_initmodule", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
