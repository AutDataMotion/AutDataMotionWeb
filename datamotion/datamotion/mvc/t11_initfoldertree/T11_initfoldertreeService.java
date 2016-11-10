package datamotion.mvc.t11_initfoldertree;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.platform.mvc.base.BaseService;

import datamotion.mvc.t12_initmodule.T12_initmodule;

public class T11_initfoldertreeService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T11_initfoldertreeService.class);
	
	public static final T11_initfoldertreeService service = Enhancer.enhance(T11_initfoldertreeService.class);
	
	public T11_initfoldertree SelectById(Integer id){
		
		T11_initfoldertree mdl = T11_initfoldertree.dao.findFirst("select * from t11_initfoldertree where id=?", id);
		return mdl;
	}
	public List<T11_initfoldertree> getList() {

		return T11_initfoldertree.dao.find("select * from t11_initfoldertree order by id asc limit 1000");
	}
}
