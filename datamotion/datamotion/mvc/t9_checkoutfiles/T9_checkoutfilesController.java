package datamotion.mvc.t9_checkoutfiles;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.JsonUtils;
import datamotion.mvc.mdlcomm.MdlClientCheckout;

/**
 * XXX 管理 描述：
 * 
 * /jf/datamotion/t9_checkoutfiles /jf/datamotion/t9_checkoutfiles/save
 * /jf/datamotion/t9_checkoutfiles/edit /jf/datamotion/t9_checkoutfiles/update
 * /jf/datamotion/t9_checkoutfiles/view /jf/datamotion/t9_checkoutfiles/delete
 * /datamotion/t9_checkoutfiles/add.html
 * 
 */
// @Controller(controllerKey = "/jf/datamotion/t9_checkoutfiles")
public class T9_checkoutfilesController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger
			.getLogger(T9_checkoutfilesController.class);

	public static final String pthc = "/jf/datamotion/t9_checkoutfiles/";
	public static final String pthv = "/datamotion/t9_checkoutfiles/";

	/**
	 * 列表
	 */
	@Clear
	public void index() {
		// paging(ConstantInitMy.db_dataSource_main, splitPage,
		// BaseModel.sqlId_splitPage_select,
		// T9_checkoutfiles.sqlId_splitPage_from);
		// renderWithPath(pthv+"list.html");
		// 提取前100条
		List<T9_checkoutfiles> list = T9_checkoutfiles.dao.find(
				"select * from t9_checkoutfiles order by id desc limit ?", 100);
		setAttr("list", list);
		renderWithPath(pthv + "mng.html");
	}

	// 查询
	@Clear
	public void search() {

		// 获得参数
		String strvalue = getPara("v");
		if (null == strvalue || strvalue.isEmpty()) {
			renderText("-1");//错误
		}
		//log.debug(strvalue);
		MdlClientCheckout mdlClient = null;
		try {
			mdlClient = JsonUtils.deserialize(strvalue, MdlClientCheckout.class);
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

		// 遍历树结构，拼接SQL语句
		String strSQL = mdlClient.getSQLStr("t9_checkoutfiles");
		log.debug(strSQL);
		// 数据库查询
		List<T9_checkoutfiles> res = T9_checkoutfiles.dao.find(strSQL);
		
		// 返回结果
		if (null == res || res.size() <= 0) {
			renderText("-1");
			return;
		}else {
			 renderJson(res);
			 return ;
		}
		
	}

	@Clear
	public void download() {

		return;
	}

	// 全部下载本地
	@Clear
	public void downloadAll() {

		// renderJson(null);
	}

	// 全部检出
	@Clear
	public void checkoutAll() {

		// renderJson(null);
	}

	@Clear
	public void checkout() {

		// renderJson(null);
	}

	/**
	 * 保存
	 */
	@Before(T9_checkoutfilesValidator.class)
	public void save() {
		T9_checkoutfiles t9_checkoutfiles = getModel(T9_checkoutfiles.class);
		// other set

		// t9_checkoutfiles.save(); //guiid
		t9_checkoutfiles.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T9_checkoutfiles t9_checkoutfiles =
		// T9_checkoutfiles.dao.findById(getPara()); //guuid
		T9_checkoutfiles t9_checkoutfiles = T9_checkoutfilesService.service
				.SelectById(getParaToInt()); // serial int id
		setAttr("t9_checkoutfiles", t9_checkoutfiles);
		renderWithPath(pthv + "update.html");

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
		// T9_checkoutfiles t9_checkoutfiles =
		// T9_checkoutfiles.dao.findById(getPara()); //guuid
		T9_checkoutfiles t9_checkoutfiles = T9_checkoutfilesService.service
				.SelectById(getParaToInt()); // serial int id
		setAttr("t9_checkoutfiles", t9_checkoutfiles);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T9_checkoutfilesService.service.delete("t9_checkoutfiles", getPara()
		// == null ? ids : getPara()); //guuid
		T9_checkoutfilesService.service.deleteById("t9_checkoutfiles",
				getPara() == null ? ids : getPara()); // serial int id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
