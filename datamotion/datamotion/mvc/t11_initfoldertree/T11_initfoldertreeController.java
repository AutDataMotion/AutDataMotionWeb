package datamotion.mvc.t11_initfoldertree;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.objects.annotations.Where;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;
import org.beetl.ext.fn.ParseInt;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import csuduc.platform.util.JsonUtils;
import datamotion.constant.ConstantInitMy;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/datamotion/t11_initfoldertree
 * /jf/datamotion/t11_initfoldertree/save
 * /jf/datamotion/t11_initfoldertree/edit
 * /jf/datamotion/t11_initfoldertree/update
 * /jf/datamotion/t11_initfoldertree/view
 * /jf/datamotion/t11_initfoldertree/delete
 * /datamotion/t11_initfoldertree/add.html
 * 
 */
//@Controller(controllerKey = "/jf/datamotion/t11_initfoldertree")
public class T11_initfoldertreeController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T11_initfoldertreeController.class);

	public static final String pthc = "/jf/datamotion/t11_initfoldertree/";
	public static final String pthv = "/datamotion/t11_initfoldertree/";
	public static final String pthvf = "/datamotion/f/";

	/**
	 * 列表
	 */
	
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T11_initfoldertree.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	@Clear
	public void visual() {
		// paging(ConstantInitMy.db_dataSource_main, splitPage,
		// BaseModel.sqlId_splitPage_select,
		// T8_archivefile.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");

		renderWithPath(pthvf + "visualization.html");
	}
	
	@Clear
	/*view tree*/
	public void treeIndex() {  
		List<Record> res = Db.use(ConstantInitMy.db_dataSource_main)
				.find("select key_,parentkeys,namechi,level from t11_initfoldertree");
		setAttr("resInit", res);
		renderWithPath(pthv+"treeConfig.html");
	}
	
	@Clear
	/*add tree node*/
	public void addTreeNode(){
		String key_=getPara("key_");
		String parentkeys=getPara("parentkeys");
		String namechi=getPara("namechi");
		String level=getPara("level");
		if (key_ != null && (key_.length() > 0)) {
			/*存储节点的基本信息*/
			Record t11_initfoldertree=new Record()
				.set("key_", key_)
				.set("parentkeys", parentkeys)
				.set("namechi", namechi)
				.set("level", Integer.valueOf(level))
				.set("timeadd", new Timestamp(System.currentTimeMillis()));
			Db.use(ConstantInitMy.db_dataSource_main)
				.save("t11_initfoldertree", t11_initfoldertree);
			List<Record> childkeylist=Db.use(ConstantInitMy.db_dataSource_main)
				.find("select childkeys from t11_initfoldertree where key_=?",parentkeys);
			String childkeys = null;
			if (childkeylist != null && childkeylist.size() != 0) {
				childkeys=childkeylist.get(0).get("childkeys");
				if(childkeys==null||childkeys.isEmpty())childkeys=key_;
				else childkeys=childkeys+"-"+key_;
			}
			/*更新当前节点的父节点的子节点信息*/
			Db.use(ConstantInitMy.db_dataSource_main)
				.update("update t11_initfoldertree set childkeys=? where key_=?",childkeys,parentkeys);
		}
	}
	
	@Clear
	/*modify tree node*/
	public void modifyTreeNode(){
		String key_=getPara("key_");
		String newName=getPara("newName");
		
		/*更新当前节点的中文名称*/
		Db.use(ConstantInitMy.db_dataSource_main)
			.update("update t11_initfoldertree set namechi=?,timeupdate='"
		+new Timestamp(System.currentTimeMillis())+
		"' where key_=?",newName,key_);
	}
	
	@Clear
	/*delete tree node*/
	public void delTreeNode(){
		String key_=getPara("key_");
		String childkeys=getPara("childkeys");
		
		/*根据key_更新该父节点的子节点信息*/
		Db.use(ConstantInitMy.db_dataSource_main)
			.update("update t11_initfoldertree set childkeys='"+childkeys+
				"',timeupdate='"+new Timestamp(System.currentTimeMillis())+"' where key_=(select parentkeys from t11_initfoldertree"+
				" where key_='"+key_+"')");
		/*根据key_删除当前节点以及子节点*/
		Db.use(ConstantInitMy.db_dataSource_main)
			.update("delete from t11_initfoldertree where parentkeys=?",key_);
		Db.use(ConstantInitMy.db_dataSource_main)
			.update("delete from t11_initfoldertree where key_=?",key_);
		/*根据key_删除t12_initmodule中的信息*/
		Db.use(ConstantInitMy.db_dataSource_main)
		.update("delete from t12_initmodule where fkeystreenode=?",key_);
		
		
	}
	@Clear
	/*get tree node*/
	public void getTreeNode(){
		List<Record> res = Db.use(ConstantInitMy.db_dataSource_main)
			.find("select key_ as id,parentkeys as pid,namechi as name from t11_initfoldertree");
		renderJson(res);
	}
	@Clear
	public void show(){
		T11_initfoldertree t11_initfoldertree = new T11_initfoldertree();
		t11_initfoldertree.setId(1234);
		t11_initfoldertree.setKey_("nihao");
		setAttr("t11_initfoldertree", t11_initfoldertree);
		renderWithPath(pthv+"view.html");
	}
	/**
	 * 保存
	 */
	@Before(T11_initfoldertreeValidator.class)
	public void save() {
		T11_initfoldertree t11_initfoldertree = getModel(T11_initfoldertree.class);
		//other set 
		//t11_initfoldertree.save();		//guiid
		t11_initfoldertree.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	@Clear
	public void edit() {
		//T11_initfoldertree t11_initfoldertree = T11_initfoldertree.dao.findById(getPara());	//guuid
		T11_initfoldertree t11_initfoldertree = T11_initfoldertreeService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t11_initfoldertree", t11_initfoldertree);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T11_initfoldertreeValidator.class)
	public void update() {
		getModel(T11_initfoldertree.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T11_initfoldertree t11_initfoldertree = T11_initfoldertree.dao.findById(getPara());	//guuid
		T11_initfoldertree t11_initfoldertree = T11_initfoldertreeService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t11_initfoldertree", t11_initfoldertree);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T11_initfoldertreeService.service.delete("t11_initfoldertree", getPara() == null ? ids : getPara());	//guuid
		T11_initfoldertreeService.service.deleteById("t11_initfoldertree", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
