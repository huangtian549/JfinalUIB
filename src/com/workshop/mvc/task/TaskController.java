package com.workshop.mvc.task;

import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;
import com.jfinal.aop.Before;

/**
 * XXX 管理	
 * 描述：
 * 
 * /workshop/task
 * /workshop/task/save
 * /workshop/task/edit
 * /workshop/task/update
 * /workshop/task/view
 * /workshop/task/delete
 * /common/task/add.html
 * 
 */
@Controller("/workshop/task")
public class TaskController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(TaskController.class);
	
	private TaskService taskService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Task.sqlId_splitPageFrom);
		render("/workshop/task/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(TaskValidator.class)
	public void save() {
		getModel(Task.class).save();
		forwardAction("/workshop/task/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Task task = Task.dao.findById(getPara());
		setAttr("task", task);
		render("/workshop/task/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(TaskValidator.class)
	public void update() {
		getModel(Task.class).update();
		forwardAction("/workshop/task/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Task task = Task.dao.findById(getPara());
		setAttr("task", task);
		render("/workshop/task/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		taskService.baseDelete(Task.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/task/backOff");
	}
	
}
