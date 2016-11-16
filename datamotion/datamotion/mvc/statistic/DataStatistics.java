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
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import csuduc.platform.util.JsonUtils;
import datamotion.config.ConfMain;
import datamotion.mvc.mdlcomm.InfMdlCom;
import datamotion.mvc.mdlcomm.MdlClientCheckout;
import datamotion.mvc.mdlcomm.MdlClientDownLoad;
import datamotion.mvc.t11_initfoldertree.T11_initfoldertree;
import datamotion.mvc.t6_dwnloadfile.T6_dwnloadfile;
import datamotion.mvc.t7_backupfile.T7_backupfile;
import datamotion.mvc.t7_backupfile.T7_backupfileController;
import datamotion.mvc.t8_archivefile.T8_archivefile;
import datamotion.mvc.t9_checkoutfiles.T9_checkoutfiles;
import datamotion.mvc.t_kvalue.T_kvalue;

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
	//public static JSONObject jsonObject_datalevel = new JSONObject();//载荷的数据级别
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
	public void doInit() {//统计宽波段，高度计，紫外
		// 获得参数
		String D_status = getPara("v");
		if (null == D_status || D_status.isEmpty()) {
			renderText("-1");//错误
		}
		//log.debug(D_status);
		
		JSONObject js = doQueryInit(Integer.parseInt(D_status));
		String Jsondata=js.toString();
		renderJson(Jsondata);
	}
	// 查询
	@Clear
	public JSONObject doQueryInit(int status) {//统计宽波段，高度计，紫外
		log.debug(status);
		String tableName = null;
		
		JSONObject jsonObject = new JSONObject();
		
		JSONObject jsonObject_K = new JSONObject();//宽波段
		JSONObject jsonObject_G = new JSONObject();//高度计
		JSONObject jsonObject_Z = new JSONObject();//紫外邻边
		
		jsonObject_K.put("个数", 0);
		jsonObject_K.put("容量", 0);
		
		jsonObject_G.put("个数", 0);
		jsonObject_G.put("容量", 0);
		
		jsonObject_Z.put("个数", 0);
		jsonObject_Z.put("容量", 0);
		
		jsonObject.put("MWI", jsonObject_K);//宽波段
		jsonObject.put("IALT", jsonObject_G);//高度计
		jsonObject.put("ZW", jsonObject_Z);//紫外邻边
		
		if (status==10) {
			tableName="t6_dwnloadfile";
			String sql ="select sensor,count(*) as num,sum(filesize) as vol from "+
					tableName+" where sensor in ('MWI','IALT','ZW') group by sensor";
			log.debug(sql);
			// 数据库查询
			List<T6_dwnloadfile> res = T6_dwnloadfile.dao.find(sql);
			for(int i=0;i<res.size();i++)
			{
				String ZHname = res.get(i).get("sensor").toString();
				int ZHnum = (Integer.parseInt(res.get(i).get("num").toString()));
				int ZHvol = (Integer.parseInt(res.get(i).get("vol").toString()));
				
				JSONObject js= (JSONObject)jsonObject.get(ZHname);
				js.put("个数", ZHnum);
				js.put("容量", ZHvol);
			}

		}
		if (status==11) {
			tableName="t7_backupfile";
			String sql ="select sensor,count(*) as num,sum(filesize) as vol from "+
					tableName+" where sensor in ('MWI','IALT','ZW') group by sensor";
			log.debug(sql);
			// 数据库查询
			List<T7_backupfile> res = T7_backupfile.dao.find(sql);
			for(int i=0;i<res.size();i++)
			{
				String ZHname = res.get(i).get("sensor").toString();
				int ZHnum = (Integer.parseInt(res.get(i).get("num").toString()));
				int ZHvol = (Integer.parseInt(res.get(i).get("vol").toString()));
				
				JSONObject js= (JSONObject)jsonObject.get(ZHname);
				js.put("个数", ZHnum);
				js.put("容量", ZHvol);
			}
		}
		if (status==12) {
			tableName="t8_archivefile";
			String sql ="select sensor,count(*) as num,sum(filesize) as vol from "+
					tableName+" where sensor in ('MWI','IALT','ZW') group by sensor";
			log.debug(sql);
			// 数据库查询
			List<T8_archivefile> res = T8_archivefile.dao.find(sql);
			for(int i=0;i<res.size();i++)
			{
				String ZHname = res.get(i).get("sensor").toString();
				int ZHnum = (Integer.parseInt(res.get(i).get("num").toString()));
				int ZHvol = (Integer.parseInt(res.get(i).get("vol").toString()));
				
				JSONObject js= (JSONObject)jsonObject.get(ZHname);
				js.put("个数", ZHnum);
				js.put("容量", ZHvol);
			}
		}
		if (status==13) {
			tableName="t9_checkoutfiles";
			String sql ="select sensor,count(*) as num,sum(filesize) as vol from "+
					tableName+" where sensor in ('MWI','IALT','ZW') group by sensor";
			log.debug(sql);
			// 数据库查询
			List<T9_checkoutfiles> res = T9_checkoutfiles.dao.find(sql);
			for(int i=0;i<res.size();i++)
			{
				String ZHname = res.get(i).get("sensor").toString();
				int ZHnum = (Integer.parseInt(res.get(i).get("num").toString()));
				int ZHvol = (Integer.parseInt(res.get(i).get("vol").toString()));
				
				JSONObject js= (JSONObject)jsonObject.get(ZHname);
				js.put("个数", ZHnum);
				js.put("容量", ZHvol);
			}
		}
		return jsonObject;
	}
	@Clear
	public JSONObject InitProperties() {
		JSONObject jsonObject = new JSONObject();
		ArrayList<String> datalevel = new ArrayList<String>();
		datalevel.add("0B");datalevel.add("0C");datalevel.add("L0");datalevel.add("L1");
		datalevel.add("L2");datalevel.add("L3");datalevel.add("L4");
		datalevel.add("L5");
		jsonObject.put("datalevel", datalevel);
		//载荷数据级别对应的个数和容量初始化数组
		ArrayList<Integer> data_num_vol = new ArrayList<Integer>();
		data_num_vol.add(0);data_num_vol.add(0);data_num_vol.add(0);data_num_vol.add(0);
		data_num_vol.add(0);data_num_vol.add(0);data_num_vol.add(0);
		data_num_vol.add(0);
		JSONArray jsonArray = getZH_CH_EN();
		for(int i=0;i<jsonArray.length();i++)
		{
			JSONObject js=jsonArray.getJSONObject(i);
			js.put("datanum", data_num_vol);
			js.put("datavolume", data_num_vol);
		}
		
		jsonObject.put("载荷", jsonArray);
		
		return jsonObject;
	}
	@Clear
	public int findDatalevelIndex(JSONArray datalevel,String level) {
		
		log.debug("findDatalevelIndex "+datalevel.getString(0));
		for(int i=0;i<datalevel.length();i++)
		{
			if (datalevel.getString(i).equals(level)) {
				return i;
			}
		}
		return 10;
	}
	// 查询
	@Clear
	public void doQuery() {
		//JSONArray jsonArray = getZH_CH_EN();
		//log.debug(jsonArray.toString());
		
		// 获得参数,
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
		//log.debug(D_status);
		JSONObject js3 = doQueryInit(D_status);
		//log.debug(js.toString());
		
		JSONObject jsonObject =InitProperties();
		jsonObject.put("MWI", (JSONObject)js3.get("MWI"));
		jsonObject.put("IALT", (JSONObject)js3.get("IALT"));
		jsonObject.put("ZW", (JSONObject)js3.get("ZW"));
		
		//log.debug(jsonObject.toString());
		JSONArray levels =jsonObject.getJSONArray("datalevel");
		log.debug("levels"+levels.toString());
		String strSQL = null;
		JSONArray jsonArray_ZHs = jsonObject.getJSONArray("载荷");
		if (D_status ==10) {//统计下载
			for (int i = 0; i < jsonArray_ZHs.length(); i++) {
				JSONObject jsonObject_ZH=jsonArray_ZHs.getJSONObject(i);
				String ZH_EN=jsonObject_ZH.getString("EN");
				JSONArray datanum =jsonObject_ZH.getJSONArray("datanum");
				JSONArray datavolume =jsonObject_ZH.getJSONArray("datavolume");
				strSQL = mdlClient.getSQLStrDS("t6_dwnloadfile",ZH_EN);
				log.debug("数据库查询 "+strSQL);
				// 数据库查询
				List<T6_dwnloadfile> res = T6_dwnloadfile.dao.find(strSQL);
				log.debug(res.size());
				for(int j=0;j<res.size();j++)
				{
					String datalevel = res.get(j).get("datalevel").toString();
					log.debug("datalevel"+datalevel);
					int Index=findDatalevelIndex(levels, datalevel);
					log.debug(Index);
					if (Index<10) {
						int datalevel_num = (Integer.parseInt(res.get(j).get("num").toString()));
						int datalevel_vol = (Integer.parseInt(res.get(j).get("vol").toString()));
						
						datanum.put(Index, datalevel_num);
						datavolume.put(Index, datalevel_vol);

					}
					
				}
			}
			
			//JSONObject resjsonObject = assembleJsonFromData(mdlClient,jsonObject,levels);
			log.debug(jsonObject.toString());
		}
		else if (D_status == 11) {//统计备份
			//strSQL = mdlClient.getSQLStrDS("t7_backupfile");
			// 数据库查询
			//List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
			//getStatisticsdata(res);
			for (int i = 0; i < jsonArray_ZHs.length(); i++) {
				JSONObject jsonObject_ZH=jsonArray_ZHs.getJSONObject(i);
				String ZH_EN=jsonObject_ZH.getString("EN");
				JSONArray datanum =jsonObject_ZH.getJSONArray("datanum");
				JSONArray datavolume =jsonObject_ZH.getJSONArray("datavolume");
				
				strSQL = mdlClient.getSQLStrDS("t7_backupfile",ZH_EN);
				log.debug("数据库查询 "+strSQL);
				// 数据库查询
				List<T7_backupfile> res = T7_backupfile.dao.find(strSQL);
				log.debug(res.size());
				for(int j=0;j<res.size();j++)
				{
					String datalevel = res.get(j).get("datalevel").toString();
					log.debug("datalevel"+datalevel);
					int Index=findDatalevelIndex(levels, datalevel);
					log.debug(Index);
					if (Index<10) {
						int datalevel_num = (Integer.parseInt(res.get(j).get("num").toString()));
						int datalevel_vol = (Integer.parseInt(res.get(j).get("vol").toString()));
						
						datanum.put(Index, datalevel_num);
						datavolume.put(Index, datalevel_vol);

					}
					
				}
			}
			log.debug(jsonObject.toString());
		}
		else if (D_status == 12) {//统计归档
			//strSQL = mdlClient.getSQLStrDS("t8_archivefile");
			// 数据库查询
			//List<T8_archivefile> res = T8_archivefile.dao.find(strSQL);
			//getStatisticsdata(res);
			for (int i = 0; i < jsonArray_ZHs.length(); i++) {
				JSONObject jsonObject_ZH=jsonArray_ZHs.getJSONObject(i);
				String ZH_EN=jsonObject_ZH.getString("EN");
				JSONArray datanum =jsonObject_ZH.getJSONArray("datanum");
				JSONArray datavolume =jsonObject_ZH.getJSONArray("datavolume");
				
				strSQL = mdlClient.getSQLStrDS("t8_archivefile",ZH_EN);
				log.debug("数据库查询 "+strSQL);
				// 数据库查询
				List<T8_archivefile> res = T8_archivefile.dao.find(strSQL);
				log.debug(res.size());
				for(int j=0;j<res.size();j++)
				{
					String datalevel = res.get(j).get("datalevel").toString();
					log.debug("datalevel"+datalevel);
					int Index=findDatalevelIndex(levels, datalevel);
					log.debug(Index);
					if (Index<10) {
						int datalevel_num = (Integer.parseInt(res.get(j).get("num").toString()));
						int datalevel_vol = (Integer.parseInt(res.get(j).get("vol").toString()));
						
						datanum.put(Index, datalevel_num);
						datavolume.put(Index, datalevel_vol);

					}
					
				}
			}
			log.debug(jsonObject.toString());
			
		}
		else {//13 统计检出
			//strSQL = mdlClient.getSQLStrDS("t9_checkoutfiles");
			// 数据库查询
			//List<T9_checkoutfiles> res = T9_checkoutfiles.dao.find(strSQL);
			//getStatisticsdata(res);
			for (int i = 0; i < jsonArray_ZHs.length(); i++) {
				JSONObject jsonObject_ZH=jsonArray_ZHs.getJSONObject(i);
				String ZH_EN=jsonObject_ZH.getString("EN");
				JSONArray datanum =jsonObject_ZH.getJSONArray("datanum");
				JSONArray datavolume =jsonObject_ZH.getJSONArray("datavolume");
				
				strSQL = mdlClient.getSQLStrDS("t9_checkoutfiles",ZH_EN);
				log.debug("数据库查询 "+strSQL);
				// 数据库查询
				List<T9_checkoutfiles> res = T9_checkoutfiles.dao.find(strSQL);
				log.debug(res.size());
				for(int j=0;j<res.size();j++)
				{
					String datalevel = res.get(j).get("datalevel").toString();
					log.debug("datalevel"+datalevel);
					int Index=findDatalevelIndex(levels, datalevel);
					log.debug(Index);
					if (Index<10) {
						int datalevel_num = (Integer.parseInt(res.get(j).get("num").toString()));
						int datalevel_vol = (Integer.parseInt(res.get(j).get("vol").toString()));
						
						datanum.put(Index, datalevel_num);
						datavolume.put(Index, datalevel_vol);

					}
					
				}
			}
			log.debug(jsonObject.toString());
		}
		renderJson(jsonObject.toString());
		
	}
	@Clear
	public JSONObject assembleJsonFromData(MdlClientDownLoad mdlClient,JSONObject jsonObject,JSONArray levels)
	{
		String strSQL = null;
		JSONArray jsonArray_ZHs = jsonObject.getJSONArray("载荷");
		
		for (int i = 0; i < jsonArray_ZHs.length(); i++) {
			JSONObject jsonObject_ZH=jsonArray_ZHs.getJSONObject(i);
			String ZH_EN=jsonObject_ZH.getString("EN");
			JSONArray datanum =jsonObject_ZH.getJSONArray("datanum");
			JSONArray datavolume =jsonObject_ZH.getJSONArray("datavolume");
			
			strSQL = mdlClient.getSQLStrDS("t6_dwnloadfile",ZH_EN);
			log.debug("数据库查询 "+strSQL);
			// 数据库查询
			List<T6_dwnloadfile> res = T6_dwnloadfile.dao.find(strSQL);
			log.debug(res.size());
			for(int j=0;j<res.size();j++)
			{
				String datalevel = res.get(j).get("datalevel").toString();
				log.debug("datalevel"+datalevel);
				int Index=findDatalevelIndex(levels, datalevel);
				log.debug(Index);
				if (Index<10) {
					int datalevel_num = (Integer.parseInt(res.get(j).get("num").toString()));
					int datalevel_vol = (Integer.parseInt(res.get(j).get("vol").toString()));
					
					datanum.put(Index, datalevel_num);
					datavolume.put(Index, datalevel_vol);

				}
				
			}
		}
		return jsonObject;
	}
	@Clear
	public ArrayList<String> getZH_CH()//找出载荷的中文
	{
		Map<String, T11_initfoldertree> mapInitfoldertrees =ConfMain.mapInitfoldertrees;
		ArrayList<String> ZHS = new ArrayList<String>();
		for (Map.Entry<String, T11_initfoldertree> item : mapInitfoldertrees
				.entrySet()) {
			T11_initfoldertree i=item.getValue();
			if ((int)i.getLevel()==2) {
				ZHS.add((String)i.getNamechi());
			}
			//log.debug("getZH"+i.getLevel());
		}
		return ZHS;
	}
	@Clear
	public JSONArray getZH_CH_EN()//找出载荷的中－英文
	{
		JSONArray jsonArray = new JSONArray();
		
		Map<String, T_kvalue> mapKValues =ConfMain.mapKValues;
		ArrayList<String> CH=getZH_CH();
		
		for(int i=0;i<CH.size();i++)
		{
			JSONObject jsonObject = new JSONObject();
			
			String ch=CH.get(i);
			T_kvalue kvalue = mapKValues.get(ch);
			if (kvalue!=null) {
				String EN=(String)kvalue.getValue_();
				jsonObject.put("CH", ch);
				jsonObject.put("EN", EN);
				
				jsonArray.put(jsonObject);
			}
			
		}
		
		log.debug("getZH_CH_EN "+jsonArray.toString());
		
		return jsonArray;
	}
	@Clear
	public <M> String getStatisticsdata(List<M> res)
	{
		// 返回结果
		if (null == res || res.size() <= 0) {
			renderText("-1");
			return "-1";
		}else {

			InfMdlCom itemRes = null;
			ArrayList<String> ZHnames = new ArrayList<String>();//存储载荷name
			for(int i=0;i<res.size();i++)
			{
				itemRes = (InfMdlCom)res.get(i);
				String ZHname = itemRes.get("sensor").toString();
				if (ZHnames.contains(ZHname)) {
					
				};
			}
			 return "";
		}
		/*
		JSONObject jsonObject = new JSONObject();
		
		JSONObject jsonObject_K = new JSONObject();//宽波段
		JSONObject jsonObject_G = new JSONObject();//高度计
		JSONObject jsonObject_Z = new JSONObject();//紫外邻边
		
		int num_K=0;//宽波段-个数
		int num_G=0;//高度计-个数
		int num_Z=0;//紫外邻边-个数
		
		int vol_K=0;//宽波段-容量
		int vol_G=0;//高度计-容量
		int vol_Z=0;//紫外邻边-容量
		
		jsonObject_K.put("个数", num_K);
		jsonObject_K.put("容量", vol_K);
		
		jsonObject_G.put("个数", num_G);
		jsonObject_G.put("容量", vol_G);
		
		jsonObject_Z.put("个数", num_Z);
		jsonObject_Z.put("容量", vol_Z);
		
		jsonObject.put("宽波段", jsonObject_K);
		jsonObject.put("高度计", jsonObject_G);
		jsonObject.put("紫外", jsonObject_Z);
		
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0;i<res.size();i++)
		{
			JSONObject jsonObject_Zs = new JSONObject();//载荷
			
			jsonObject_Zs.put("name", value);//每个载荷的名字
			jsonObject_Zs.put("datalevel", value);//每个载荷的数据级别
			jsonObject_Zs.put("datavolume", value);//每个载荷的数据容量
			
			jsonArray.put(jsonObject_Zs);
			
		}
		jsonObject.put("载荷", jsonArray);
		
		
		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		InfMdlCom itemRes = null;
		for(int i=0;i<res.size();i++)
		{
			itemRes = (InfMdlCom)res.get(i);
			ArrayList<String> subdata = new ArrayList<String>();
			
			//subdata.add(res.get(i).getId().toString());
			subdata.add(itemRes.get("id").toString());
			subdata.add(itemRes.get("pathsrc").toString());
			subdata.add(itemRes.get("namesrc").toString());
			subdata.add(itemRes.get("pathdest").toString());
			subdata.add(itemRes.get("timedo").toString());
			subdata.add(itemRes.get("filesize").toString());
			subdata.add(itemRes.get("station").toString());
			subdata.add(itemRes.get("aircraft").toString());
			subdata.add(itemRes.get("sensor").toString());
			subdata.add(itemRes.get("datatype").toString());
			subdata.add(itemRes.get("datalevel").toString());
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add("");
			subdata.add(itemRes.get("status_").toString());
			subdata.add("");
			subdata.add("buttons");
			//subdata.add(sublist.get(0).get("labelids").toString());
			data.add(subdata);
		}
		jsonObject.put("data", data);
		
		return jsonObject.toString();
		*/
	}
}
