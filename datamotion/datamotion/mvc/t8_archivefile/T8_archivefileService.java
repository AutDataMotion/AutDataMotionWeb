package datamotion.mvc.t8_archivefile;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T8_archivefileService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T8_archivefileService.class);
	
	public static final T8_archivefileService service = Enhancer.enhance(T8_archivefileService.class);
	
	public T8_archivefile SelectById(Integer id){
		
		T8_archivefile mdl = T8_archivefile.dao.findFirst("select * from t8_archivefile where id=?", id);
		return mdl;
	}
}
