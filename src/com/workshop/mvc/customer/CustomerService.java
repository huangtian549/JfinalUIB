package com.workshop.mvc.customer;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = CustomerService.serviceName)
public class CustomerService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(CustomerService.class);
	
	public static final String serviceName = "customerService";
	
}
