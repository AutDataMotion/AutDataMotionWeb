/**
 * <p>title:routePlugins.java<／p>
 * <p>Description: <／p>
 * @date:2016年1月28日下午2:15:23
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.config;
import datamotion.mvc.t6_dwnloadfile.T6_dwnloadfileController;
import datamotion.mvc.t7_backupfile.T7_backupfileController;
import datamotion.mvc.t8_archivefile.T8_archivefileController;
import datamotion.mvc.t9_checkoutfiles.T9_checkoutfilesController;
import datamotion.mvc.t_kvalue.T_kvalueController;
import datamotion.mvc.t11_initfoldertree.T11_initfoldertreeController;
import datamotion.mvc.t12_initmodule.T12_initmoduleController;

import com.jfinal.config.Routes;
/**
 * 创建时间：2016年1月28日 下午2:15:23
 * 项目名称：DUCPlatFromWeb
 * 文件类型：RoutePlugins.java
 * 类说明：
 *
 *  
 *修改日志：
 * Date			Author		Version		Description
 *---------------------------------------------------
 *2016年1月28日		Zhongweng	1.0			1.0Version
 */

/**
 * <p>Title: RoutePlugins<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年1月28日
 */
public class RoutePlugins extends Routes {
	@Override
	public void config() {
		add("/jf/datamotion/t6_dwnloadfile", T6_dwnloadfileController.class);
		add("/jf/datamotion/t7_backupfile", T7_backupfileController.class);
		add("/jf/datamotion/t8_archivefile", T8_archivefileController.class);
		add("/jf/datamotion/t9_checkoutfiles", T9_checkoutfilesController.class);
		add("/jf/datamotion/t11_initfoldertree", T11_initfoldertreeController.class);
		add("/jf/datamotion/t12_initmodule", T12_initmoduleController.class);
		add("/jf/datamotion/t_kvalue", T_kvalueController.class);
	}
}
