package com.workshop.mvc.buy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.dept.Department;
import com.platform.tools.ToolString;
import com.workshop.mvc.customer.Customer;

/**
 * XXX 管理	
 * 描述：
 * 
 * /workshop/buy
 * /workshop/buy/save
 * /workshop/buy/edit
 * /workshop/buy/update
 * /workshop/buy/view
 * /workshop/buy/delete
 * /common/buy/add.html
 * 
 */
@Controller("/workshop/buy")
public class BuyController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(BuyController.class);
	
	private BuyService buyService;
	
	/**
	 * 列表
	 */
	public void index() {
		Map<String, Object> queryParam = splitPage.getQueryParam();
		queryParam.put("userId", this.getCUserIds());
		splitPage.setQueryParam(queryParam);
		paging(ConstantInit.db_dataSource_main, splitPage, Buy.sqlId_splitPageSelect, Buy.sqlId_splitPageFrom);
		
		String sql = null;
		Record record = null;
		Object isPay = queryParam.get("isPay");
		if ( isPay == null) {
			sql = getSql(Buy.sqlId_tongjiAllFrom);
			record = Db.use(ConstantInit.db_dataSource_main).findFirst(sql,this.getCUserIds(),queryParam.get("startdate_start"),queryParam.get("startdate_end"));
		}else {
			sql = getSql(Buy.sqlId_tongjiFrom);
			record = Db.use(ConstantInit.db_dataSource_main).findFirst(sql,this.getCUserIds(),queryParam.get("isPay"),queryParam.get("startdate_start"),queryParam.get("startdate_end"));
			
		}

		Map<String, Object>map = record.getColumns();
		Object sum = map.get("sum");
//		if (sum == null) {
//			sum =  new Object();
//		}
		setAttr("sum", sum);
		render("/workshop/buy/list.html");
	}
	
	public void filterIndex() {
		Map<String, Object> queryParam = splitPage.getQueryParam();
		queryParam.put("userId", this.getCUserIds());
		String param = getPara("param");
		queryParam.put(param, "0");
		splitPage.setQueryParam(queryParam);
		paging(ConstantInit.db_dataSource_main, splitPage, Buy.sqlId_splitPageSelect, Buy.sqlId_splitPageFrom);
		render("/workshop/buy/list.html");
	}
	
	

	
	
	/**
	 * 进入add
	 */
	public void toAdd() {
		render("/workshop/buy/add.html");
	}
	
	
	/**
	 * 保存
	 */
	public void save() {
		Buy buy = getModel(Buy.class);
		buy.setPurchaseDate(new java.sql.Date(new java.util.Date().getTime()));
		buy.setUserId(this.getCUserIds());
		buy.save(true);
		forwardAction("/workshop/buy/");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Buy buy = Buy.dao.findById(getPara());
		setAttr("buy", buy);
		render("/workshop/buy/update.html");
	}
	
	/**
	 * 更新
	 */
	public void update() {
		Buy buy = getModel(Buy.class);
		buy.update();
		forwardAction("/workshop/buy/backOff");
	}
	
	
	/**
	 * 更改多个
	 */
	public void updateMultiByCustomerId() {
		String ids = getPara("ids");
		String column = getPara("column");
		String[] idsArr = splitByComma(ids);
		for (String id : idsArr) {
			Buy.dao.findByIdLoadColumns(id, "ids," + column).set(column, 1).update();
		}
		String customerId = getPara("customerId") ;
		forwardAction("/workshop/buy/listByCustomer/" + customerId);
			
	}
	public void updateMultiToAll() {
		String ids = getPara("ids");
		String column = getPara("column");
		String[] idsArr = splitByComma(ids);
		for (String id : idsArr) {
			Buy.dao.findByIdLoadColumns(id, "ids," + column).set(column, 1).update();
		}
		forwardAction("/workshop/buy/backOff");
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
		String buyId = getPara();
		if (buyId != null && buyId.length() > 0) {
			setAttr("buyId", buyId);
			Buy buy = Buy.dao.findById(getPara());
			setAttr("buy", buy);
		}
		render("/workshop/buy/copy.html");
	}
	
	public void submitcopy() {
		Buy buy = getModel(Buy.class);
		buy.setPurchaseDate(new java.sql.Date(new java.util.Date().getTime()));
		String customerIds = getPara("customerIds");
		if (customerIds != null && customerIds.length() > 0) {
			String[] arr = customerIds.split(",");
			for (int i = 0; i < arr.length; i++) {
				buy.setIds(null); //在存入一条记录后，ids这个字段会放入这条记录的ids，这样下一条记录就会就不会自动生成ids，就会造成主键冲突
				buy.save(true);
			}
		}
		forwardAction("/workshop/buy/backOff");
	}

	
	/**
	 * 删除
	 */
	public void delete() {
		buyService.baseDelete(Buy.table_name, getPara(0) == null ? ids : getPara(0));
		String id = getPara(1) == null ? ids : getPara(1);
		forwardAction("/workshop/buy/backOff");
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
