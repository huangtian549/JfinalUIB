package com.workshop.mvc.freelancer;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class FreelancerValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(FreelancerValidator.class);
	
	@SuppressWarnings("unused")
	private FreelancerService freelancerService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/freelancer/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/workshop/freelancer/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Freelancer.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/freelancer/save")){
			controller.render("/workshop/xxx.html");
		
		} else if (actionKey.equals("/workshop/freelancer/update")){
			controller.render("/workshop/xxx.html");
		
		}
	}
	
}
