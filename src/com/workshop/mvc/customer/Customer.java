package com.workshop.mvc.customer;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 *  model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Customer.table_name, pkName = "ids")
public class Customer extends BaseModel<Customer> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Customer.class);
	
	public static final Customer dao = new Customer();

	/**
	 * 表名称
	 */
	public static final String table_name = "dg_customer";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(30)  长度：30
	 */
	public static final String column_name = "name";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(30)  长度：30
	 */
	public static final String column_wechat = "wechat";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(20)  长度：20
	 */
	public static final String column_mobile = "mobile";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(100)  长度：100
	 */
	public static final String column_address = "address";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_version = "version";
	
	
	/**
	 * sqlId : workshop.customer.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.customer.splitPageFrom";

	private Integer ids;
	private String name;
	private String wechat;
	private String mobile;
	private String address;
	private Integer version;
	
	public void setIds(Integer ids){
		set(column_ids, ids);
	}
	public Integer getIds() {
		return get(column_ids);
	}
	public void setName(String name){
		set(column_name, name);
	}
	public String getName() {
		return get(column_name);
	}
	public void setWechat(String wechat){
		set(column_wechat, wechat);
	}
	public String getWechat() {
		return get(column_wechat);
	}
	public void setMobile(String mobile){
		set(column_mobile, mobile);
	}
	public String getMobile() {
		return get(column_mobile);
	}
	public void setAddress(String address){
		set(column_address, address);
	}
	public String getAddress() {
		return get(column_address);
	}
	public void setVersion(Integer version){
		set(column_version, version);
	}
	public Integer getVersion() {
		return get(column_version);
	}
	
}
