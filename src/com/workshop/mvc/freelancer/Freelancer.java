package com.workshop.mvc.freelancer;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 

import com.jfinal.log.Log;

/**
 *  model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Freelancer.table_name, pkName = "ids")
public class Freelancer extends BaseModel<Freelancer> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Freelancer.class);
	
	public static final Freelancer dao = new Freelancer();

	/**
	 * 表名称
	 */
	public static final String table_name = "hy_freelancer";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(20)  长度：20
	 */
	public static final String column_name = "name";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(30)  长度：30
	 */
	public static final String column_qq_name = "qq_name";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(30)  长度：30
	 */
	public static final String column_weixin_name = "weixin_name";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(50)  长度：50
	 */
	public static final String column_alipay = "alipay";
	
	/**
	 * 字段描述：擅长类型，比如写推荐信，或者翻译 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_description = "description";
	
	/**
	 * 字段描述： 
	 * 字段类型：datetime  长度：null
	 */
	public static final String column_create_date = "create_date";
	
	/**
	 * 字段描述： 
	 * 字段类型：bigint(20)  长度：null
	 */
	public static final String column_version = "version";
	
	
	/**
	 * sqlId : workshop.freelancer.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.freelancer.splitPageFrom";

	private Integer ids;
	private String name;
	private String qq_name;
	private String weixin_name;
	private String alipay;
	private Integer type;
	private String description;
	private Timestamp create_date;
	private Long version;
	
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
	public void setQq_name(String qq_name){
		set(column_qq_name, qq_name);
	}
	public String getQq_name() {
		return get(column_qq_name);
	}
	public void setWeixin_name(String weixin_name){
		set(column_weixin_name, weixin_name);
	}
	public String getWeixin_name() {
		return get(column_weixin_name);
	}
	public void setAlipay(String alipay){
		set(column_alipay, alipay);
	}
	public String getAlipay() {
		return get(column_alipay);
	}
	public void setType(Integer type){
		set(column_type, type);
	}
	public Integer getType() {
		return get(column_type);
	}
	public void setDescription(String description){
		set(column_description, description);
	}
	public String getDescription() {
		return get(column_description);
	}
	public void setCreate_date(Timestamp create_date){
		set(column_create_date, create_date);
	}
	public Timestamp getCreate_date() {
		return get(column_create_date);
	}
	public void setVersion(Long version){
		set(column_version, version);
	}
	public Long getVersion() {
		return get(column_version);
	}
	
}
