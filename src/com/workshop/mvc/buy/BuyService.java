package com.workshop.mvc.buy;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = BuyService.serviceName)
public class BuyService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(BuyService.class);
	
	public static final String serviceName = "buyService";
	
}
