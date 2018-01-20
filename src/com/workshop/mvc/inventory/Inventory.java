package com.workshop.mvc.inventory;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 *  model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Inventory.table_name, pkName = "ids")
public class Inventory extends BaseModel<Inventory> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Inventory.class);
	
	public static final Inventory dao = new Inventory();

	/**
	 * 表名称
	 */
	public static final String table_name = "dg_inventory";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(50)  长度：50
	 */
	public static final String column_name = "name";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(500)  长度：500
	 */
	public static final String column_description = "description";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_count = "count";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_daigou_price = "daigou_price";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_daili_price = "daili_price";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_customer_price = "customer_price";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(50)  长度：50
	 */
	public static final String column_userId = "userId";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_pic = "pic";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_category = "category";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_type = "type";
	
	
	/**
	 * sqlId : workshop.inventory.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.inventory.splitPageFrom";

	private Integer ids;
	private String name;
	private String description;
	private Integer count;
	private Integer daigou_price;
	private Integer daili_price;
	private Integer customer_price;
	private String userId;
	private String pic;
	private Integer category;
	private Integer type;
	
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
	public void setDescription(String description){
		set(column_description, description);
	}
	public String getDescription() {
		return get(column_description);
	}
	public void setCount(Integer count){
		set(column_count, count);
	}
	public Integer getCount() {
		return get(column_count);
	}
	public void setDaigou_price(Integer daigou_price){
		set(column_daigou_price, daigou_price);
	}
	public Integer getDaigou_price() {
		return get(column_daigou_price);
	}
	public void setDaili_price(Integer daili_price){
		set(column_daili_price, daili_price);
	}
	public Integer getDaili_price() {
		return get(column_daili_price);
	}
	public void setCustomer_price(Integer customer_price){
		set(column_customer_price, customer_price);
	}
	public Integer getCustomer_price() {
		return get(column_customer_price);
	}
	public void setUserId(String userId){
		set(column_userId, userId);
	}
	public String getUserId() {
		return get(column_userId);
	}
	public void setPic(String pic){
		set(column_pic, pic);
	}
	public String getPic() {
		return get(column_pic);
	}
	public void setCategory(Integer category){
		set(column_category, category);
	}
	public Integer getCategory() {
		return get(column_category);
	}
	public void setType(Integer type){
		set(column_type, type);
	}
	public Integer getType() {
		return get(column_type);
	}
	
}
