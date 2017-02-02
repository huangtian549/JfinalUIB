package com.workshop.mvc.purchase;

import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;

import java.util.List;

import com.jfinal.aop.Before;

/**
 * XXX 管理	
 * 描述：
 * 
 * /workshop/purchase
 * /workshop/purchase/save
 * /workshop/purchase/edit
 * /workshop/purchase/update
 * /workshop/purchase/view
 * /workshop/purchase/delete
 * /common/purchase/add.html
 * 
 */
@Controller("/workshop/purchase")
public class PurchaseController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PurchaseController.class);
	
	private PurchaseService purchaseService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Purchase.sqlId_splitPageFrom);
		render("/workshop/purchase/list.html");
	}
	
	
	/**
	 * 列表
	 */
	public void listByCustomer() {
		String sql = getSql("workshop.purchase.listByCustomer");
		List<Purchase> list = Purchase.dao.find(sql, getPara());
		setAttr("list", list);
//		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Purchase.sqlId_splitPageForCustomerFrom);
		render("/workshop/purchase/list2.html");
	}
	
	
	/**
	 * 进入add
	 */
	public void toAdd() {
		String customerIds = getPara();
		setAttr("customerIds", customerIds);
		render("/workshop/purchase/add.html");
	}
	
	
	/**
	 * 保存
	 */
	@Before(PurchaseValidator.class)
	public void save() {
		Purchase purchase = getModel(Purchase.class);
		purchase.save(true);
		forwardAction("/workshop/purchase/listByCustomer/" + purchase.getCustomer_ids());
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Purchase purchase = Purchase.dao.findById(getPara());
		setAttr("purchase", purchase);
		setAttr("customerIds", purchase.getCustomer_ids());
		render("/workshop/purchase/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(PurchaseValidator.class)
	public void update() {
		Purchase purchase = getModel(Purchase.class);
		purchase.update();
		forwardAction("/workshop/purchase/listByCustomer/" + purchase.getCustomer_ids());
	}

	/**
	 * 查看
	 */
	public void view() {
		Purchase purchase = Purchase.dao.findById(getPara());
		setAttr("purchase", purchase);
		render("/workshop/purchase/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		purchaseService.baseDelete(Purchase.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/purchase/backOff");
	}
	
}
