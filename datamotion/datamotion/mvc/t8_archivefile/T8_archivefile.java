package datamotion.mvc.t8_archivefile;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import datamotion.mvc.mdlcomm.InfMdlCom;

import java.sql.Timestamp; 

import org.apache.log4j.Logger;

/**
 * 自增id model
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "t8_archivefile")
public class T8_archivefile extends BaseModel<T8_archivefile> implements InfMdlCom{

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T8_archivefile.class);
	
	public static final T8_archivefile dao = new T8_archivefile();
	
	/**
	 * 字段描述：自增id 
	 * 字段类型：integer  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：随机码 
	 * 字段类型：character varying  长度：128
	 */
	public static final String column_key_ = "key_";
	
	/**
	 * 字段描述：源文件路径 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_pathsrc = "pathsrc";
	
	/**
	 * 字段描述：备份文件名称 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_namesrc = "namesrc";
	
	/**
	 * 字段描述：备份文件路径 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_pathdest = "pathdest";
	
	/**
	 * 字段描述：目的名称 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_namedest = "namedest";
	
	/**
	 * 字段描述：备份时间 
	 * 字段类型：timestamp without time zone  长度：null
	 */
	public static final String column_timedo = "timedo";
	
	/**
	 * 字段描述：文件大小 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_filesize = "filesize";
	
	/**
	 * 字段描述：接收站名称 
	 * 字段类型：character varying  长度：4
	 */
	public static final String column_station = "station";
	
	/**
	 * 字段描述：飞行器 
	 * 字段类型：character varying  长度：4
	 */
	public static final String column_aircraft = "aircraft";
	
	/**
	 * 字段描述：载荷名称 
	 * 字段类型：character varying  长度：16
	 */
	public static final String column_sensor = "sensor";
	
	/**
	 * 字段描述：数据类型 
	 * 字段类型：character varying  长度：16
	 */
	public static final String column_datatype = "datatype";
	
	/**
	 * 字段描述：数据级别 
	 * 字段类型：character varying  长度：16
	 */
	public static final String column_datalevel = "datalevel";
	
	/**
	 * 字段描述：产品标识 
	 * 字段类型：character varying  长度：16
	 */
	public static final String column_camera = "camera";
	
	/**
	 * 字段描述：下传时间 
	 * 字段类型：timestamp without time zone  长度：null
	 */
	public static final String column_timerecive = "timerecive";
	
	/**
	 * 字段描述：拍摄开始时间 
	 * 字段类型：timestamp without time zone  长度：null
	 */
	public static final String column_timecollectstart = "timecollectstart";
	
	/**
	 * 字段描述：拍摄结束时间 
	 * 字段类型：timestamp without time zone  长度：null
	 */
	public static final String column_timecollectend = "timecollectend";
	
	/**
	 * 字段描述：后缀名 
	 * 字段类型：character varying  长度：8
	 */
	public static final String column_suffix = "suffix";
	
	/**
	 * 字段描述：是否主文件 
	 * 字段类型：boolean  长度：null
	 */
	public static final String column_ismain = "ismain";
	
	/**
	 * 字段描述：辅助文件keys 
	 * 字段类型：character varying  长度：512
	 */
	public static final String column_auxkeys = "auxkeys";
	
	/**
	 * 字段描述：处理次数 
	 * 字段类型：integer  长度：null
	 */
	public static final String column_cntdo = "cntdo";
	
	/**
	 * 字段描述：状态 
	 * 字段类型：integer  长度：null
	 */
	public static final String column_status_ = "status_";
	
	/**
	 * 字段描述：添加时间 
	 * 字段类型：timestamp without time zone  长度：null
	 */
	public static final String column_timeadd = "timeadd";
	
	/**
	 * 字段描述：标签id串 
	 * 字段类型：text  长度：null
	 */
	public static final String column_labelids = "labelids";
	
	
	/**
	 * sqlId : datamotion.t8_archivefile.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "datamotion.t8_archivefile.splitPageFrom";

	private Integer id;
	private String key_;
	private String pathsrc;
	private String namesrc;
	private String pathdest;
	private String namedest;
	private Timestamp timedo;
	private Long filesize;
	private String station;
	private String aircraft;
	private String sensor;
	private String datatype;
	private String datalevel;
	private String camera;
	private Timestamp timerecive;
	private Timestamp timecollectstart;
	private Timestamp timecollectend;
	private String suffix;
	private Boolean ismain;
	private String auxkeys;
	private Integer cntdo;
	private Integer status_;
	private Timestamp timeadd;
	private String labelids;

	public void setId(Integer id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setKey_(String key_){
		set(column_key_, key_);
	}
	public <T> T getKey_() {
		return get(column_key_);
	}
	public void setPathsrc(String pathsrc){
		set(column_pathsrc, pathsrc);
	}
	public <T> T getPathsrc() {
		return get(column_pathsrc);
	}
	public void setNamesrc(String namesrc){
		set(column_namesrc, namesrc);
	}
	public <T> T getNamesrc() {
		return get(column_namesrc);
	}
	public void setPathdest(String pathdest){
		set(column_pathdest, pathdest);
	}
	public <T> T getPathdest() {
		return get(column_pathdest);
	}
	public void setNamedest(String namedest){
		set(column_namedest, namedest);
	}
	public <T> T getNamedest() {
		return get(column_namedest);
	}
	public void setTimedo(Timestamp timedo){
		set(column_timedo, timedo);
	}
	public <T> T getTimedo() {
		return get(column_timedo);
	}
	public void setFilesize(Long filesize){
		set(column_filesize, filesize);
	}
	public <T> T getFilesize() {
		return get(column_filesize);
	}
	public void setStation(String station){
		set(column_station, station);
	}
	public <T> T getStation() {
		return get(column_station);
	}
	public void setAircraft(String aircraft){
		set(column_aircraft, aircraft);
	}
	public <T> T getAircraft() {
		return get(column_aircraft);
	}
	public void setSensor(String sensor){
		set(column_sensor, sensor);
	}
	public <T> T getSensor() {
		return get(column_sensor);
	}
	public void setDatatype(String datatype){
		set(column_datatype, datatype);
	}
	public <T> T getDatatype() {
		return get(column_datatype);
	}
	public void setDatalevel(String datalevel){
		set(column_datalevel, datalevel);
	}
	public <T> T getDatalevel() {
		return get(column_datalevel);
	}
	public void setCamera(String camera){
		set(column_camera, camera);
	}
	public <T> T getCamera() {
		return get(column_camera);
	}
	public void setTimerecive(Timestamp timerecive){
		set(column_timerecive, timerecive);
	}
	public <T> T getTimerecive() {
		return get(column_timerecive);
	}
	public void setTimecollectstart(Timestamp timecollectstart){
		set(column_timecollectstart, timecollectstart);
	}
	public <T> T getTimecollectstart() {
		return get(column_timecollectstart);
	}
	public void setTimecollectend(Timestamp timecollectend){
		set(column_timecollectend, timecollectend);
	}
	public <T> T getTimecollectend() {
		return get(column_timecollectend);
	}
	public void setSuffix(String suffix){
		set(column_suffix, suffix);
	}
	public <T> T getSuffix() {
		return get(column_suffix);
	}
	public void setIsmain(Boolean ismain){
		set(column_ismain, ismain);
	}
	public <T> T getIsmain() {
		return get(column_ismain);
	}
	public void setAuxkeys(String auxkeys){
		set(column_auxkeys, auxkeys);
	}
	public <T> T getAuxkeys() {
		return get(column_auxkeys);
	}
	public void setCntdo(Integer cntdo){
		set(column_cntdo, cntdo);
	}
	public <T> T getCntdo() {
		return get(column_cntdo);
	}
	public void setStatus_(Integer status_){
		set(column_status_, status_);
	}
	public <T> T getStatus_() {
		return get(column_status_);
	}
	public void setTimeadd(Timestamp timeadd){
		set(column_timeadd, timeadd);
	}
	public <T> T getTimeadd() {
		return get(column_timeadd);
	}
	public void setLabelids(String labelids){
		set(column_labelids, labelids);
	}
	public <T> T getLabelids() {
		return get(column_labelids);
	}
	
}
