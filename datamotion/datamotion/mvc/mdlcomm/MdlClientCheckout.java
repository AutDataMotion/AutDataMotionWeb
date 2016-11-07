/**
 * <p>title:MdlClientCheckout.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月6日下午3:18:33
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.mvc.mdlcomm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 * 创建时间：2016年11月6日 下午3:18:33  
 * 项目名称：AutDataMotion   
 * 文件名称：MdlClientCheckout.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月6日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: MdlClientCheckout<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年11月6日
 */
public class MdlClientCheckout implements Serializable {

	public List<MdlClientTreeChecked> treecheckeds;
	public String timebegcollect;
	public String timeendcollect;
	public String timebegreceive;
	public String timeendreceive;
	public String timebegdb;
	public String timeenddb;
	public int status;
	private transient MdlClientTreeChecked root;

	public MdlClientCheckout() {
	}

	public String getSQLStr(String aTableName) {
		// 这里的SQL 需要优化，建议与数据库的树比对，以缩减查询条件
		// 比如全选了某个载荷，则其下面的对于孩子的查询选项不必再写
		// 多条件查询，数据库需要建立一些索引
		StringBuilder sb = new StringBuilder();
		MdlClientTreeChecked curaircraft;
		MdlClientTreeChecked cursensor;
		MdlClientTreeChecked curdatatype;
		MdlClientTreeChecked curdatalevel;
		MdlClientTreeChecked curcameras;
		List<MdlClientTreeChecked> node_aircrafts = new ArrayList<MdlClientTreeChecked>(
				2);
		List<MdlClientTreeChecked> node_sensors = new ArrayList<MdlClientTreeChecked>(
				20);
		List<MdlClientTreeChecked> node_datatypes = new ArrayList<MdlClientTreeChecked>(
				6);
		List<MdlClientTreeChecked> node_datalevels = new ArrayList<MdlClientTreeChecked>(
				6);
		List<MdlClientTreeChecked> node_cameras = new ArrayList<MdlClientTreeChecked>(
				30);

		Map<String, MdlClientTreeChecked> mapNodes = new HashMap<String, MdlClientTreeChecked>();
		Map<String, List<String>> mapSQL = new HashMap<String, List<String>>();
		// 初始化查询树
		for (MdlClientTreeChecked item : treecheckeds) {
			// 改用map
			mapNodes.put(item.id, item);
			switch (item.level) {
			case 0:
				// 根节点
				root = item;
				break;
			case 1:
				// aircrafts 飞行器
				curaircraft = item;
				root.addChild(curaircraft);
				node_aircrafts.add(curaircraft);
				break;
			case 2:
				// sensors 载荷
				for (MdlClientTreeChecked mdlClientTreeChecked : node_cameras) {
					
				}
				root = item;
				break;
			case 3:
				// datatypes 产品类型
				root = item;
				break;
			case 4:
				// datalevel 产品等级
				root = item;
				break;
			case 5:
				// camera 产品标识 最后的叶子节点
				root = item;
				break;
			default:
				break;
			}

		}

		// 初始化查询树
		for (MdlClientTreeChecked item : treecheckeds) {
			// 改用map
			mapNodes.put(item.id, item);
			switch (item.level) {
			case 0:
				// 根节点
				root = item;
				break;
			case 1:
				// aircrafts 飞行器
				node_aircrafts.add(item);
				break;
			case 2:
				// sensors 载荷

				root = item;
				break;
			case 3:
				// datatypes 产品类型
				root = item;
				break;
			case 4:
				// datalevel 产品等级
				root = item;
				break;
			case 5:
				// camera 产品标识 最后的叶子节点
				root = item;
				break;
			default:
				break;
			}

		}

		return sb.toString();
	}

	/**
	 * @return the treecheckeds
	 */
	public List<MdlClientTreeChecked> getTreecheckeds() {
		return treecheckeds;
	}

	/**
	 * @param treecheckeds
	 *            the treecheckeds to set
	 */
	public void setTreecheckeds(List<MdlClientTreeChecked> treecheckeds) {
		this.treecheckeds = treecheckeds;
	}

	/**
	 * @return the timebegcollect
	 */
	public String getTimebegcollect() {
		return timebegcollect;
	}

	/**
	 * @param timebegcollect
	 *            the timebegcollect to set
	 */
	public void setTimebegcollect(String timebegcollect) {
		this.timebegcollect = timebegcollect;
	}

	/**
	 * @return the timeendcollect
	 */
	public String getTimeendcollect() {
		return timeendcollect;
	}

	/**
	 * @param timeendcollect
	 *            the timeendcollect to set
	 */
	public void setTimeendcollect(String timeendcollect) {
		this.timeendcollect = timeendcollect;
	}

	/**
	 * @return the timebegdb
	 */
	public String getTimebegdb() {
		return timebegdb;
	}

	/**
	 * @param timebegdb
	 *            the timebegdb to set
	 */
	public void setTimebegdb(String timebegdb) {
		this.timebegdb = timebegdb;
	}

	/**
	 * @return the timeenddb
	 */
	public String getTimeenddb() {
		return timeenddb;
	}

	/**
	 * @param timeenddb
	 *            the timeenddb to set
	 */
	public void setTimeenddb(String timeenddb) {
		this.timeenddb = timeenddb;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
