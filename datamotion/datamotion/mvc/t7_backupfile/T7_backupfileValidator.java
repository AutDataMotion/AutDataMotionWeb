package datamotion.mvc.t7_backupfile;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T7_backupfileValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T7_backupfileValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T7_backupfileController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T7_backupfileController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T7_backupfile.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T7_backupfileController.pthc+"save")){
			controller.render(T7_backupfileController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T7_backupfileController.pthc+"update")){
			controller.render(T7_backupfileController.pthv+"xxx.html");
		
		}
	}
	
}
