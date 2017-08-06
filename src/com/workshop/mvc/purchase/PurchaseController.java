package com.workshop.mvc.purchase;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolString;
import com.workshop.mvc.customer.Customer;

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
		Object isPay = getPara("isPay");
		paging(ConstantInit.db_dataSource_main, splitPage, Purchase.sqlId_splitPageSelect_list, Purchase.sqlId_splitPageFrom);
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
		Integer customerIds = getParaToInt(0);
		String name = getPara(1);
		if (name != null) {
			try {
				name = URLDecoder.decode(name, "gbk");
				name = URLDecoder.decode(name, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Customer customer = Customer.dao.findById(customerIds);
		String flag = getPara(2);    //flag = 1 表示是从客户列表点击购买记录进来的
		if (flag == null) {
			flag = "0";
		}
		setAttr("customer", customer);
		setAttr("list", list);
		setAttr("customerIds", customerIds);
		setAttr("sum", sum);
		setAttr("name", name);
		setAttr("flag", flag);
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
		purchase.setPurchaseDate(new java.sql.Date(new java.util.Date().getTime()));
		if (purchase.getPriceUS() != null) {
			purchase.setPriceUS(purchase.getPriceUS() * 7);
		}
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
		forwardAction("/workshop/purchase/backOff");
	}
	
	
	/**
	 * 更改多个
	 */
	public void updateMultiByCustomerId() {
		String ids = getPara("ids");
		String column = getPara("column");
		String[] idsArr = splitByComma(ids);
		for (String id : idsArr) {
			Purchase.dao.findByIdLoadColumns(id, "ids," + column).set(column, 1).update();
		}
		String customerId = getPara("customerId") ;
		forwardAction("/workshop/purchase/listByCustomer/" + customerId);
			
	}
	public void updateMultiToAll() {
		String ids = getPara("ids");
		String column = getPara("column");
		String[] idsArr = splitByComma(ids);
		for (String id : idsArr) {
			Purchase.dao.findByIdLoadColumns(id, "ids," + column).set(column, 1).update();
		}
		forwardAction("/workshop/purchase/backOff");
	}
	
	public void goBack() {
		String name = getPara(0);
		Integer flag = getParaToInt(1);
		if (flag == null || flag == 0) {
			paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Customer.sqlId_splitPageFrom);
			render("/workshop/customer/list.html");
			
		}
	}
	

	public void copy() {
		String purchaseId = getPara();
		if (purchaseId != null && purchaseId.length() > 0) {
			setAttr("purchaseId", purchaseId);
			Purchase purchase = Purchase.dao.findById(getPara());
			setAttr("purchase", purchase);
			setAttr("customerIds", purchase.getCustomer_ids());
		}
		render("/workshop/purchase/copy.html");
	}
	
	@Before(PurchaseValidator.class)
	public void submitcopy() {
		Purchase purchase = getModel(Purchase.class);
		purchase.setPurchaseDate(new java.sql.Date(new java.util.Date().getTime()));
		String customerIds = getPara("customerIds");
		if (customerIds != null && customerIds.length() > 0) {
			String[] arr = customerIds.split(",");
			for (int i = 0; i < arr.length; i++) {
				purchase.setIds(null); //在存入一条记录后，ids这个字段会放入这条记录的ids，这样下一条记录就会就不会自动生成ids，就会造成主键冲突
				purchase.setCustomer_ids(Integer.parseInt(arr[i]));
				purchase.save(true);
			}
		}
		forwardAction("/workshop/purchase/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Purchase purchase = Purchase.dao.findById(getPara());
		Customer customer = Customer.dao.findById(purchase.getCustomer_ids());
		setAttr("purchase", purchase);
		setAttr("customer", customer);
		render("/workshop/purchase/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		purchaseService.baseDelete(Purchase.table_name, getPara(0) == null ? ids : getPara(0));
		String id = getPara(1) == null ? ids : getPara(1);
		forwardAction("/workshop/purchase/backOff");
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
