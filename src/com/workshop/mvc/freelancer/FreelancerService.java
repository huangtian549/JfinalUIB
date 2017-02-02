package com.workshop.mvc.freelancer;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = FreelancerService.serviceName)
public class FreelancerService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(FreelancerService.class);
	
	public static final String serviceName = "freelancerService";
	
}
