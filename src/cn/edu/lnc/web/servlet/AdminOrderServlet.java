package cn.edu.lnc.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.edu.lnc.domain.Order;
import cn.edu.lnc.service.AdminProductService;

public class AdminOrderServlet extends AdminBeasServlet {
	
	
	//展示每张订单的详情
	public void findOrderInfoByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String oid = request.getParameter("oid");
		
		AdminProductService service = new AdminProductService();
		List<Map<String, Object>> orderList = service.findOrderInfoByOid(oid);
		
		Gson gson = new Gson();
		String json = gson.toJson(orderList);
		System.out.println(json);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		
	}

	//展示订单
	public void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminProductService service = new AdminProductService();
		List<Order> orders = service.findAllOrder();
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/admin/order/list.jsp").forward(request, response);
		
	}

	
}