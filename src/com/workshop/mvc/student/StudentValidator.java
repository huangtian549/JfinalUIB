package com.workshop.mvc.student;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class StudentValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(StudentValidator.class);
	
	@SuppressWarnings("unused")
	private StudentService studentService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/student/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/workshop/student/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Student.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/workshop/student/save")){
			controller.render("/workshop/xxx.html");
		
		} else if (actionKey.equals("/workshop/student/update")){
			controller.render("/workshop/xxx.html");
		
		}
	}
	
}
