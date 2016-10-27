package datamotion.mvc.t6_dwnloadfile;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T6_dwnloadfileValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6_dwnloadfileValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T6_dwnloadfileController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T6_dwnloadfileController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T6_dwnloadfile.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T6_dwnloadfileController.pthc+"save")){
			controller.render(T6_dwnloadfileController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T6_dwnloadfileController.pthc+"update")){
			controller.render(T6_dwnloadfileController.pthv+"xxx.html");
		
		}
	}
	
}
