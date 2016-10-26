/**
 * <p>title:MappingTable.java<／p>
 * <p>Description: <／p>
 * @date:2016年3月12日上午10:11:42
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.config;

import org.apache.log4j.Logger;
import datamotion.mvc.t6_dwnloadfile.T6_dwnloadfile;
import datamotion.mvc.t7_backupfile.T7_backupfile;
import datamotion.mvc.t8_archivefile.T8_archivefile;
import datamotion.mvc.t9_checkoutfiles.T9_checkoutfiles;
import datamotion.mvc.t11_initfoldertree.T11_initfoldertree;
import datamotion.mvc.t12_initmodule.T12_initmodule;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
/**
 * 创建时间：2016年3月12日 上午10:11:42
 * 项目名称：DUCPlatFromWeb
 * 文件类型：MappingTable.java
 * 类说明：
 *
 *  
 *修改日志：
 * Date			Author		Version		Description
 *---------------------------------------------------
 *2016年3月12日		Zhongweng	1.0			1.0Version
 */

/**
 * <p>Title: MappingTable<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年3月12日
 */
public class MappingTable {

	private static Logger log = Logger.getLogger(MappingTable.class);
	public static void mapping(ActiveRecordPlugin arp){
		log.info("datamotion MappingTable 表手工注册-----begin");
		arp.addMapping("t6_dwnloadfile", "id", T6_dwnloadfile.class);
		arp.addMapping("t7_backupfile", "id", T7_backupfile.class);
		arp.addMapping("t8_archivefile", "id", T8_archivefile.class);
		arp.addMapping("t9_checkoutfiles", "id", T9_checkoutfiles.class);
		arp.addMapping("t11_initfoldertree", "id", T11_initfoldertree.class);
		arp.addMapping("t12_initmodule", "id", T12_initmodule.class);
		log.info("datamotion MappingTable 表手工注册-----end");
		
	}
}
