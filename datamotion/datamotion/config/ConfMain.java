package datamotion.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.platform.config.run.BaseConfMain;

import datamotion.mvc.mdlcomm.MdlClientTreeChecked;
import datamotion.mvc.t11_initfoldertree.T11_initfoldertree;
import datamotion.mvc.t11_initfoldertree.T11_initfoldertreeService;
import datamotion.mvc.t12_initmodule.T12_initmodule;
import datamotion.mvc.t12_initmodule.T12_initmoduleService;
import datamotion.mvc.t_kvalue.T_kvalue;
import datamotion.mvc.t_kvalue.T_kvalueService;

/**  
 * 创建时间：2016年1月26日 上午11:13:45  
 * 项目名称：DUCPlatFormWeb   
 * 文件名称：ConfMain.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年1月26日     Zhongweng       1.0         1.0 Version   
 */

/**
 * <p>Title: ConfMain<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年11月8日
 */
public class ConfMain extends BaseConfMain{
	private static Logger log = Logger.getLogger(ConfMain.class);
	private final static ConfMain single = new ConfMain();
	public static MdlClientTreeChecked treeRootTG2;
	public static List<T11_initfoldertree> listInitfoldertrees;
	public static List<T12_initmodule> listInitmodules;
	public static List<T_kvalue> listKValues;
	public static Map<String, T12_initmodule> mapProperty = new HashMap<String, T12_initmodule>();
	
	public static ConfMain getInstance(){
		return single;
	}
	
	
	/**
	 * <p>Title: loadFolderTree<／p>
	 * <p>Description: 
	 * 加载目录树
	 * <／p>
	 * @return
	 */
	public static boolean loadFolderTree(){
		try {
			listInitfoldertrees = T11_initfoldertreeService.service.getList();
			listInitmodules = T12_initmoduleService.service.getList();
			listKValues = T_kvalueService.service.getList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return true;
	}
	
	/**
	 * <p>Title: loadModuleProperties<／p>
	 * <p>Description:
	 * 加载模块配置信息
	 *  <／p>
	 * @return
	 */
	public static boolean loadModuleProperties(){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
}
