package cn.edu.lnc.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.edu.lnc.domain.Cart;
import cn.edu.lnc.domain.CartItem;
import cn.edu.lnc.domain.Order;
import cn.edu.lnc.domain.OrderItem;
import cn.edu.lnc.domain.Product;
import cn.edu.lnc.domain.User;
import cn.edu.lnc.service.ProductService;
import cn.edu.lnc.utils.CommonsUtils;

public class OrderServlet extends BeasServlet {
	
	

	//用户的订单信息
	public void userOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//获取user，传递uid获取到user的所有订单
		ProductService service = new ProductService();
		List<Order> orders = service.findOrders(user.getUid());
		//循环每一个订单为其补充完整
		for (Order order : orders) {
			String oid = order.getOid();
			//获取每一个订单下的所有子订单项
			List<Map<String, Object>> list = service.findOrderItems(oid);
			//补全orderitem
			for (Map<String, Object> map : list) {
				//用BeanUtils映射封装数据
				try {
					OrderItem item = new OrderItem();
					BeanUtils.populate(item, map);
					Product product = new Product();
					BeanUtils.populate(product, map);
					//把封装的数据存储回OrderItem
					item.setProduct(product);
					//把封装的数据存储回Order
					order.getOrderItems().add(item);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}

			}
		}
		//封装order，并转发到order——list.jsp
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
	}
	
	
	//生成订单
	public void confrimOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Order order = new Order();
		try {
			BeanUtils.populate(order, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductService service = new ProductService();
		service.updateOrderAdrr(order);
		
		response.sendRedirect(request.getContextPath()+"/order.jsp");
		
	}
	
	
	//提交购物车信息
	public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		
		Order order = new Order();
		//储存数据
		String oid = CommonsUtils.getUUID();
		order.setOid(oid);
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setState(0);
		order.setName(null);
		order.setTelephone(null);
		order.setAddress(null);
		order.setUser(user);
		//储存订单的信息
		
		Map<String, CartItem> cartItemList = cart.getCartItemList();
		for(Entry<String, CartItem> entry : cartItemList.entrySet()){
			CartItem value = entry.getValue();
			OrderItem item = new OrderItem();
			item.setItemid(CommonsUtils.getUUID());
			item.setCount(value.getCount());
			item.setSubtotal(value.getSubtotal());
			item.setProduct(value.getProduct());
			item.setOrder(order);
			order.getOrderItems().add(item);	
		}
		
		//order对象封装完毕
		// 传递数据到service层
		ProductService service = new ProductService();
		service.submitOrder(order);

		session.setAttribute("order", order);

		// 页面跳转
		response.sendRedirect(request.getContextPath()+"/order_info.jsp");
		
	}

	
}