package com.workshop.mvc.notification;

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
@Table(tableName = Notification.table_name, pkName = "ids")
public class Notification extends BaseModel<Notification> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Notification.class);
	
	public static final Notification dao = new Notification();

	/**
	 * 表名称
	 */
	public static final String table_name = "hy_notification";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_user_id = "user_id";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(500)  长度：500
	 */
	public static final String column_msg = "msg";
	
	/**
	 * 字段描述： 
	 * 字段类型：datetime  长度：null
	 */
	public static final String column_notification_time = "notification_time";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(500)  长度：500
	 */
	public static final String column_description = "description";
	
	/**
	 * 字段描述：ps, rl ,cv, rl1,rl2 
	 * 字段类型：int(255)  长度：null
	 */
	public static final String column_task_type = "task_type";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_status = "status";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_student_id = "student_id";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_create_user_id = "create_user_id";
	
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
	 * sqlId : workshop.notification.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.notification.splitPageFrom";

	private Integer ids;
	private Integer user_id;
	private String msg;
	private Timestamp notification_time;
	private String description;
	private Integer task_type;
	private Integer type;
	private Integer status;
	private Integer student_id;
	private Integer create_user_id;
	private Timestamp create_date;
	private Long version;
	
	public void setIds(Integer ids){
		set(column_ids, ids);
	}
	public Integer getIds() {
		return get(column_ids);
	}
	public void setUser_id(Integer user_id){
		set(column_user_id, user_id);
	}
	public Integer getUser_id() {
		return get(column_user_id);
	}
	public void setMsg(String msg){
		set(column_msg, msg);
	}
	public String getMsg() {
		return get(column_msg);
	}
	public void setNotification_time(Timestamp notification_time){
		set(column_notification_time, notification_time);
	}
	public Timestamp getNotification_time() {
		return get(column_notification_time);
	}
	public void setDescription(String description){
		set(column_description, description);
	}
	public String getDescription() {
		return get(column_description);
	}
	public void setTask_type(Integer task_type){
		set(column_task_type, task_type);
	}
	public Integer getTask_type() {
		return get(column_task_type);
	}
	public void setType(Integer type){
		set(column_type, type);
	}
	public Integer getType() {
		return get(column_type);
	}
	public void setStatus(Integer status){
		set(column_status, status);
	}
	public Integer getStatus() {
		return get(column_status);
	}
	public void setStudent_id(Integer student_id){
		set(column_student_id, student_id);
	}
	public Integer getStudent_id() {
		return get(column_student_id);
	}
	public void setCreate_user_id(Integer create_user_id){
		set(column_create_user_id, create_user_id);
	}
	public Integer getCreate_user_id() {
		return get(column_create_user_id);
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
