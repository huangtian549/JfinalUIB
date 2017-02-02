package com.workshop.mvc.freelancer;

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
 * /workshop/freelancer
 * /workshop/freelancer/save
 * /workshop/freelancer/edit
 * /workshop/freelancer/update
 * /workshop/freelancer/view
 * /workshop/freelancer/delete
 * /common/freelancer/add.html
 * 
 */
@Controller("/workshop/freelancer")
public class FreelancerController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(FreelancerController.class);
	
	private FreelancerService freelancerService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Freelancer.sqlId_splitPageFrom);
		render("/workshop/freelancer/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(FreelancerValidator.class)
	public void save() {
		getModel(Freelancer.class).save();
		forwardAction("/workshop/freelancer/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Freelancer freelancer = Freelancer.dao.findById(getPara());
		setAttr("freelancer", freelancer);
		render("/workshop/freelancer/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(FreelancerValidator.class)
	public void update() {
		getModel(Freelancer.class).update();
		forwardAction("/workshop/freelancer/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Freelancer freelancer = Freelancer.dao.findById(getPara());
		setAttr("freelancer", freelancer);
		render("/workshop/freelancer/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		freelancerService.baseDelete(Freelancer.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/freelancer/backOff");
	}
	
}
