/**
 * <p>title:MdlTreeProperty.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月10日下午3:33:09
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datamotion.mvc.t11_initfoldertree.T11_initfoldertree;
import datamotion.mvc.t12_initmodule.T12_initmodule;

/**  
 * 创建时间：2016年11月10日 下午3:33:09  
 * 项目名称：AutDataMotion   
 * 文件名称：MdlTreeProperty.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月10日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: MdlTreeProperty<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年11月10日
 */
public class MdlTreeProperty implements Serializable{
	// 父节点
	public MdlTreeProperty parent;
	// 本身属性
	public T11_initfoldertree self;
	// 英文缩写
	public String nameEng;
	// 英文缩写路径
	public String nameEngPath;
	// 配置信息
	public T12_initmodule property;
	// 孩子节点
	public Map<String, MdlTreeProperty> children = new HashMap<String, MdlTreeProperty>();

	public MdlTreeProperty() {

	}

}
