package com.platform.mvc.login;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.platform.annotation.Service;
import com.platform.constant.ConstantInit;
import com.platform.constant.ConstantLogin;
import com.platform.interceptor.AuthInterceptor;
import com.platform.mvc.base.BaseService;
import com.platform.mvc.user.User;
import com.platform.tools.ToolDateTime;
import com.platform.tools.ToolMail;
import com.platform.tools.ToolRandoms;
import com.platform.tools.security.ToolPbkdf2;

@Service(name = LoginService.serviceName)
public class LoginService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(LoginService.class);

	public static final String serviceName = "loginService";

	/**
	 * 验证账号是否存在
	 * @param userIds
	 * @param userName
	 * @return
	 * 描述：新增用户时userIds为空，修改用户时userIds传值
	 */
	public boolean valiUserName(String userIds, String userName){
		return valiByUser(userIds, User.column_username, userName);
	}

	/**
	 * 验证邮箱是否存在
	 * @param userIds
	 * @param mailBox
	 * @return
	 * 描述：新增用户时userIds为空，修改用户时userIds传值
	 */
	public boolean valiMailBox(String userIds, String mailBox){
		return valiByUser(userIds, User.column_email, mailBox);
	}

	/**
	 * 验证身份证是否存在
	 * @param userIds
	 * @param idcard
	 * @return
	 * 描述：新增用户时userIds为空，修改用户时userIds传值
	 */
	public boolean valiIdcard(String userIds, String idcard){
		return valiByUser(userIds, User.column_idcard, idcard);
	}

	/**
	 * 验证手机号是否存在
	 * @param userIds
	 * @param mobile
	 * @return
	 * 描述：新增用户时userIds为空，修改用户时userIds传值
	 */
	public boolean valiMobile(String userIds, String mobile){
		return valiByUser(userIds, User.column_mobile, mobile);
	}
	
	/**
	 * 验证UserInfo表中的某一列是否唯一可用
	 * @param userIds
	 * @param condition
	 * @param value
	 * @return
	 */
	private boolean valiByUser(String userIds, String condition, String value){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("column", "ids");
		param.put("table", "pt_user");
		param.put("condition", condition);
		String sql = getSqlByBeetl("platform.baseModel.select", param);
		List<User> list = User.dao.find(sql, value);
		int size = list.size();
		if(size == 0){
			return true;
		}
		if(size == 1){
			User user = list.get(0);
			if(userIds != null && user.getStr(User.column_ids).equals(userIds)){
				return true;
			}
		}
		if(size > 1){
			return false;
		}
		return false;
	}
	
	/**
	 * 用户登录后台验证
	 * @param request
	 * @param response
	 * @param userName	账号
	 * @param passWord	密码
	 * @param autoLogin	是否自动登录
	 * @return
	 */
	public int login(HttpServletRequest request, HttpServletResponse response, String userName, String passWord, boolean autoLogin) {
		// 1.取用户
		User user = User.cacheGetByUserName(userName);
		if (null == user) {
			return ConstantLogin.login_info_0;// 用户不存在
		}
		
		// 2.停用账户
		String status = user.getStr(User.column_status);
		if (status.equals("0")) {
			return ConstantLogin.login_info_1;
		}

		// 3.密码错误次数超限
		long errorCount = user.getNumber(User.column_errorcount).longValue();
		int passErrorCount = PropKit.getInt(ConstantInit.config_passErrorCount_key);
		if(errorCount >= passErrorCount){
			Date stopDate = user.getDate(User.column_stopdate);
			int hourSpace = ToolDateTime.getDateHourSpace(stopDate, ToolDateTime.getDate());
			int passErrorHour = PropKit.getInt(ConstantInit.config_passErrorHour_key);
			if(hourSpace < passErrorHour){
				return ConstantLogin.login_info_2;// 密码错误次数超限，几小时内不能登录
			}else{
				String sql = getSql(User.sqlId_start);
				Db.use(ConstantInit.db_dataSource_main).update(sql, user.getPKValue());
				// 更新缓存
				User.cacheAdd(user.getPKValue());
			}
		}

		// 4.验证密码
		String saltStr = user.getSalt();			// 密码盐
		byte[] salt = Base64.decodeBase64(saltStr);
		String passStr = user.getPassword();		// 密码
		byte[] encryptedPassword = Base64.decodeBase64(passStr);
		boolean bool = false;
		try {
			bool = ToolPbkdf2.authenticate(passWord, encryptedPassword, salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		if (bool) {
			// 密码验证成功
			AuthInterceptor.setCurrentUser(request, response, user, autoLogin);// 设置登录账户
			return ConstantLogin.login_info_3;
		} else {
			// 密码验证失败
			String sql = getSql(User.sqlId_stop);
			Db.use(ConstantInit.db_dataSource_main).update(sql, ToolDateTime.getSqlTimestamp(ToolDateTime.getDate()), errorCount+1, user.getPKValue());
			// 更新缓存
			User.cacheAdd(user.getPKValue());
			return ConstantLogin.login_info_4;
		}
	}

	/**
	 * 用户登录后台验证
	 * @param request
	 * @param response
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public int pass(HttpServletRequest request, HttpServletResponse response, String userName, String passWord) {
		// 1.取用户
		User user = User.cacheGetByUserName(userName);
		if (null == user) {
			return ConstantLogin.login_info_0;// 用户不存在
		} 
		
		// 2.停用账户
		String status = user.getStr(User.column_status);
		if (status.equals("0")) {
			return ConstantLogin.login_info_1;
		}

		// 3.密码错误次数超限
		long errorCount = user.getNumber(User.column_errorcount).longValue();
		int passErrorCount = PropKit.getInt(ConstantInit.config_passErrorCount_key);
		if(errorCount >= passErrorCount){
			Date stopDate = user.getDate(User.column_stopdate);
			int hourSpace = ToolDateTime.getDateHourSpace(stopDate, ToolDateTime.getDate());
			int passErrorHour = PropKit.getInt(ConstantInit.config_passErrorHour_key);
			if(hourSpace < passErrorHour){
				return ConstantLogin.login_info_2;// 密码错误次数超限，几小时内不能登录
			}else{
				String sql = getSql(User.sqlId_start);
				Db.use(ConstantInit.db_dataSource_main).update(sql, user.getPKValue());
				// 更新缓存
				User.cacheAdd(user.getPKValue());
			}
		}

		// 4.验证密码
		String saltStr = user.getSalt();			// 密码盐
		byte[] salt = Base64.decodeBase64(saltStr);
		String passStr = user.getPassword();		// 密码
		byte[] encryptedPassword = Base64.decodeBase64(passStr);
		boolean bool = false;
		try {
			bool = ToolPbkdf2.authenticate(passWord, encryptedPassword, salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		if (bool) {
			// 密码验证成功
			return ConstantLogin.login_info_3;
		} else {
			// 密码验证失败
			String sql = getSql(User.sqlId_stop);
			Db.use(ConstantInit.db_dataSource_main).update(sql, ToolDateTime.getSqlTimestamp(ToolDateTime.getDate()), errorCount+1, user.getPKValue());
			// 更新缓存
			User.cacheAdd(user.getPKValue());
			return ConstantLogin.login_info_4;
		}
	}
	
	public int resetPass(String email) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("column", User.column_email);
			String sql = getSqlByBeetl(User.sqlId_column, param);
			User user = User.dao.findFirst(sql, email);
			
			if(user == null) {
				return 0;
			}
			
			String newPass = ToolRandoms.getAuthCodeAll(6);
				byte[] saltNew = ToolPbkdf2.generateSalt();// 密码盐
				byte[] encryptedPasswordNew = ToolPbkdf2.getEncryptedPassword(newPass, saltNew);
				user.set(User.column_salt, Base64.encodeBase64String(saltNew));
				user.set(User.column_password, Base64.encodeBase64String(encryptedPasswordNew));
				// 更新用户
				user.update();
				// 缓存
				User.cacheAdd(user.getPKValue());
				
				
			String content = "你好，你的密码已经重置为" + newPass +",请尽快登录后修改密码";
			sendEmail(ToolMail.sendType_text, email, "代购系统重置密码", content);
			return 1;
		} catch (Exception e) {
			log.error("发送邮件重置密码出现异常，邮箱是：" + email, e);
			return 2;
		}
	}
	
	private void sendEmail(String sendType, String to, String subject, String content) {
		String  d_email = "huangtian549@gmail.com",
	            d_uname = "huangtian549@gmail.com",
	            d_password = "hy198665",
	            d_host = "smtp.gmail.com",
	            d_port  = "465";
	    Properties props = new Properties();
	    props.put("mail.smtp.user", d_email);
	    props.put("mail.smtp.host", d_host);
	    props.put("mail.smtp.port", d_port);
	    props.put("mail.smtp.starttls.enable","true");
	    props.put("mail.smtp.debug", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.socketFactory.port", d_port);
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");

	    Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("huangtian549@gmail.com");
                String password = props.getProperty("hy198665");
                return new PasswordAuthentication(userName, password);
            }
        };
	    Session session = Session.getInstance(props, authenticator);
	    session.setDebug(true);

	    MimeMessage msg = new MimeMessage(session);
	    try {
	        msg.setSubject(subject);
	        msg.setFrom(new InternetAddress(d_email));
	        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        msg.setText(content);

	        	Transport transport = session.getTransport("smtps");
	            transport.connect(d_host, Integer.valueOf(d_port), d_uname, d_password);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();

	        } catch (AddressException e) {
	            e.printStackTrace();
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	}

}
