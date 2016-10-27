package datamotion.mvc.t11_initfoldertree;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T11_initfoldertreeValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T11_initfoldertreeValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T11_initfoldertreeController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T11_initfoldertreeController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T11_initfoldertree.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T11_initfoldertreeController.pthc+"save")){
			controller.render(T11_initfoldertreeController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T11_initfoldertreeController.pthc+"update")){
			controller.render(T11_initfoldertreeController.pthv+"xxx.html");
		
		}
	}
	
}
