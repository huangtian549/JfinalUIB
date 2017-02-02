package com.workshop.mvc.notification;

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
 * /workshop/notification
 * /workshop/notification/save
 * /workshop/notification/edit
 * /workshop/notification/update
 * /workshop/notification/view
 * /workshop/notification/delete
 * /common/notification/add.html
 * 
 */
@Controller("/workshop/notification")
public class NotificationController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(NotificationController.class);
	
	private NotificationService notificationService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Notification.sqlId_splitPageFrom);
		render("/workshop/notification/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(NotificationValidator.class)
	public void save() {
		getModel(Notification.class).save();
		forwardAction("/workshop/notification/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Notification notification = Notification.dao.findById(getPara());
		setAttr("notification", notification);
		render("/workshop/notification/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(NotificationValidator.class)
	public void update() {
		getModel(Notification.class).update();
		forwardAction("/workshop/notification/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Notification notification = Notification.dao.findById(getPara());
		setAttr("notification", notification);
		render("/workshop/notification/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		notificationService.baseDelete(Notification.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/notification/backOff");
	}
	
}
