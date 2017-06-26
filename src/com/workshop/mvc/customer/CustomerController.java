package com.workshop.mvc.customer;

import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.dept.Department;
import com.jfinal.log.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.json.FastJson;

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
	
	
	public void search() {
		String name = getPara("name");
		String callback = getPara("callback");
		String sql = getSql("workshop.customer.search");
		List<Customer> customerList = Customer.dao.find(sql, name, name);
		Map<String, Object> map = new HashMap<String, Object>();
		if (customerList != null) {
			String customers[ ][ ] = new String[customerList.size()][4];
			for (int i = 0; i < customerList.size(); i++) {
					customers[i][0] = String.valueOf(customerList.get(i).getIds());
					customers[i][1] = customerList.get(i).getName();
					customers[i][2] = customerList.get(i).getWechat();
					customers[i][3] = customerList.get(i).getAddress();
					
			}
			map.put("result", customers);
		}else{
			map.put("result", new Customer[0]);
			
		}
		FastJson fastJson = new FastJson();
		String json = fastJson.toJson(map);
		renderJson(callback + "(" + json + ")");
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
