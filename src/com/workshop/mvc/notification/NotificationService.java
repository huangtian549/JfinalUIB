package com.workshop.mvc.notification;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = NotificationService.serviceName)
public class NotificationService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(NotificationService.class);
	
	public static final String serviceName = "notificationService";
	
}
