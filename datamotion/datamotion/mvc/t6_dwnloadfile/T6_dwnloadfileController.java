package datamotion.mvc.t6_dwnloadfile;

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

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T6_dwnloadfile.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
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
	
}
