/**
 * <p>title:DataStatistics.java<／p>
 * <p>Description: <／p>
 * @date:2016年11月11日下午5:25:27
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.mvc.statistic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.JsonUtils;
import datamotion.mvc.mdlcomm.MdlClientCheckout;
import datamotion.mvc.mdlcomm.MdlClientDownLoad;
import datamotion.mvc.t7_backupfile.T7_backupfile;
import datamotion.mvc.t7_backupfile.T7_backupfileController;

/**  
 * 创建时间：2016年11月11日 下午5:25:27  
 * 项目名称：AutDataMotion   
 * 文件名称：DataStatistics.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年11月11日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: DataStatistics<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年11月11日
 */
public class DataStatistics extends BaseController{
	private static Logger log = Logger.getLogger(DataStatistics.class);
	public static final String pthv10 = "/datamotion/t10_datastatistics/";
	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @see com.platform.mvc.base.BaseController#setViewPath()
	 */
	@Override
	protected void setViewPath() {
		// TODO Auto-generated method stub
		
	}
	@Clear
	public void datastatistics()
	{
		
		renderWithPath(pthv10+"datastatistics.html");
	}
	// 查询
	@Clear
	public void doQuery() {
		
		// 获得参数
				String strvalue = getPara("v");
				if (null == strvalue || strvalue.isEmpty()) {
					renderText("-1");//错误
				}
				log.debug(strvalue);
				MdlClientDownLoad mdlClient = null;
				try {
					mdlClient = JsonUtils.deserialize(strvalue, MdlClientDownLoad.class);
					if (null == mdlClient) {
		renderText("-1");//错误
						return;
					}
					log.debug(JsonUtils.serialize(mdlClient));
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					renderText("-1");//错误
					return;
				}
				int D_status = mdlClient.getStatus();
				log.debug(D_status);
				if (D_status ==10) {//统计下载
					
				}
				else if (D_status == 11) {//统计备份
					
				}
				else if (D_status == 12) {//统计归档
					
				}
				else {//统计检出
					
				}
				// 遍历树结构，拼接SQL语句
				String strSQL = mdlClient.getSQLStr("t7_backupfile");
				// 数据库查询
				List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
				
				log.debug(strSQL);
				// 返回结果
				if (null == res || res.size() <= 0) {
					renderText("-1");
					return;
				}else {
					String Jsondata = getPagedata(res);
					 renderJson(Jsondata);
					 return ;
				}
	}
	@Clear
	public String getPagedata(List<T7_backupfile> list)
	{

		JSONObject jsonObject = new JSONObject();
		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		for(int i=0;i<list.size();i++)
		{
			ArrayList<String> subdata = new ArrayList<String>();
			subdata.add(list.get(i).get("id").toString());
			subdata.add(list.get(i).get("pathsrc").toString());
			subdata.add(list.get(i).get("namesrc").toString());
			subdata.add(list.get(i).get("pathdest").toString());
			subdata.add(list.get(i).get("timedo").toString());
			subdata.add(list.get(i).get("filesize").toString());
			subdata.add(list.get(i).get("station").toString());
			subdata.add(list.get(i).get("aircraft").toString());
			subdata.add(list.get(i).get("sensor").toString());
			subdata.add(list.get(i).get("datatype").toString());
			subdata.add(list.get(i).get("datalevel").toString());
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add(list.get(i).get("status_").toString());
			subdata.add("");
			subdata.add("buttons");
			//subdata.add(sublist.get(0).get("labelids").toString());
			data.add(subdata);
		}
		jsonObject.put("data", data);
		
		return jsonObject.toString();
	}
}
