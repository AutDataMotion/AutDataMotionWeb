/**
 * <p>title:MdlClientTreeChecked.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月6日下午3:20:29
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.mvc.mdlcomm;

import java.io.Serializable;
import java.util.List;

/**  
 * 创建时间：2016年11月6日 下午3:20:29  
 * 项目名称：AutDataMotion   
 * 文件名称：MdlClientTreeChecked.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月6日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: MdlClientTreeChecked<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年11月6日
 */
public class MdlClientTreeChecked implements Serializable {
	public String id;
	public String pid;
	public int level;
	public String name;
	public boolean isParent;
	public transient List<MdlClientTreeChecked> children;
	public transient MdlClientTreeChecked parent;

	public MdlClientTreeChecked() {
	}

	public void addChild(MdlClientTreeChecked aChild) {
		
	}

	public void addChildren(List<MdlClientTreeChecked> aChildren) {

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isParent
	 */
	public boolean isParent() {
		return isParent;
	}

	/**
	 * @param isParent
	 *            the isParent to set
	 */
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

}
