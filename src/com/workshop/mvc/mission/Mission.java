package com.workshop.mvc.mission;

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
@Table(tableName = Mission.table_name, pkName = "ids")
public class Mission extends BaseModel<Mission> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Mission.class);
	
	public static final Mission dao = new Mission();

	/**
	 * 表名称
	 */
	public static final String table_name = "hy_mission";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_student_id = "student_id";
	
	/**
	 * 字段描述：任务id 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_task_id = "task_id";
	
	/**
	 * 字段描述：ps , rl, cv 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_task_type = "task_type";
	
	/**
	 * 字段描述：1.未挖掘， 2. 已挖掘待审核， 3.已经审核，待发邮件，4.已发邮件，待反馈 
	 * 字段类型：int(1)  长度：null
	 */
	public static final String column_activity = "activity";
	
	/**
	 * 字段描述： 
	 * 字段类型：int(10)  长度：null
	 */
	public static final String column_freelancer_id = "freelancer_id";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(100)  长度：100
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
	 * sqlId : workshop.mission.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "workshop.mission.splitPageFrom";

	private Integer ids;
	private Integer student_id;
	private Integer task_id;
	private Integer task_type;
	private Integer activity;
	private Integer freelancer_id;
	private String description;
	private Timestamp create_date;
	private Long version;
	
	public void setIds(Integer ids){
		set(column_ids, ids);
	}
	public Integer getIds() {
		return get(column_ids);
	}
	public void setStudent_id(Integer student_id){
		set(column_student_id, student_id);
	}
	public Integer getStudent_id() {
		return get(column_student_id);
	}
	public void setTask_id(Integer task_id){
		set(column_task_id, task_id);
	}
	public Integer getTask_id() {
		return get(column_task_id);
	}
	public void setTask_type(Integer task_type){
		set(column_task_type, task_type);
	}
	public Integer getTask_type() {
		return get(column_task_type);
	}
	public void setActivity(Integer activity){
		set(column_activity, activity);
	}
	public Integer getActivity() {
		return get(column_activity);
	}
	public void setFreelancer_id(Integer freelancer_id){
		set(column_freelancer_id, freelancer_id);
	}
	public Integer getFreelancer_id() {
		return get(column_freelancer_id);
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
