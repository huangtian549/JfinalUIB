package com.workshop.mvc.student;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = StudentService.serviceName)
public class StudentService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(StudentService.class);
	
	public static final String serviceName = "studentService";
	
}
