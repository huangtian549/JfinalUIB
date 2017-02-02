package com.workshop.mvc.purchase;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = PurchaseService.serviceName)
public class PurchaseService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PurchaseService.class);
	
	public static final String serviceName = "purchaseService";
	
}
