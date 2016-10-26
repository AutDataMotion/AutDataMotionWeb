package datamotion.mvc.t7_backupfile;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T7_backupfileService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T7_backupfileService.class);
	
	public static final T7_backupfileService service = Enhancer.enhance(T7_backupfileService.class);
	
	public T7_backupfile SelectById(Integer id){
		
		T7_backupfile mdl = T7_backupfile.dao.findFirst("select * from t7_backupfile where id=?", id);
		return mdl;
	}
}
