package datamotion.mvc.t9_checkoutfiles;

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
 * /jf/datamotion/t9_checkoutfiles
 * /jf/datamotion/t9_checkoutfiles/save
 * /jf/datamotion/t9_checkoutfiles/edit
 * /jf/datamotion/t9_checkoutfiles/update
 * /jf/datamotion/t9_checkoutfiles/view
 * /jf/datamotion/t9_checkoutfiles/delete
 * /datamotion/t9_checkoutfiles/add.html
 * 
 */
//@Controller(controllerKey = "/jf/datamotion/t9_checkoutfiles")
public class T9_checkoutfilesController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T9_checkoutfilesController.class);

	public static final String pthc = "/jf/datamotion/t9_checkoutfiles/";
	public static final String pthv = "/datamotion/t9_checkoutfiles/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T9_checkoutfiles.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T9_checkoutfilesValidator.class)
	public void save() {
		T9_checkoutfiles t9_checkoutfiles = getModel(T9_checkoutfiles.class);
		//other set 
		
		//t9_checkoutfiles.save();		//guiid
		t9_checkoutfiles.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T9_checkoutfiles t9_checkoutfiles = T9_checkoutfiles.dao.findById(getPara());	//guuid
		T9_checkoutfiles t9_checkoutfiles = T9_checkoutfilesService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t9_checkoutfiles", t9_checkoutfiles);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T9_checkoutfilesValidator.class)
	public void update() {
		getModel(T9_checkoutfiles.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T9_checkoutfiles t9_checkoutfiles = T9_checkoutfiles.dao.findById(getPara());	//guuid
		T9_checkoutfiles t9_checkoutfiles = T9_checkoutfilesService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t9_checkoutfiles", t9_checkoutfiles);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T9_checkoutfilesService.service.delete("t9_checkoutfiles", getPara() == null ? ids : getPara());	//guuid
		T9_checkoutfilesService.service.deleteById("t9_checkoutfiles", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
