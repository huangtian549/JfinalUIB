package com.workshop.mvc.junan_inventory;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = JunanInventoryService.serviceName)
public class JunanInventoryService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(JunanInventoryService.class);
	
	public static final String serviceName = "junan_inventoryService";
	
}
