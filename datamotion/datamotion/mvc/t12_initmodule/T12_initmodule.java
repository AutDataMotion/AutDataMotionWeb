package datamotion.mvc.t12_initmodule;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 

import org.apache.log4j.Logger;

/**
 * 自增id model
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "t12_initmodule")
public class T12_initmodule extends BaseModel<T12_initmodule> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T12_initmodule.class);
	
	public static final T12_initmodule dao = new T12_initmodule();
	
	/**
	 * 字段描述：自增id 
	 * 字段类型：integer  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：唯一key 
	 * 字段类型：character varying  长度：128
	 */
	public static final String column_key_ = "key_";
	
	/**
	 * 字段描述：配置节点keys串 
	 * 字段类型：text  长度：null
	 */
	public static final String column_fkeystreenode = "fkeystreenode";
	
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
	 * 字段描述：ftp 地址ip:port 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_ftpaddr = "ftpaddr";
	
	/**
	 * 字段描述：ftp用户名 
	 * 字段类型：character varying  长度：32
	 */
	public static final String column_ftpusername = "ftpusername";
	
	/**
	 * 字段描述：ftp密码 
	 * 字段类型：character varying  长度：128
	 */
	public static final String column_ftpuserpwd = "ftpuserpwd";
	
	/**
	 * 字段描述：ftp路径 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_pathftp = "pathftp";
	
	/**
	 * 字段描述：是否自动下载 
	 * 字段类型：boolean  长度：null
	 */
	public static final String column_isdwnload = "isdwnload";
	
	/**
	 * 字段描述：下载路径 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_pathdwnload = "pathdwnload";
	
	/**
	 * 字段描述：是否自动备份 
	 * 字段类型：boolean  长度：null
	 */
	public static final String column_isbackup = "isbackup";
	
	/**
	 * 字段描述：备份路径 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_pathbackup = "pathbackup";
	
	/**
	 * 字段描述：是否自动归档 
	 * 字段类型：boolean  长度：null
	 */
	public static final String column_isarchive = "isarchive";
	
	/**
	 * 字段描述：归档路径 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_patharchive = "patharchive";
	
	/**
	 * 字段描述：是否自动检出 
	 * 字段类型：boolean  长度：null
	 */
	public static final String column_ischeckout = "ischeckout";
	
	/**
	 * 字段描述：源名称模式 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_namemdlsrc = "namemdlsrc";
	
	/**
	 * 字段描述：检出路径 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_pathcheckout = "pathcheckout";
	
	/**
	 * 字段描述：检出名称模式 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_namemdldes = "namemdldes";
	
	/**
	 * 字段描述：是否有辅助文件 
	 * 字段类型：boolean  长度：null
	 */
	public static final String column_ishavaaux = "ishavaaux";
	
	/**
	 * 字段描述：辅助文件类型 
	 * 字段类型：character varying  长度：256
	 */
	public static final String column_auxfiletypes = "auxfiletypes";
	
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
	 * 字段描述：更新时间 
	 * 字段类型：timestamp without time zone  长度：null
	 */
	public static final String column_timeupdate = "timeupdate";
	
	/**
	 * 字段描述：标签id串 
	 * 字段类型：text  长度：null
	 */
	public static final String column_labelids = "labelids";
	
	
	/**
	 * sqlId : datamotion.t12_initmodule.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "datamotion.t12_initmodule.splitPageFrom";

	private Integer id;
	private String key_;
	private String fkeystreenode;
	private String station;
	private String aircraft;
	private String sensor;
	private String datatype;
	private String datalevel;
	private String camera;
	private String ftpaddr;
	private String ftpusername;
	private String ftpuserpwd;
	private String pathftp;
	private Boolean isdwnload;
	private String pathdwnload;
	private Boolean isbackup;
	private String pathbackup;
	private Boolean isarchive;
	private String patharchive;
	private Boolean ischeckout;
	private String namemdlsrc;
	private String pathcheckout;
	private String namemdldes;
	private Boolean ishavaaux;
	private String auxfiletypes;
	private Integer status_;
	private Timestamp timeadd;
	private Timestamp timeupdate;
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
	public void setFkeystreenode(String fkeystreenode){
		set(column_fkeystreenode, fkeystreenode);
	}
	public <T> T getFkeystreenode() {
		return get(column_fkeystreenode);
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
	public void setFtpaddr(String ftpaddr){
		set(column_ftpaddr, ftpaddr);
	}
	public <T> T getFtpaddr() {
		return get(column_ftpaddr);
	}
	public void setFtpusername(String ftpusername){
		set(column_ftpusername, ftpusername);
	}
	public <T> T getFtpusername() {
		return get(column_ftpusername);
	}
	public void setFtpuserpwd(String ftpuserpwd){
		set(column_ftpuserpwd, ftpuserpwd);
	}
	public <T> T getFtpuserpwd() {
		return get(column_ftpuserpwd);
	}
	public void setPathftp(String pathftp){
		set(column_pathftp, pathftp);
	}
	public <T> T getPathftp() {
		return get(column_pathftp);
	}
	public void setIsdwnload(Boolean isdwnload){
		set(column_isdwnload, isdwnload);
	}
	public <T> T getIsdwnload() {
		return get(column_isdwnload);
	}
	public void setPathdwnload(String pathdwnload){
		set(column_pathdwnload, pathdwnload);
	}
	public <T> T getPathdwnload() {
		return get(column_pathdwnload);
	}
	public void setIsbackup(Boolean isbackup){
		set(column_isbackup, isbackup);
	}
	public <T> T getIsbackup() {
		return get(column_isbackup);
	}
	public void setPathbackup(String pathbackup){
		set(column_pathbackup, pathbackup);
	}
	public <T> T getPathbackup() {
		return get(column_pathbackup);
	}
	public void setIsarchive(Boolean isarchive){
		set(column_isarchive, isarchive);
	}
	public <T> T getIsarchive() {
		return get(column_isarchive);
	}
	public void setPatharchive(String patharchive){
		set(column_patharchive, patharchive);
	}
	public <T> T getPatharchive() {
		return get(column_patharchive);
	}
	public void setIscheckout(Boolean ischeckout){
		set(column_ischeckout, ischeckout);
	}
	public <T> T getIscheckout() {
		return get(column_ischeckout);
	}
	public void setNamemdlsrc(String namemdlsrc){
		set(column_namemdlsrc, namemdlsrc);
	}
	public <T> T getNamemdlsrc() {
		return get(column_namemdlsrc);
	}
	public void setPathcheckout(String pathcheckout){
		set(column_pathcheckout, pathcheckout);
	}
	public <T> T getPathcheckout() {
		return get(column_pathcheckout);
	}
	public void setNamemdldes(String namemdldes){
		set(column_namemdldes, namemdldes);
	}
	public <T> T getNamemdldes() {
		return get(column_namemdldes);
	}
	public void setIshavaaux(Boolean ishavaaux){
		set(column_ishavaaux, ishavaaux);
	}
	public <T> T getIshavaaux() {
		return get(column_ishavaaux);
	}
	public void setAuxfiletypes(String auxfiletypes){
		set(column_auxfiletypes, auxfiletypes);
	}
	public <T> T getAuxfiletypes() {
		return get(column_auxfiletypes);
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
	public void setTimeupdate(Timestamp timeupdate){
		set(column_timeupdate, timeupdate);
	}
	public <T> T getTimeupdate() {
		return get(column_timeupdate);
	}
	public void setLabelids(String labelids){
		set(column_labelids, labelids);
	}
	public <T> T getLabelids() {
		return get(column_labelids);
	}
	
}
