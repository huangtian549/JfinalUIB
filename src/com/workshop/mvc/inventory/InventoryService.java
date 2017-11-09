package com.workshop.mvc.inventory;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = InventoryService.serviceName)
public class InventoryService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(InventoryService.class);
	
	public static final String serviceName = "inventoryService";
	
}
