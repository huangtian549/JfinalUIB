package com.workshop.mvc.student;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolDateTime;
import com.platform.tools.ToolWord;

/**
 * XXX 管理	
 * 描述：
 * 
 * /workshop/student
 * /workshop/student/save
 * /workshop/student/edit
 * /workshop/student/update
 * /workshop/student/view
 * /workshop/student/delete
 * /common/student/add.html
 * 
 */
@Controller("/workshop/student")
public class StudentController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(StudentController.class);
	
	private StudentService studentService;
	
	private static String allowSuffix = "jpg,png,gif,jpeg,doc,docx";
	
	String destDir = "/home/data/workshop/";
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Student.sqlId_splitPageFrom);
		render("/workshop/student/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(StudentValidator.class)
	public void save() {
		Student student = getModel(Student.class);
		student.setCreate_date(ToolDateTime.getSqlTimestamp());
		String[] types = getParaValues("type" );
		String type = Arrays.toString(types).replace("[","").replace("]","");
		student.setType(type);
		student.save(true);
		forwardAction("/workshop/student/backOff");
	}
	
	
//	public void uploadFile() {
//		String dateString = ToolDateTime.getDateString(new Date(), ToolDateTime.SHORT_DATE_FORMAT_NO_DASH);
//		dateString = "workshop/"  + dateString;
//		
//		List<UploadFile> files = getFiles("/home/data/" + dateString + File.separator, 10 * 1024 * 1024, ToolString.encoding); // 10M
//		if (files != null) {
//			log.info("size:" + files.size());
//		}
//		renderText(files.get(0).getUploadPath() + files.get(0).getFileName() + ",");
//	}
	
	public void uploadFile(MultipartFile file,HttpServletRequest request) throws Exception {
        String picUrl = "";
        try {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
                String fileOriginalName = file.getOriginalFilename();
                int length = allowSuffix.indexOf(suffix);
                if(length == -1){
                    throw new Exception("请上传允许格式的文件");
                }
                if(file.getSize() > 5000000L){
                    throw new Exception("您上传的文件大小已经超出范围");
                }
                 
                File destFile = new File(destDir  + "/");
                if(!destFile.exists()){
                    destFile.mkdirs();
                }
                File f = new File(destFile.getAbsoluteFile()+"/"+fileOriginalName);
                if (f.exists()) {
                	Date date = new Date();
            		String dateString = ToolDateTime.getDateString(new Date(), ToolDateTime.SHORT_DATE_FORMAT_NO_DASH);
                	f = new File(destFile.getAbsoluteFile()+"/" + dateString + fileOriginalName);
				}
                String fileNameNew = f.getName();
                file.transferTo(f);
                f.createNewFile();
                
                //转换成html
                ToolWord.convertWord2Html(f.getAbsolutePath(), destDir + "html/");
                
               picUrl = "/data/workshop/" + fileNameNew;
        } catch (Exception e) {
            throw e;
        }
        renderText(picUrl);;
    }
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Student student = Student.dao.findById(getPara());
		setAttr("student", student);
		render("/workshop/student/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(StudentValidator.class)
	public void update() {
		getModel(Student.class).update();
		forwardAction("/workshop/student/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Student student = Student.dao.findById(getPara());
		setAttr("student", student);
		render("/workshop/student/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		studentService.baseDelete(Student.table_name, getPara() == null ? ids : getPara());
		forwardAction("/workshop/student/backOff");
	}
	
}
