package datamotion.config;

import java.util.List;

import org.apache.log4j.Logger;

import com.platform.config.run.BaseConfMain;

import datamotion.mvc.mdlcomm.MdlClientTreeChecked;
import datamotion.mvc.t11_initfoldertree.T11_initfoldertree;
import datamotion.mvc.t12_initmodule.T12_initmodule;

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
	private static List<T11_initfoldertree> listInitfoldertrees;
	private static List<T12_initmodule> listInitmodules;
	
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
		listInitfoldertrees = T11_initfoldertree.dao.find("");
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
