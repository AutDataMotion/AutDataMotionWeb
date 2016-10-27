package datamotion.mvc.t11_initfoldertree;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;

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

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T11_initfoldertree.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
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
