package datamotion.mvc.t12_initmodule;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.platform.mvc.base.BaseService;

public class T12_initmoduleService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T12_initmoduleService.class);

	public static final T12_initmoduleService service = Enhancer
			.enhance(T12_initmoduleService.class);

	public T12_initmodule SelectById(Integer id) {

		T12_initmodule mdl = T12_initmodule.dao.findFirst(
				"select * from t12_initmodule where id=?", id);
		return mdl;
	}

	public List<T12_initmodule> getList() {

		return T12_initmodule.dao.find("select * from t12_initmodule ");
	}
}
