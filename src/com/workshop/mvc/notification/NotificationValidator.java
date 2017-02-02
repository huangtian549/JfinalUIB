package com.workshop.mvc.notification;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class NotificationValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(NotificationValidator.class);
	
	@SuppressWarnings("unused")
	private NotificationService notificationService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/notification/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/workshop/notification/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Notification.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/notification/save")){
			controller.render("/workshop/xxx.html");
		
		} else if (actionKey.equals("/workshop/notification/update")){
			controller.render("/workshop/xxx.html");
		
		}
	}
	
}
