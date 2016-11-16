/**
 * <p>title:MdlFileEvent.java<／p>
 * <p>Description: <／p>
 * @date:2016年10月31日下午8:38:26
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import csuduc.platform.util.ComUtil;
import csuduc.platform.util.StringUtil;
import datamotion.constant.StatusMy;
import datamotion.ftpwatch.WatchFilesFtp;

/**  
 * 创建时间：2016年10月31日 下午8:38:26  
 * 项目名称：AutDataMotion   
 * 文件名称：MdlFileEvent.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年10月31日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: MdlFileEvent<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年10月31日
 */
public class MdlFileEvent implements Serializable {
	private static Logger log = Logger.getLogger(MdlFileEvent.class);
	/**
	 * serialVersionUID
	 */
	public transient static final long serialVersionUID = 1234234234L;

	public transient static String dateFormat = "yyyyMMddHHmmss";
	public transient static char split = '_';

	public enum NAMETOKE {
		PLAT, AIRCRAFT, SENSOR, CAMERA, TYPE, DATE1, DATE2, DATE3, NUM, LEVEL
	};

	// NUM圈数

	public Integer id;
	public String key_;
	public String pathsrc;
	public String namesrc;
	public String pathdest;
	public String namedest;
	public Timestamp timedo;
	public Long filesize;

	public String station;
	public String aircraft;
	public String sensor;
	public String datatype;
	public String datalevel;
	public String camera;
	public Timestamp timerecive;
	public Timestamp timecollectstart;
	public Timestamp timecollectend;
	public String suffix;
	
	public Boolean ismain;
	public String auxkeys;
	public Integer cntdo;
	public Integer status_;

	//扫描时的文件状态 0 未就绪  1就绪  2 已分发
	
	public int flagWatch;
	//
	// 文件名解析token
	public List<String> nameTokens;
	private boolean isInit = false;

	//文件处理的属性
	public StatusMy flowStatus;
	public transient MdlTreeProperty property;
	
	public MdlFileEvent(){}
	
	public MdlFileEvent(String aPathsrc, String aNamesrc) {
		pathsrc = aPathsrc;
		namesrc = aNamesrc;
	}

	public MdlFileEvent(String aPathsrc, String aNamesrc, String aPathdest,
			String aNamedest) {
		this(aPathsrc, aNamesrc);
		pathdest = aPathdest;
		namedest = aNamedest;
	}

	public boolean initProperties(){
		if (null == property) {
			return false;
		}
		
		return initProperties(property);
	}
	
	/**
	 * <p>
	 * Title: initProperties<／p>
	 * <p>
	 * Description: 解析文件名，初始化文件属性 <／p>
	 * 
	 * @return
	 */
	public boolean initProperties(MdlTreeProperty aProperty) {
		
		if (ComUtil.isEmptyStr(namesrc)) {
			return false;
		}
		nameTokens = StringUtil.split(namesrc, split);
		return initProperties(nameTokens, aProperty);
	}

	public boolean initProperties(List<String> aNameTokens, MdlTreeProperty aProperty) {
		if (ComUtil.isEmptyList(aNameTokens)) {
			log.debug("ComUtil.isEmptyList(aaNameToken)");
			return false;
		}
		if (aNameTokens.size() < 10) {
			log.debug("CaNameTokens.size < 10");
			return false;
		}
		nameTokens = aNameTokens;
		// 根据文件名格式进行属性赋值
		// 枚举类型：PLAT, AIRCRAFT, SENSOR, CAMERA, TYPE, DATE1, DATE2, DATE3, NUM,
		// LEVEL
		try {
			this.station = nameTokens.get(NAMETOKE.PLAT.ordinal());
			this.aircraft = nameTokens.get(NAMETOKE.AIRCRAFT.ordinal());
			this.sensor = nameTokens.get(NAMETOKE.SENSOR.ordinal());
			this.camera = nameTokens.get(NAMETOKE.CAMERA.ordinal());
			this.datatype = nameTokens.get(NAMETOKE.TYPE.ordinal());
			// 拆分级别和后缀名
			String last = nameTokens.get(NAMETOKE.LEVEL.ordinal());
			String[] lasts = last.split("\\.");
			this.datalevel = lasts[0];
			this.suffix = lasts[1];
			this.timerecive = StringUtil.strToTimeStamp(
					nameTokens.get(NAMETOKE.DATE1.ordinal()), dateFormat);
			this.timecollectstart = StringUtil.strToTimeStamp(
					nameTokens.get(NAMETOKE.DATE2.ordinal()), dateFormat);
			this.timecollectend = StringUtil.strToTimeStamp(
					nameTokens.get(NAMETOKE.DATE3.ordinal()), dateFormat);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("error:init Properties");
			return false;
		}
		property = aProperty;
		return true;
	}

	public synchronized static String getFileNameFilterStr(

			List<String> aFileNameTokens) {
		if (ComUtil.isEmptyList(aFileNameTokens)) {
			log.debug("ComUtil.isEmptyList(aFileNameTokens)");
			return null;
		}
		if (aFileNameTokens.size() < 10) {
			log.debug("aFileNameTokens.size < 10");
			return null;
		}
		// 这个需要修改
		return String.format("_%s_%s_%s_%s",
				aFileNameTokens.get(NAMETOKE.AIRCRAFT.ordinal()),
				aFileNameTokens.get(NAMETOKE.SENSOR.ordinal()),
				aFileNameTokens.get(NAMETOKE.TYPE.ordinal()),
				aFileNameTokens.get(NAMETOKE.CAMERA.ordinal()));
	}

	public boolean changeFileEvent(String aPathSrc, String aNameSrc, StatusMy aStatus){
		//更改路径 文件名
		pathsrc = aPathSrc;
		namesrc = aNameSrc;
		
		switch (aStatus) {
		case FLOW_DOWNLAD:
			pathdest = property.property.getPathdwnload();
			namedest = namesrc;
			break;
		case FLOW_BACKUP:
			pathdest = property.property.getPathbackup();
			namedest = namesrc;
			break;
		case FLOW_ARCHIVE:
			pathdest = property.property.getPatharchive();
			namedest = namesrc;
			break;
		case FLOW_CHECKOUT:
			pathdest = property.property.getPathcheckout();
			//修改文件名
			namedest = namesrc;
			break;
		default:
			return false;
		}
		
		return true;
	}
}
