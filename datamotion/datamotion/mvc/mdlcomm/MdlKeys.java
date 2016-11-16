/**
 * Nov 14, 2016
4:57:16 PM
lyf
 */
package datamotion.mvc.mdlcomm;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author lyf
Nov 14, 2016
 *
 */
public class MdlKeys implements Serializable {
	public ArrayList<String> keys;
	public MdlKeys() {
		
	}
	public ArrayList<String> getKeys() {
		
		return keys;
		
	}
	public void setKeys(ArrayList<String> keys) {
		
		this.keys = keys;
		
	}
	public String getSQLStr(String aTableName) {

		// 多条件查询，数据库需要建立一些索引
		StringBuilder sbSQLTree = new StringBuilder();
		//从root的第一个节点开始 拼接SQL
		sbSQLTree.append("select * from ").append(aTableName).append(" where id IN (");
		getSQLByKeys(sbSQLTree);

		sbSQLTree.append(") order by id desc limit 200");
		
		return sbSQLTree.toString();
	}
	public void getSQLByKeys(StringBuilder sbSQLTree) {
		for(int i = 0;i < this.keys.size() - 1;i++)
		{
			sbSQLTree.append(this.keys.get(i)+",");
		}
		sbSQLTree.append(this.keys.get(this.keys.size() - 1));
	}
}
