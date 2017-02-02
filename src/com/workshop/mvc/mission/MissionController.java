package com.workshop.mvc.mission;

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
 * /workshop/mission
 * /workshop/mission/save
 * /workshop/mission/edit
 * /workshop/mission/update
 * /workshop/mission/view
 * /workshop/mission/delete
 * /common/mission/add.html
 * 
 */
@Controller("/workshop/mission")
public class MissionController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(MissionController.class);
	
	private MissionService missionService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Mission.sqlId_splitPageFrom);
		render("/workshop/mission/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(MissionValidator.class)
	public void save() {
		getModel(Mission.class).save();
		forwardAction("/workshop/mission/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Mission mission = Mission.dao.findById(getPara());
		setAttr("mission", mission);
		render("/workshop/mission/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(MissionValidator.class)
	public void update() {
		getModel(Mission.class).update();
		forwardAction("/workshop/mission/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Mission mission = Mission.dao.findById(getPara());
		setAttr("mission", mission);
		render("/workshop/mission/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		missionService.baseDelete(Mission.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/mission/backOff");
	}
	
}
