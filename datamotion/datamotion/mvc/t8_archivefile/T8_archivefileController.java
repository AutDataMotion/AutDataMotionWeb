package datamotion.mvc.t8_archivefile;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;

import sun.util.logging.resources.logging;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;

import datamotion.constant.ConstantInitMy;

/**
 * XXX 管理 描述：
 * 
 * /jf/datamotion/t8_archivefile /jf/datamotion/t8_archivefile/save
 * /jf/datamotion/t8_archivefile/edit /jf/datamotion/t8_archivefile/update
 * /jf/datamotion/t8_archivefile/view /jf/datamotion/t8_archivefile/delete
 * /datamotion/t8_archivefile/add.html
 * 
 */
// @Controller(controllerKey = "/jf/datamotion/t8_archivefile")
public class T8_archivefileController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger
			.getLogger(T8_archivefileController.class);

	public static final String pthc = "/jf/datamotion/t8_archivefile/";
	public static final String pthv = "/datamotion/t8_archivefile/";

	/**
	 * 列表
	 */
	@Clear
	public void index() {
		// paging(ConstantInitMy.db_dataSource_main, splitPage,
		// BaseModel.sqlId_splitPage_select,
		// T8_archivefile.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");

		//renderWithPath(pthv + "mng.html");
	}

	@Clear
	public void log() {

		renderWithPath(pthv + "archlog.html");
	}

	@Clear
	public void conf() {

		renderWithPath(pthv + "archconf.html");
	}

	/**
	 * 保存
	 */
	@Before(T8_archivefileValidator.class)
	public void save() {
		T8_archivefile t8_archivefile = getModel(T8_archivefile.class);
		// other set

		// t8_archivefile.save(); //guiid
		t8_archivefile.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T8_archivefile t8_archivefile =
		// T8_archivefile.dao.findById(getPara()); //guuid
		T8_archivefile t8_archivefile = T8_archivefileService.service
				.SelectById(getParaToInt()); // serial int id
		setAttr("t8_archivefile", t8_archivefile);
		renderWithPath(pthv + "update.html");

	}

	/**
	 * 更新
	 */
	@Before(T8_archivefileValidator.class)
	public void update() {
		getModel(T8_archivefile.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		// T8_archivefile t8_archivefile =
		// T8_archivefile.dao.findById(getPara()); //guuid
		T8_archivefile t8_archivefile = T8_archivefileService.service
				.SelectById(getParaToInt()); // serial int id
		setAttr("t8_archivefile", t8_archivefile);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T8_archivefileService.service.delete("t8_archivefile", getPara() ==
		// null ? ids : getPara()); //guuid
		T8_archivefileService.service.deleteById("t8_archivefile",
				getPara() == null ? ids : getPara()); // serial int id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
