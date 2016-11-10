package datamotion.mvc.t_kvalue;

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
//@Table(tableName = "t_kvalue")
public class T_kvalue extends BaseModel<T_kvalue> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T_kvalue.class);
	
	public static final T_kvalue dao = new T_kvalue();
	
	/**
	 * 字段描述：自增id 
	 * 字段类型：integer  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：key 
	 * 字段类型：character varying  长度：128
	 */
	public static final String column_key_ = "key_";
	
	/**
	 * 字段描述：值 
	 * 字段类型：character varying  长度：null
	 */
	public static final String column_value_ = "value_";
	
	/**
	 * 字段描述：信息 
	 * 字段类型：character varying  长度：null
	 */
	public static final String column_info = "info";
	
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
	 * sqlId : datamotion.t_kvalue.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "datamotion.t_kvalue.splitPageFrom";

	private Integer id;
	private String key_;
	private String value_;
	private String info;
	private Integer status_;
	private Timestamp timeadd;

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
	public void setValue_(String value_){
		set(column_value_, value_);
	}
	public <T> T getValue_() {
		return get(column_value_);
	}
	public void setInfo(String info){
		set(column_info, info);
	}
	public <T> T getInfo() {
		return get(column_info);
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
	
}
