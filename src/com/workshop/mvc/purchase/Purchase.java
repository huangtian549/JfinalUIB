package com.workshop.mvc.purchase;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Date; 

import com.jfinal.log.Log;

/**
 *  model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Purchase.table_name, pkName = "ids")
public class Purchase extends BaseModel<Purchase> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Purchase.class);
	
	public static final Purchase dao = new Purchase();
	
	public static final String sqlId_splitPageSelect = "workshop.purchase.splitPageSelect";

	/**
	 * 表名称
	 */
	public static final String table_name = "dg_purchase";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_customer_ids = "customer_ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_website = "website";
	
	/**
	 * 字段描述： 
	 * 字段类型：float  长度：null
	 */
	public static final String column_priceUS = "priceUS";
	
	/**
	 * 字段描述： 
	 * 字段类型：float  长度：null
	 */
	public static final String column_priceCN = "priceCN";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_description = "description";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_pic = "pic";
	
	/**
	 * 字段描述： 
	 * 字段类型：date  长度：null
	 */
	public static final String column_purchaseDate = "purchaseDate";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_isPay = "isPay";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_isSend = "isSend";
	
	/**
	 * 字段描述： 
	 * 字段类型：float  长度：null
	 */
	public static final String column_deliverFee = "deliverFee";
	
	/**
	 * 字段描述： 
	 * 字段类型：date  长度：null
	 */
	public static final String column_deliverDate = "deliverDate";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_isPayDeliverFee = "isPayDeliverFee";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_version = "version";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(100)  长度：100
	 */
	public static final String column_deliverMethod = "deliverMethod";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(100)  长度：100
	 */
	public static final String column_trackNum = "trackNum";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(255)  长度：255
	 */
	public static final String column_remark1 = "remark1";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(255)  长度：255
	 */
	public static final String column_remark2 = "remark2";
	
	
	/**
	 * sqlId : workshop.purchase.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.purchase.splitPageFrom";

	private Integer ids;
	private Integer customer_ids;
	private String website;
	private Float priceUS;
	private Float priceCN;
	private String description;
	private String pic;
	private Date purchaseDate;
	private Integer isPay;
	private Integer isSend;
	private Float deliverFee;
	private Date deliverDate;
	private Integer isPayDeliverFee;
	private Integer version;
	private String deliverMethod;
	private String trackNum;
	private String remark1;
	private String remark2;
	
	public void setIds(Integer ids){
		set(column_ids, ids);
	}
	public Integer getIds() {
		return get(column_ids);
	}
	public void setCustomer_ids(Integer customer_ids){
		set(column_customer_ids, customer_ids);
	}
	public Integer getCustomer_ids() {
		return get(column_customer_ids);
	}
	public void setWebsite(String website){
		set(column_website, website);
	}
	public String getWebsite() {
		return get(column_website);
	}
	public void setPriceUS(Float priceUS){
		set(column_priceUS, priceUS);
	}
	public Float getPriceUS() {
		return get(column_priceUS);
	}
	public void setPriceCN(Float priceCN){
		set(column_priceCN, priceCN);
	}
	public Float getPriceCN() {
		return get(column_priceCN);
	}
	public void setDescription(String description){
		set(column_description, description);
	}
	public String getDescription() {
		return get(column_description);
	}
	public void setPic(String pic){
		set(column_pic, pic);
	}
	public String getPic() {
		return get(column_pic);
	}
	public void setPurchaseDate(Date purchaseDate){
		set(column_purchaseDate, purchaseDate);
	}
	public Date getPurchaseDate() {
		return get(column_purchaseDate);
	}
	public void setIsPay(Integer isPay){
		set(column_isPay, isPay);
	}
	public Integer getIsPay() {
		return get(column_isPay);
	}
	public void setIsSend(Integer isSend){
		set(column_isSend, isSend);
	}
	public Integer getIsSend() {
		return get(column_isSend);
	}
	public void setDeliverFee(Float deliverFee){
		set(column_deliverFee, deliverFee);
	}
	public Float getDeliverFee() {
		return get(column_deliverFee);
	}
	public void setDeliverDate(Date deliverDate){
		set(column_deliverDate, deliverDate);
	}
	public Date getDeliverDate() {
		return get(column_deliverDate);
	}
	public void setIsPayDeliverFee(Integer isPayDeliverFee){
		set(column_isPayDeliverFee, isPayDeliverFee);
	}
	public Integer getIsPayDeliverFee() {
		return get(column_isPayDeliverFee);
	}
	public void setVersion(Integer version){
		set(column_version, version);
	}
	public Integer getVersion() {
		return get(column_version);
	}
	public void setDeliverMethod(String deliverMethod){
		set(column_deliverMethod, deliverMethod);
	}
	public String getDeliverMethod() {
		return get(column_deliverMethod);
	}
	public void setTrackNum(String trackNum){
		set(column_trackNum, trackNum);
	}
	public String getTrackNum() {
		return get(column_trackNum);
	}
	public void setRemark1(String remark1){
		set(column_remark1, remark1);
	}
	public String getRemark1() {
		return get(column_remark1);
	}
	public void setRemark2(String remark2){
		set(column_remark2, remark2);
	}
	public String getRemark2() {
		return get(column_remark2);
	}
	
}
