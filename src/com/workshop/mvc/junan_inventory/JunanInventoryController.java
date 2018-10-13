package com.workshop.mvc.junan_inventory;

import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

/**
 * XXX 管理	
 * 描述：
 * 
 * /workshop/Junan_inventory
 * /workshop/Junan_inventory/save
 * /workshop/Junan_inventory/edit
 * /workshop/Junan_inventory/update
 * /workshop/Junan_inventory/view
 * /workshop/Junan_inventory/delete
 * /common/Junan_inventory/add.html
 * 
 */
@Controller("/workshop/junan_inventory")
public class JunanInventoryController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(JunanInventoryController.class);
	
	private JunanInventoryService junanInventoryService;
	
	/**
	 * 列表
	 */
	public void index() {
		Map<String, Object> queryParam = splitPage.getQueryParam();
		queryParam.put("userId", this.getCUserIds());
		splitPage.setQueryParam(queryParam);
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Junan_inventory.sqlId_splitPageFrom);
		render("/workshop/junan_inventory/list.html");
	}
	
	
//	public void search() {
//		String name = getPara("name");
//		String callback = getPara("callback");
//		String sql = getSql("workshop.Junan_inventory.search");
//		List<Junan_inventory> InventoryList = Junan_inventory.dao.find(sql, this.getCUserIds(),name, name);
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
//			map.put("result", new Junan_inventory[0]);
//			
//		}
//		FastJson fastJson = new FastJson();
//		String json = fastJson.toJson(map);
//		renderJson(callback + "(" + json + ")");
//	}
	/**
	 * 保存
	 */
	@Before(JunanInventoryValidator.class)
	public void save() {
		Junan_inventory Junan_inventory = getModel(Junan_inventory.class);
		Junan_inventory.setUserId(this.getCUserIds());
		Junan_inventory.save(true);
		forwardAction("/workshop/junan_inventory/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Junan_inventory inventory = Junan_inventory.dao.findById(getPara());
		setAttr("junan_inventory", inventory);
		render("/workshop/junan_inventory/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(JunanInventoryValidator.class)
	public void update() {
		getModel(Junan_inventory.class).update();
		forwardAction("/workshop/junan_inventory/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Junan_inventory inventory = Junan_inventory.dao.findById(getPara());
		setAttr("junan_inventory", inventory);
		render("/workshop/junan_inventory/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		junanInventoryService.baseDelete(Junan_inventory.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/junan_inventory/backOff");
	}
	
}
