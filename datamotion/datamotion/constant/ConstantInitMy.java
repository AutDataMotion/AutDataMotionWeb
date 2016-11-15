package datamotion.constant;

import com.platform.constant.ConstantInit;

/**  
 * 创建时间：2016年6月16日 下午9:19:14  
 * 项目名称：DUCPlatFormWeb   
 * 文件名称：ConstantInitMy.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年6月16日     Zhongweng       1.0         1.0 Version   
 */

public interface ConstantInitMy extends ConstantInit{
	public static final String db_dataSource_main = "db.dataSource.datamotion";
	final static int timeSpanSecond = 1000;// 1分钟
	final static int timeSpanMinute = timeSpanSecond * 60;// 1分钟
	final static int timeSpanHoure = timeSpanMinute * 60;// 1小时
	final static long TIME_BEG = System.currentTimeMillis();
}
