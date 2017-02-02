package com.workshop.mvc.customer;

import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;
import com.jfinal.aop.Before;

/**
 * XXX 管理	
 * 描述：
 * 
 * /workshop/customer
 * /workshop/customer/save
 * /workshop/customer/edit
 * /workshop/customer/update
 * /workshop/customer/view
 * /workshop/customer/delete
 * /common/customer/add.html
 * 
 */
@Controller("/workshop/customer")
public class CustomerController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(CustomerController.class);
	
	private CustomerService customerService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Customer.sqlId_splitPageFrom);
		render("/workshop/customer/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(CustomerValidator.class)
	public void save() {
		getModel(Customer.class).save(true);
		forwardAction("/workshop/customer/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Customer customer = Customer.dao.findById(getPara());
		setAttr("customer", customer);
		render("/workshop/customer/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(CustomerValidator.class)
	public void update() {
		getModel(Customer.class).update();
		forwardAction("/workshop/customer/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Customer customer = Customer.dao.findById(getPara());
		setAttr("customer", customer);
		render("/workshop/customer/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		customerService.baseDelete(Customer.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/customer/backOff");
	}
	
}
