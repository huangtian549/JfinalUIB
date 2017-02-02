package com.workshop.mvc.task;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = TaskService.serviceName)
public class TaskService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(TaskService.class);
	
	public static final String serviceName = "taskService";
	
}
