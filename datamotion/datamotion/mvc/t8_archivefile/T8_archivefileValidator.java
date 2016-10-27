package datamotion.mvc.t8_archivefile;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T8_archivefileValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T8_archivefileValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T8_archivefileController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T8_archivefileController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T8_archivefile.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T8_archivefileController.pthc+"save")){
			controller.render(T8_archivefileController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T8_archivefileController.pthc+"update")){
			controller.render(T8_archivefileController.pthv+"xxx.html");
		
		}
	}
	
}
