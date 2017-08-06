package com.platform.mvc.user;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Controller;
import com.platform.dto.ZtreeNode;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.dept.Department;
import com.platform.mvc.station.Station;
import com.platform.mvc.upload.Upload;
import com.platform.mvc.upload.UploadService;
import com.platform.tools.ToolRandoms;
import com.platform.tools.ToolString;

/**
 * 用户管理
 */
@Controller("/platform/user")
public class UserController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(UserController.class);
	
	private UserService userService;
	private UploadService uploadService;
	
	/**
	 * 默认列表
	 */
	public void index() {
		paging(splitPage, User.sqlId_splitPageSelect, User.sqlId_splitPageFrom);
		render("/platform/user/list.html");
	}
	
	/**
	 * 保存新增用户
	 */
	@Before(UserValidator.class)
	public void save() {
		String ids = ToolRandoms.getUuid(true);
		
		List<UploadFile> files = getFiles("files" + File.separator + "upload", 1 * 1024 * 1024, ToolString.encoding); // 1M
		if(files != null && files.size() != 0){
			uploadService.upload("webRoot", files.get(0), ids);
		}

		String password = getPara("password");
		User user = getModel(User.class);
		UserInfo userInfo = getModel(UserInfo.class);
		
		userService.save(ids, user, password, userInfo);

		forwardAction("/platform/user/backOff");
	}
	
	
	/**
	 * 保存新增用户
	 */
	public void reg() {
		String ids = ToolRandoms.getUuid(true);
		
		String username = getPara("username");
		String password = getPara("password");
		User user = getModel(User.class);
		user.setUsername(username);
		user.setDepartmentids("002be6310d8a46ec8a314b3c1fb9c82f");
		user.setStationids("a5c8effa0971437b9f440cfd8e662bb3");
		user.setStopdate(new Timestamp(System.currentTimeMillis()) );
		UserInfo userInfo = getModel(UserInfo.class);
		
		userService.save(ids, user, password, userInfo);
		
		renderText("1");;

	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		User user = User.dao.findById(getPara());
		setAttr("user", user);
		setAttr("userInfo", UserInfo.dao.findById(user.getPKValue()));
		setAttr("station", Station.dao.findById(user.getStationids()));
		setAttr("dept", Department.dao.findById(user.getDepartmentids()));
		setAttr("upload", Upload.dao.findById(user.getPKValue()));
		render("/platform/user/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(UserValidator.class)
	public void update() {
		List<UploadFile> files = getFiles("files" + File.separator + "upload", 1 * 1024 * 1024, ToolString.encoding); // 1M

		String password = getPara("password");
		User user = getModel(User.class);
		UserInfo userInfo = getModel(UserInfo.class);
		
		if(files != null && files.size() == 1){
			// 删除旧LOGO
			Upload.dao.deleteById(user.getPKValue());
			
			// 存入新LOGO
			uploadService.upload("webRoot", files.get(0), user.getPKValue());
		}
		
		userService.update(user, password, userInfo);
		forwardAction("/platform/user/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		User user = User.dao.findById(getPara());
		setAttr("user", user);
		setAttr("userInfo", user.getUserInfo());
		setAttr("station", Station.dao.findById(user.getStationids()));
		setAttr("dept", Department.dao.findById(user.getDepartmentids()));
		setAttr("upload", Upload.dao.findById(user.getPKValue()));
		render("/platform/user/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		userService.delete(getPara() == null ? ids : getPara());
		forwardAction("/platform/user/backOff");
	}

	/**
	 * 用户树ztree节点数据
	 */
	public void treeData() {
		List<ZtreeNode> list = userService.childNodeData(getCxt(), getPara("deptIds"));
		renderJson(list);
	}
	
	/**
	 * 验证旧密码是否正确
	 */
	public void valiPassWord(){
		String passWord = getPara("passWord");
		boolean bool = userService.valiPassWord(ids, passWord);
		renderText(String.valueOf(bool));
	}
	
	/**
	 * 密码变更
	 */
	public void passChange(){
		String userName = getPara("userName");
		String passOld = getPara("passOld");
		String passNew = getPara("passNew");
		userService.passChange(userName, passOld, passNew);
		renderText("");
	}
	
}


