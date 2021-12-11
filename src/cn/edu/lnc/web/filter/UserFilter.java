package cn.edu.lnc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lnc.domain.User;

public class UserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//校验用户是否登录----校验session是否存在user对象
		HttpSession session = request.getSession();
		//判断用户是否已经登录 未登录下面代码不执行
		User user = (User) session.getAttribute("user");
		if(user==null){
			//没有登陆
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		chain.doFilter(request, response);
		
	}

	

}
