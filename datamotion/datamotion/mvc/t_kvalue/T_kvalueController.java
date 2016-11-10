package datamotion.mvc.t_kvalue;

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
 * /jf/datamotion/t_kvalue
 * /jf/datamotion/t_kvalue/save
 * /jf/datamotion/t_kvalue/edit
 * /jf/datamotion/t_kvalue/update
 * /jf/datamotion/t_kvalue/view
 * /jf/datamotion/t_kvalue/delete
 * /datamotion/t_kvalue/add.html
 * 
 */
//@Controller(controllerKey = "/jf/datamotion/t_kvalue")
public class T_kvalueController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T_kvalueController.class);

	public static final String pthc = "/jf/datamotion/t_kvalue/";
	public static final String pthv = "/datamotion/t_kvalue/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T_kvalue.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T_kvalueValidator.class)
	public void save() {
		T_kvalue t_kvalue = getModel(T_kvalue.class);
		//other set 
		
		//t_kvalue.save();		//guiid
		t_kvalue.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T_kvalue t_kvalue = T_kvalue.dao.findById(getPara());	//guuid
		T_kvalue t_kvalue = T_kvalueService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t_kvalue", t_kvalue);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T_kvalueValidator.class)
	public void update() {
		getModel(T_kvalue.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T_kvalue t_kvalue = T_kvalue.dao.findById(getPara());	//guuid
		T_kvalue t_kvalue = T_kvalueService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t_kvalue", t_kvalue);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T_kvalueService.service.delete("t_kvalue", getPara() == null ? ids : getPara());	//guuid
		T_kvalueService.service.deleteById("t_kvalue", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
