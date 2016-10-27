package datamotion.mvc.t12_initmodule;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;
import com.jfinal.aop.Before;

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
