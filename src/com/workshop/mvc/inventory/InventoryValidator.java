package com.workshop.mvc.inventory;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class InventoryValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(InventoryValidator.class);
	
	@SuppressWarnings("unused")
	private InventoryService customerService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/customer/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/workshop/customer/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Inventory.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/customer/save")){
			controller.render("/workshop/xxx.html");
		
		} else if (actionKey.equals("/workshop/customer/update")){
			controller.render("/workshop/xxx.html");
		
		}
	}
	
}
