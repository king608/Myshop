package cn.edu.lnc.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import cn.edu.lnc.domain.User;
import cn.edu.lnc.service.UserService;
import cn.edu.lnc.utils.CommonsUtils;
import cn.edu.lnc.utils.MailUtils;

public class UserServlet extends BeasServlet {
	
	//注销
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//从session中删除user
		session.removeAttribute("user");
		
		//设置cookie储存username和password并设置为0,
		Cookie cookie_username = new Cookie("cookie_username", "");
		cookie_username.setMaxAge(0);
		Cookie cookie_password = new Cookie("cookie_password", "");
		cookie_password.setMaxAge(0);
		
		response.addCookie(cookie_username);
		response.addCookie(cookie_password);
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	
	
	//登录
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//获取username，password
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals("admin")&&password.equals("admin")){
			response.sendRedirect(request.getContextPath()+"/admin/home.jsp");
			return;
		}
		
		//传递数据-判断user是否存在
		UserService service = new UserService();
		User user = service.login(username ,password);
		
		//判断user是否登录成功--user是否为空
		if(user != null){
			//登录成功
			//判断用户是否点击自动登录
			String autoLogin = request.getParameter("autoLogin");
			if("autoLogin".equals(autoLogin)){
				//要自动登录--设置cookie储存username和password并设置持久化
				Cookie cookie_username = new Cookie("cookie_username", user.getUsername());
				cookie_username.setMaxAge(10*60);
				Cookie cookie_password = new Cookie("cookie_password", user.getPassword());
				cookie_password.setMaxAge(10*60);
				
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
			}
			
			//存user到session域
			session.setAttribute("user", user);
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{
			request.setAttribute("loginError", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}
	
	
	
	//验证用户名是否存在
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		
		UserService service = new UserService();
		boolean isExist = service.checkUsername(username);
		
		//写回去给ajax
		String json= "{\"isExist\":"+isExist+"}";
		
		response.getWriter().write(json);
		
	}
	
	
	//注册用户
	public void addRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		//获取表单的所有数据
		Map<String, String[]> parameterMap = request.getParameterMap();
		//创建User实体类
		User user = new User();
		//定义一个时间转换器String转Data
		ConvertUtils.register(new Converter() {
			
			@Override
			public Object convert(Class clazz, Object obj) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ss");
				Date parse =null;
				try {
					parse = format.parse(obj.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return parse;
			}
		}, Date.class);
		
		
		try {
			//用BeaUtils进行映射封装，对应表单里的字段
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//对没有的数据进行封装
		user.setUid(CommonsUtils.getUUID());
		user.setState(0);
		String activeCode = CommonsUtils.getUUID();
		user.setCode(activeCode);
		user.setTelephone(null);
		
		//传输数据到service层
		UserService service = new UserService();
		boolean inRegisterSource = service.register(user);
		//注册--true发送邮件---false跳转到错误页
		if(inRegisterSource){
			String emailMsg = "<a href='http://localhost:8080/"+request.getContextPath()+"/user?method=active&activeCode="+activeCode+"'>"+
								"http://localhost:8080/"+request.getContextPath()+"/user?method=active&activeCode="+activeCode+"</a>";
			
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
		
	}

	//激活账户
	public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取激活码
		String code = request.getParameter("activeCode");
		
		//修改激活码状态
		UserService service = new UserService();
		service.activeCode(code);
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}
}