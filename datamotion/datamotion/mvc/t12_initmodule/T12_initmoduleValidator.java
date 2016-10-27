package datamotion.mvc.t12_initmodule;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T12_initmoduleValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T12_initmoduleValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T12_initmoduleController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T12_initmoduleController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T12_initmodule.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T12_initmoduleController.pthc+"save")){
			controller.render(T12_initmoduleController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T12_initmoduleController.pthc+"update")){
			controller.render(T12_initmoduleController.pthv+"xxx.html");
		
		}
	}
	
}
