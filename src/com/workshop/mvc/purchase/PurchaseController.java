package com.workshop.mvc.purchase;

import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolString;
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
		paging(ConstantInit.db_dataSource_main, splitPage, Purchase.sqlId_splitPageSelect, Purchase.sqlId_splitPageFrom);
		render("/workshop/purchase/list.html");
	}
	
	
	/**
	 * 列表
	 */
	public void listByCustomer() {
		String sql = getSql("workshop.purchase.listByCustomer");
		List<Purchase> list = Purchase.dao.find(sql, getPara());
		Float sum = 0f;
		if (list != null) {
			for (Purchase purchase : list) {
				if (purchase.getIsPay() != 1) {
					if (purchase.getPriceCN() != null) {
						sum = sum + purchase.getPriceCN();
						
					}
				}
			}
		}
		setAttr("list", list);
		setAttr("customerIds", getPara());
		setAttr("sum", sum);
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
	 * 更改多个
	 */
	public void updateMultiPay() {
		String ids = getPara("ids");
		String column = getPara("column");
		String[] idsArr = splitByComma(ids);
		for (String id : idsArr) {
			Purchase.dao.findByIdLoadColumns(id, "ids," + column).set(column, 1).update();
		}
		String id = getPara("customerIds") == null ? ids : getPara("customerIds");
		String action = "/workshop/purchase/listByCustomer/" + id;
		forwardAction(action);
	}
	public void updateMultiToAll() {
		String ids = getPara("ids");
		String column = getPara("column");
		String[] idsArr = splitByComma(ids);
		for (String id : idsArr) {
			Purchase.dao.findByIdLoadColumns(id, "ids," + column).set(column, 1).update();
		}
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
		purchaseService.baseDelete(Purchase.table_name, getPara(0) == null ? ids : getPara(0));
		String id = getPara(1) == null ? ids : getPara(1);
		String action = "/workshop/purchase/listByCustomer/" + id;
		System.out.println(action);
		forwardAction(action);
	}
	
	/**
	 * 把11,22,33...转成数组['11','22','33'...]
	 * @param ids
	 * @return
	 * 描述：把字符串分割成数组返回，并验证分割后的数据
	 */
	public static String[] splitByComma(String ids){
		if(null == ids || ids.trim().isEmpty()){
			return new String[0];
		}
		
		String[] idsArr = ids.split(",");
		
		for (String id : idsArr) {
			if(!ToolString.regExpVali(id, ToolString.regExp_letter_5)){ // 匹配字符串，由数字、大小写字母、下划线组成
				log.error("字符安全验证失败：" + id);
				throw new RuntimeException("字符安全验证失败：" + id);
			}
		}
		
		return idsArr;
	}
	
}
