package datamotion.mvc.t_kvalue;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T_kvalueValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T_kvalueValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T_kvalueController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T_kvalueController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T_kvalue.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T_kvalueController.pthc+"save")){
			controller.render(T_kvalueController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T_kvalueController.pthc+"update")){
			controller.render(T_kvalueController.pthv+"xxx.html");
		
		}
	}
	
}
