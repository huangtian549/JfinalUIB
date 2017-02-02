package com.workshop.mvc.task;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class TaskValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(TaskValidator.class);
	
	@SuppressWarnings("unused")
	private TaskService taskService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/task/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/workshop/task/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Task.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/task/save")){
			controller.render("/workshop/xxx.html");
		
		} else if (actionKey.equals("/workshop/task/update")){
			controller.render("/workshop/xxx.html");
		
		}
	}
	
}
