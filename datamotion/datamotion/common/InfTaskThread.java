/**
 * <p>title:InfTaskThread.java<／p>
 * <p>Description: <／p>
 * @date:2016年10月30日下午7:08:03
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.common;
/**  
 * 创建时间：2016年10月30日 下午7:08:03  
 * 项目名称：AutDataMotion   
 * 文件名称：InfTaskThread.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年10月30日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: InfTaskThread<／p>
 * <p>Description: <／p>
 * 
 * 
 * @author ZhongwengHao
 * @date 2016年10月30日
 */
public interface InfTaskThread {

	boolean init();
	
	boolean start();
	
	boolean stop();
	
	boolean restart();
	
	<F> boolean addWork(F amdl);
	<F> boolean doWork(F amdl);
}
