package com.workshop.mvc.mission;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class MissionValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(MissionValidator.class);
	
	@SuppressWarnings("unused")
	private MissionService missionService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/mission/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/workshop/mission/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Mission.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/mission/save")){
			controller.render("/workshop/xxx.html");
		
		} else if (actionKey.equals("/workshop/mission/update")){
			controller.render("/workshop/xxx.html");
		
		}
	}
	
}
