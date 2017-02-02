package com.workshop.mvc.purchase;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class PurchaseValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PurchaseValidator.class);
	
	@SuppressWarnings("unused")
	private PurchaseService purchaseService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/purchase/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/workshop/purchase/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Purchase.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/purchase/save")){
			controller.render("/workshop/xxx.html");
		
		} else if (actionKey.equals("/workshop/purchase/update")){
			controller.render("/workshop/xxx.html");
		
		}
	}
	
}
