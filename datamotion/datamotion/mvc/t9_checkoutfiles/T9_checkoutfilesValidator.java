package datamotion.mvc.t9_checkoutfiles;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T9_checkoutfilesValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T9_checkoutfilesValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T9_checkoutfilesController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T9_checkoutfilesController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T9_checkoutfiles.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T9_checkoutfilesController.pthc+"save")){
			controller.render(T9_checkoutfilesController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T9_checkoutfilesController.pthc+"update")){
			controller.render(T9_checkoutfilesController.pthv+"xxx.html");
		
		}
	}
	
}
