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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.jetty.util.log.Log;
import org.hamcrest.core.Is;

import sun.util.logging.resources.logging;
import csuduc.platform.util.StringUtil;

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

	/**
	 * serialVersionUID
	 */
	public transient static final long serialVersionUID = 1234234234L;

	public transient static String dateFormat = "yyyyMMddHHmmss";
	public transient static char split = '_';

	public enum NAMETOKE {
		PLAT, AIRCRAFT, SENSOR, CAMERA, TYPE, DATE1, DATE2, DATE3, NUM, LEVEL
	};
	//NUM圈数

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
	
	//
	// 文件名解析token
	public List<String> nameTokens;
	private boolean isInit = false;
	
	public MdlFileEvent() {
	}
	
	public MdlFileEvent(String aPathsrc, String aNamesrc) {
		this();
		pathsrc = aPathsrc;
		namesrc = aNamesrc;
	}
	
	public MdlFileEvent(String aPathsrc, String aNamesrc, String aPathdest, String aNamedest) {
		this(aPathsrc, aNamesrc);
		pathdest = aPathdest;
		namedest = aNamedest;
	}
	/**
	 * <p>Title: initProperties<／p>
	 * <p>Description:
	 * 解析文件名，初始化文件属性
	 *  <／p>
	 * @return
	 */
	public boolean initProperties(){
		if (nameTokens != null) {
			return true;
		}
		nameTokens = StringUtil.split(namesrc, split); 
		if (nameTokens == null || nameTokens.size()==0) {
			nameTokens = null;
			return false;
		}
		//根据文件名格式进行属性赋值
		//枚举类型：PLAT, AIRCRAFT, SENSOR, CAMERA, TYPE, DATE1, DATE2, DATE3, NUM, LEVEL
		this.station = nameTokens.get(NAMETOKE.PLAT.ordinal());
		this.aircraft = nameTokens.get(NAMETOKE.AIRCRAFT.ordinal());
		this.sensor = nameTokens.get(NAMETOKE.SENSOR.ordinal());
		this.camera = nameTokens.get(NAMETOKE.CAMERA.ordinal());
		this.datatype = nameTokens.get(NAMETOKE.TYPE.ordinal());
		//拆分级别和后缀名
		String last = nameTokens.get(NAMETOKE.LEVEL.ordinal());
		String[] lasts = last.split("\\.");
		this.datalevel = lasts[0];
		this.suffix = lasts[1];
		this.timerecive = StringUtil.strToTimeStamp(nameTokens.get(NAMETOKE.DATE1.ordinal()), dateFormat);
		this.timecollectstart = StringUtil.strToTimeStamp(nameTokens.get(NAMETOKE.DATE2.ordinal()), dateFormat);
		this.timecollectend = StringUtil.strToTimeStamp(nameTokens.get(NAMETOKE.DATE3.ordinal()), dateFormat);
		return true;
	}
}
