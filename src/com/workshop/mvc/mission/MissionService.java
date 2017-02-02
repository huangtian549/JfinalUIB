package com.workshop.mvc.mission;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = MissionService.serviceName)
public class MissionService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(MissionService.class);
	
	public static final String serviceName = "missionService";
	
}
