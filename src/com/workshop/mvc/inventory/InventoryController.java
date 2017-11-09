package com.workshop.mvc.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.json.FastJson;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

/**
 * XXX 管理	
 * 描述：
 * 
 * /workshop/Inventory
 * /workshop/Inventory/save
 * /workshop/Inventory/edit
 * /workshop/Inventory/update
 * /workshop/Inventory/view
 * /workshop/Inventory/delete
 * /common/Inventory/add.html
 * 
 */
@Controller("/workshop/inventory")
public class InventoryController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(InventoryController.class);
	
	private InventoryService inventoryService;
	
	/**
	 * 列表
	 */
	public void index() {
		Map<String, Object> queryParam = splitPage.getQueryParam();
		queryParam.put("userId", this.getCUserIds());
		splitPage.setQueryParam(queryParam);
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Inventory.sqlId_splitPageFrom);
		render("/workshop/inventory/list.html");
	}
	
	
//	public void search() {
//		String name = getPara("name");
//		String callback = getPara("callback");
//		String sql = getSql("workshop.Inventory.search");
//		List<Inventory> InventoryList = Inventory.dao.find(sql, this.getCUserIds(),name, name);
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (InventoryList != null) {
//			String Inventorys[ ][ ] = new String[InventoryList.size()][4];
//			for (int i = 0; i < InventoryList.size(); i++) {
//					Inventorys[i][0] = String.valueOf(InventoryList.get(i).getIds());
//					Inventorys[i][1] = InventoryList.get(i).getName();
//					Inventorys[i][2] = InventoryList.get(i).getWechat();
//					Inventorys[i][3] = InventoryList.get(i).getAddress();
//					
//			}
//			map.put("result", Inventorys);
//		}else{
//			map.put("result", new Inventory[0]);
//			
//		}
//		FastJson fastJson = new FastJson();
//		String json = fastJson.toJson(map);
//		renderJson(callback + "(" + json + ")");
//	}
	/**
	 * 保存
	 */
	@Before(InventoryValidator.class)
	public void save() {
		Inventory Inventory = getModel(Inventory.class);
		Inventory.setUserId(this.getCUserIds());
		Inventory.save(true);
		forwardAction("/workshop/inventory/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Inventory inventory = Inventory.dao.findById(getPara());
		setAttr("inventory", inventory);
		render("/workshop/inventory/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(InventoryValidator.class)
	public void update() {
		getModel(Inventory.class).update();
		forwardAction("/workshop/inventory/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Inventory inventory = Inventory.dao.findById(getPara());
		setAttr("Inventory", inventory);
		render("/workshop/inventory/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		inventoryService.baseDelete(Inventory.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/inventory/backOff");
	}
	
}
