package com.workshop.mvc.junan_inventory;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 *  model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Junan_inventory.table_name, pkName = "ids")
public class Junan_inventory extends BaseModel<Junan_inventory> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Junan_inventory.class);
	
	public static final Junan_inventory dao = new Junan_inventory();

	/**
	 * 表名称
	 */
	public static final String table_name = "dg_junan_inventory";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(10) unsigned  长度：null
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(50)  长度：50
	 */
	public static final String column_name = "name";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(256)  长度：256
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
	 * 字段类型：varchar(255)  长度：255
	 */
	public static final String column_userId = "userId";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(50)  长度：50
	 */
	public static final String column_pic = "pic";
	
	
	/**
	 * sqlId : workshop.junan_inventory.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.junan_inventory.splitPageFrom";

	private Long ids;
	private String name;
	private String description;
	private Integer count;
	private Integer daigou_price;
	private Integer daili_price;
	private Integer customer_price;
	private String userId;
	private String pic;
	
	public void setIds(Long ids){
		set(column_ids, ids);
	}
	public Long getIds() {
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
	
}
