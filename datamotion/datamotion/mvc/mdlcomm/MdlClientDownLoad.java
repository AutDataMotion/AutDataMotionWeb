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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**  
 * 创建时间：2016年11月10日 下午4:18:33  
 * 项目名称：AutDataMotion   
 * 文件名称：MdlClientDownLoad.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月6日     Qinman       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: MdlClientDownLoad<／p>
 * <p>
 * Description: <／p>
 * 
 * @author Qinman
 * @date 2016年11月10日
 */
public class MdlClientDownLoad implements Serializable {

	public MdlClientTreeChecked treecheckeds;
	public String timebegcollect;
	public String timeendcollect;
	public String timebegreceive;
	public String timeendreceive;
	public String timebegdb;
	public String timeenddb;
	public int status;

	public MdlClientDownLoad() {
	}

	public void getSQLStrByNode(MdlClientTreeChecked aNode, StringBuilder aSbSQL){
		String nameField=null;
		switch (aNode.level) {

		case 1:
			// aircrafts 飞行器
			nameField = "aircraft";
			break;
		case 2:
			// sensors 载荷
			nameField = "sensor";
			break;
		case 3:
			// datatypes 产品类型
			nameField = "datatype";
			break;
		case 4:
			// datalevel 产品等级
			nameField = "datalevel";
			break;
		case 5:
			// camera 产品标识 最后的叶子节点
			nameField = "camera";
			break;
		default:
			
			break;
		}
		if (aNode.children !=null && aNode.children.size() > 0) {
			//有孩子
			aSbSQL.append(String.format("( %s='%s' and (", nameField, aNode.name));
			
			int i = 0;
			for (; i < aNode.children.size()-1; i++) {
				getSQLStrByNode(aNode.children.get(i), aSbSQL);
				aSbSQL.append(" or ");
			}
			//最后一个
			getSQLStrByNode(aNode.children.get(i), aSbSQL);

			//其他字段筛选
			aSbSQL.append(")");
			getOthersStr(aSbSQL);
			aSbSQL.append(")");
			
		}else{
			//没有孩子
			aSbSQL.append(String.format(" %s='%s' ", nameField, aNode.name));
		}
		
		return; 
	}
	//拼接其他查询条件的字段
	public void getOthersStr(StringBuilder aSbSQL){
		if(status != 5&&status < 10)
			aSbSQL.append(" and status_ = "+ status);
		aSbSQL.append(" and timecollectstart > '" + timebegcollect + "'");
		aSbSQL.append(" and timecollectend < '" + timeendcollect + "'");
		aSbSQL.append(" and timerecive > '" + timebegreceive + "'");
		aSbSQL.append(" and timerecive < '" + timeendreceive + "'");
		aSbSQL.append(" and timeadd > '" + timebegdb + "'");
		aSbSQL.append(" and timeadd < '" + timeenddb + "'");
	}
	public String getSQLStr(String aTableName) {

		// 多条件查询，数据库需要建立一些索引
		StringBuilder sbSQLTree = new StringBuilder();

		//root 节点特殊处理一下
		if (this.treecheckeds.children == null || this.treecheckeds.children.size()==0) {
			sbSQLTree.append("select * from ").append(aTableName).append(" where aircraft='TG02'");
			getOthersStr(sbSQLTree);
			sbSQLTree.append(" order by id desc limit 200");
			return sbSQLTree.toString();
		}
//		//从root的第一个节点开始 拼接SQL
		sbSQLTree.append("select * from ").append(aTableName).append(" where ");
		getSQLStrByNode(this.treecheckeds.children.get(0), sbSQLTree);

		sbSQLTree.append(" order by id desc limit 200");
		
		return sbSQLTree.toString();
	}
	public String getSQLStrDS(String aTableName,String ZHname) {//数据统计SQL语句
		// 多条件查询，数据库需要建立一些索引
		StringBuilder DS_SQL = new StringBuilder();
		DS_SQL.append("select datalevel,count(*) as num,sum(filesize) as vol from ").append(aTableName).append(" where aircraft='TG02'");
		DS_SQL.append(" and sensor = '"+ ZHname+"'");
		//getOthersStr(DS_SQL);
		DS_SQL.append(" group by datalevel");
		//sbSQLTree.append(" order by id desc limit 200");
		return DS_SQL.toString();
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
