package com.workshop.mvc.student;

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
@Table(tableName = Student.table_name, pkName = "ids")
public class Student extends BaseModel<Student> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Student.class);
	
	public static final Student dao = new Student();

	/**
	 * 表名称
	 */
	public static final String table_name = "hy_student";
	
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
	 * 字段类型：varchar(50)  长度：50
	 */
	public static final String column_taobao_id = "taobao_id";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(13)  长度：13
	 */
	public static final String column_mobile = "mobile";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(11)  长度：11
	 */
	public static final String column_qq = "qq";
	
	/**
	 * 字段描述：保存的是,1,2,3, 
	 * 字段类型：varchar(50)  长度：50
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述：申请的哪一年 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_year = "year";
	
	/**
	 * 字段描述： 
	 * 字段类型：datetime  长度：null
	 */
	public static final String column_end_date = "end_date";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_ps_status = "ps_status";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_rl_status = "rl_status";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_cv_status = "cv_status";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(300)  长度：300
	 */
	public static final String column_major = "major";
	
	/**
	 * 字段描述：0代表咨询，1代表付款 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_category = "category";
	
	/**
	 * 字段描述：期望通电话时间 
	 * 字段类型：varchar(100)  长度：100
	 */
	public static final String column_expect_dial_time = "expect_dial_time";
	
	/**
	 * 字段描述：0代表正在进行，1代表完成 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_status = "status";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(500)  长度：500
	 */
	public static final String column_image = "image";
	
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
	 * sqlId : workshop.student.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.student.splitPageFrom";

	private Integer ids;
	private String name;
	private String taobao_id;
	private String mobile;
	private String qq;
	private String type;
	private Integer year;
	private Timestamp end_date;
	private Integer ps_status;
	private Integer rl_status;
	private Integer cv_status;
	private String major;
	private Integer category;
	private String expect_dial_time;
	private Integer status;
	private String image;
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
	public void setTaobao_id(String taobao_id){
		set(column_taobao_id, taobao_id);
	}
	public String getTaobao_id() {
		return get(column_taobao_id);
	}
	public void setMobile(String mobile){
		set(column_mobile, mobile);
	}
	public String getMobile() {
		return get(column_mobile);
	}
	public void setQq(String qq){
		set(column_qq, qq);
	}
	public String getQq() {
		return get(column_qq);
	}
	public void setType(String type){
		set(column_type, type);
	}
	public String getType() {
		return get(column_type);
	}
	public void setYear(Integer year){
		set(column_year, year);
	}
	public Integer getYear() {
		return get(column_year);
	}
	public void setEnd_date(Timestamp end_date){
		set(column_end_date, end_date);
	}
	public Timestamp getEnd_date() {
		return get(column_end_date);
	}
	public void setPs_status(Integer ps_status){
		set(column_ps_status, ps_status);
	}
	public Integer getPs_status() {
		return get(column_ps_status);
	}
	public void setRl_status(Integer rl_status){
		set(column_rl_status, rl_status);
	}
	public Integer getRl_status() {
		return get(column_rl_status);
	}
	public void setCv_status(Integer cv_status){
		set(column_cv_status, cv_status);
	}
	public Integer getCv_status() {
		return get(column_cv_status);
	}
	public void setMajor(String major){
		set(column_major, major);
	}
	public String getMajor() {
		return get(column_major);
	}
	public void setCategory(Integer category){
		set(column_category, category);
	}
	public Integer getCategory() {
		return get(column_category);
	}
	public void setExpect_dial_time(String expect_dial_time){
		set(column_expect_dial_time, expect_dial_time);
	}
	public String getExpect_dial_time() {
		return get(column_expect_dial_time);
	}
	public void setStatus(Integer status){
		set(column_status, status);
	}
	public Integer getStatus() {
		return get(column_status);
	}
	public void setImage(String image){
		set(column_image, image);
	}
	public String getImage() {
		return get(column_image);
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
