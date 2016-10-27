package datamotion.mvc.t11_initfoldertree;

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
//@Table(tableName = "t11_initfoldertree")
public class T11_initfoldertree extends BaseModel<T11_initfoldertree> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T11_initfoldertree.class);
	
	public static final T11_initfoldertree dao = new T11_initfoldertree();
	
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
	 * 字段描述：英文缩写 
	 * 字段类型：character varying  长度：128
	 */
	public static final String column_nameeng = "nameeng";
	
	/**
	 * 字段描述：中文名称 
	 * 字段类型：character varying  长度：128
	 */
	public static final String column_namechi = "namechi";
	
	/**
	 * 字段描述：树级别，root为0 
	 * 字段类型：integer  长度：null
	 */
	public static final String column_level = "level";
	
	/**
	 * 字段描述：父节点keys 
	 * 字段类型：text  长度：null
	 */
	public static final String column_parentkeys = "parentkeys";
	
	/**
	 * 字段描述：子节点keys 
	 * 字段类型：text  长度：null
	 */
	public static final String column_childkeys = "childkeys";
	
	/**
	 * 字段描述：下载备份归档检出配置key 
	 * 字段类型：character varying  长度：128
	 */
	public static final String column_fkeyinitmodule = "fkeyinitmodule";
	
	/**
	 * 字段描述：扩展 
	 * 字段类型：text  长度：null
	 */
	public static final String column_extend1 = "extend1";
	
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
	 * sqlId : datamotion.t11_initfoldertree.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "datamotion.t11_initfoldertree.splitPageFrom";

	private Integer id;
	private String key_;
	private String nameeng;
	private String namechi;
	private Integer level;
	private String parentkeys;
	private String childkeys;
	private String fkeyinitmodule;
	private String extend1;
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
	public void setNameeng(String nameeng){
		set(column_nameeng, nameeng);
	}
	public <T> T getNameeng() {
		return get(column_nameeng);
	}
	public void setNamechi(String namechi){
		set(column_namechi, namechi);
	}
	public <T> T getNamechi() {
		return get(column_namechi);
	}
	public void setLevel(Integer level){
		set(column_level, level);
	}
	public <T> T getLevel() {
		return get(column_level);
	}
	public void setParentkeys(String parentkeys){
		set(column_parentkeys, parentkeys);
	}
	public <T> T getParentkeys() {
		return get(column_parentkeys);
	}
	public void setChildkeys(String childkeys){
		set(column_childkeys, childkeys);
	}
	public <T> T getChildkeys() {
		return get(column_childkeys);
	}
	public void setFkeyinitmodule(String fkeyinitmodule){
		set(column_fkeyinitmodule, fkeyinitmodule);
	}
	public <T> T getFkeyinitmodule() {
		return get(column_fkeyinitmodule);
	}
	public void setExtend1(String extend1){
		set(column_extend1, extend1);
	}
	public <T> T getExtend1() {
		return get(column_extend1);
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
