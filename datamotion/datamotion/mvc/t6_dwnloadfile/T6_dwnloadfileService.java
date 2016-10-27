package datamotion.mvc.t6_dwnloadfile;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T6_dwnloadfileService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6_dwnloadfileService.class);
	
	public static final T6_dwnloadfileService service = Enhancer.enhance(T6_dwnloadfileService.class);
	
	public T6_dwnloadfile SelectById(Integer id){
		
		T6_dwnloadfile mdl = T6_dwnloadfile.dao.findFirst("select * from t6_dwnloadfile where id=?", id);
		return mdl;
	}
}
