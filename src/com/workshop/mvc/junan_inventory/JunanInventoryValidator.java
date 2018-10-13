package com.workshop.mvc.junan_inventory;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class JunanInventoryValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(JunanInventoryValidator.class);
	
	@SuppressWarnings("unused")
	private JunanInventoryService junanInventoryService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/customer/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/workshop/customer/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Junan_inventory.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/customer/save")){
			controller.render("/workshop/xxx.html");
		
		} else if (actionKey.equals("/workshop/customer/update")){
			controller.render("/workshop/xxx.html");
		
		}
	}
	
}
