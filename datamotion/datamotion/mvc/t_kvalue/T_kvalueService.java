package datamotion.mvc.t_kvalue;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.platform.mvc.base.BaseService;

import datamotion.mvc.t12_initmodule.T12_initmodule;

public class T_kvalueService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T_kvalueService.class);
	
	public static final T_kvalueService service = Enhancer.enhance(T_kvalueService.class);
	
	public T_kvalue SelectById(Integer id){
		
		T_kvalue mdl = T_kvalue.dao.findFirst("select * from t_kvalue where id=?", id);
		return mdl;
	}
	public List<T_kvalue> getList() {

		return T_kvalue.dao.find("select * from t_kvalue order by id asc limit 1000");
	}
}
