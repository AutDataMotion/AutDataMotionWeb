package datamotion.mvc.t9_checkoutfiles;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T9_checkoutfilesService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T9_checkoutfilesService.class);
	
	public static final T9_checkoutfilesService service = Enhancer.enhance(T9_checkoutfilesService.class);
	
	public T9_checkoutfiles SelectById(Integer id){
		
		T9_checkoutfiles mdl = T9_checkoutfiles.dao.findFirst("select * from t9_checkoutfiles where id=?", id);
		return mdl;
	}
}
