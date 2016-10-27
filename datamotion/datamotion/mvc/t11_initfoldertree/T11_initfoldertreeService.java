package datamotion.mvc.t11_initfoldertree;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T11_initfoldertreeService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T11_initfoldertreeService.class);
	
	public static final T11_initfoldertreeService service = Enhancer.enhance(T11_initfoldertreeService.class);
	
	public T11_initfoldertree SelectById(Integer id){
		
		T11_initfoldertree mdl = T11_initfoldertree.dao.findFirst("select * from t11_initfoldertree where id=?", id);
		return mdl;
	}
}
