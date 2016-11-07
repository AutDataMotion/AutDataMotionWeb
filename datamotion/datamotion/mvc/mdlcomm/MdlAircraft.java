/**
 * <p>title:MdlAircraft.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月7日上午9:51:57
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.mvc.mdlcomm;

import java.util.LinkedList;
import java.util.List;
/**  
 * 创建时间：2016年11月7日 上午9:51:57  
 * 项目名称：AutDataMotion   
 * 文件名称：MdlAircraft.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月7日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: MdlAircraft<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年11月7日
 */
public class MdlAircraft {

	public String name;
	public List<MdlSensor> sensors = new LinkedList<MdlSensor>();
	
	public MdlAircraft(){}
	
}
