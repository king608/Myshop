package cn.edu.lnc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("all")
public class BeasServlet extends HttpServlet {

	
	protected void service(HttpServletRequest request, HttpServletResponse respont) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//获取method对象
		String methodName = request.getParameter("method");

		try {
			//1,获取当前访问的对象字节码对象
			Class clazz = this.getClass();
			//2,获取当前字节码对象中的指定方法
			Method method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//3,执行相应的方法
			method.invoke(this,request, respont);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
