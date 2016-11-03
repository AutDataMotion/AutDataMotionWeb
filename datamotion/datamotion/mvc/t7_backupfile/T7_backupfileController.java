package datamotion.mvc.t7_backupfile;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;


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
	/**
	 * 列表
	 */
	@Clear
	public void index() {
//		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T7_backupfile.sqlId_splitPage_from);
//		renderWithPath(pthv+"list.html");

		renderWithPath(pthv+"backupfile.html");
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
	public void viewbackupfile()
	{
	}
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
