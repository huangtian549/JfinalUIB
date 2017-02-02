package com.workshop.mvc.task;

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
@Table(tableName = Task.table_name, pkName = "ids")
public class Task extends BaseModel<Task> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Task.class);
	
	public static final Task dao = new Task();

	/**
	 * 表名称
	 */
	public static final String table_name = "hy_task";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_freelancer_id = "freelancer_id";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_student_id = "student_id";
	
	/**
	 * 字段描述：ps, rl, cv 
	 * 字段类型：int(5)  长度：null
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述：兼职计算工资的方式，推荐信是按封，翻译是按120每千字 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_caculate_salary_method = "caculate_salary_method";
	
	/**
	 * 字段描述：数量，比如推荐信是1封，翻译是多少字 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_number = "number";
	
	/**
	 * 字段描述： 
	 * 字段类型：datetime  长度：null
	 */
	public static final String column_start_date = "start_date";
	
	/**
	 * 字段描述： 
	 * 字段类型：datetime  长度：null
	 */
	public static final String column_end_date = "end_date";
	
	/**
	 * 字段描述：是否结算工资 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_has_pay = "has_pay";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_status = "status";
	
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
	 * 字段类型：bigint(2)  长度：null
	 */
	public static final String column_version = "version";
	
	
	/**
	 * sqlId : workshop.task.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.task.splitPageFrom";

	private Integer ids;
	private Integer freelancer_id;
	private Integer student_id;
	private Integer type;
	private Integer caculate_salary_method;
	private Integer number;
	private Timestamp start_date;
	private Timestamp end_date;
	private Integer has_pay;
	private Integer status;
	private String description;
	private Timestamp create_date;
	private Long version;
	
	public void setIds(Integer ids){
		set(column_ids, ids);
	}
	public Integer getIds() {
		return get(column_ids);
	}
	public void setFreelancer_id(Integer freelancer_id){
		set(column_freelancer_id, freelancer_id);
	}
	public Integer getFreelancer_id() {
		return get(column_freelancer_id);
	}
	public void setStudent_id(Integer student_id){
		set(column_student_id, student_id);
	}
	public Integer getStudent_id() {
		return get(column_student_id);
	}
	public void setType(Integer type){
		set(column_type, type);
	}
	public Integer getType() {
		return get(column_type);
	}
	public void setCaculate_salary_method(Integer caculate_salary_method){
		set(column_caculate_salary_method, caculate_salary_method);
	}
	public Integer getCaculate_salary_method() {
		return get(column_caculate_salary_method);
	}
	public void setNumber(Integer number){
		set(column_number, number);
	}
	public Integer getNumber() {
		return get(column_number);
	}
	public void setStart_date(Timestamp start_date){
		set(column_start_date, start_date);
	}
	public Timestamp getStart_date() {
		return get(column_start_date);
	}
	public void setEnd_date(Timestamp end_date){
		set(column_end_date, end_date);
	}
	public Timestamp getEnd_date() {
		return get(column_end_date);
	}
	public void setHas_pay(Integer has_pay){
		set(column_has_pay, has_pay);
	}
	public Integer getHas_pay() {
		return get(column_has_pay);
	}
	public void setStatus(Integer status){
		set(column_status, status);
	}
	public Integer getStatus() {
		return get(column_status);
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
