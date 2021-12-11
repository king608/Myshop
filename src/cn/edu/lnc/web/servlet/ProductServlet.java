package cn.edu.lnc.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cn.edu.lnc.domain.Cart;
import cn.edu.lnc.domain.CartItem;
import cn.edu.lnc.domain.Category;
import cn.edu.lnc.domain.PageBean;
import cn.edu.lnc.domain.Product;
import cn.edu.lnc.service.ProductService;
import cn.edu.lnc.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

public class ProductServlet extends BeasServlet {
	
	
	//清空购物车
	public void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//删除域
		session.removeAttribute("cart");
		//跳转回cart.jsp
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}
	
	//删除商品
	public void delFromProductCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart!=null){
			Map<String, CartItem> cartItemList = cart.getCartItemList();
			//修改总计
			cart.setTotal(cart.getTotal()-cartItemList.get(pid).getSubtotal());
			//删除商品
			cartItemList.remove(pid);
			cart.setCartItemList(cartItemList);
		}
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}
	
	
	//购物车信息
	public void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService service = new ProductService();
		HttpSession session = request.getSession();
		//获得商品pid
		String pid = request.getParameter("pid");
		//获取用户购买的数量
		String buyNum = request.getParameter("buyNum");
		int count = Integer.parseInt(buyNum);
		//创建购物车对象
		CartItem cartItems = new CartItem();
		//获取商品对象
		Product product = service.finProductInfoPid(pid);
		//存储商品数量
		cartItems.setCount(count);
		//存储小计（数量*单价）
		double subtotal=count*product.getShop_price();
		cartItems.setSubtotal(subtotal);
		//存储商品
		cartItems.setProduct(product);
		//从session域取出cart，没有就创建
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null){
			cart = new Cart();
		}
		
		Map<String, CartItem> cartItemList = cart.getCartItemList();
		//判断商品添加是否为同一件
		if(cartItemList.containsKey(pid)){
			//是-取出原来的商品
			CartItem cartItem = cartItemList.get(pid);
			//更新数量
			int oldCount = cartItem.getCount();
			oldCount+=count;
			cartItem.setCount(oldCount);
			//更新小计
			double oldSubtotal = cartItem.getSubtotal();
			oldSubtotal+=subtotal;
			cartItem.setSubtotal(oldSubtotal);
			
		}else{	
			cart.getCartItemList().put(pid, cartItems);
		}
		
		//存储总计
		double total = cart.getTotal()+subtotal;
		cart.setTotal(total);
		//存储购物车对象
		
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}
	
	//异步展示浏览记录
	public void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService service = new ProductService();
		//获取cookie
		Cookie[] cookies = request.getCookies();
		//定义一个集合获得product的数据
		List<Product> historyProductList = new ArrayList<Product>();
		
		if(cookies!=null){		
			for (Cookie cookie : cookies) {
				if("pids".equals(cookie.getName())){
					//取出cookie，并分割字符串，获得pid
					String pids = cookie.getValue();
					String[] split = pids.split("-");
					for (String pid : split) {
						Product list = service.finProductInfoPid(pid);
						historyProductList.add(list);
					}
				}
			}
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(historyProductList);
		System.out.println(json);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		
	}
	
	
	//返回上一页商品
	public void productList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 每页展示条数
		int currentCount = 12;
		// 第几页
		String pageStr = request.getParameter("currentPage");
		if (pageStr == null)
			pageStr = "1";
		int currentPage = Integer.parseInt(pageStr);

		String cid = request.getParameter("cid");
		ProductService service = new ProductService();
		PageBean pageBean = service.productListCid(cid, currentCount, currentPage);

		// 存域，并转发给product_list.jsp
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		
		
		String pid = request.getParameter("pid");
		String pids=pid;
		// 获取cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if("pids".equals(cookie.getName())){
					pids = cookie.getValue();
					String[] split = pids.split("-");
					List<String> asList = Arrays.asList(split);
					LinkedList<String> list = new LinkedList<String>(asList);
					if(list.contains(pid)){
						list.remove(pid);
					}
					list.addFirst(pid);
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < list.size()&&i<7; i++) {
						sb.append(list.get(i)).append("-");
					}
					pids = sb.substring(0, sb.length()-1);
				}
			}
		}
		Cookie cookie_pids = new Cookie("pids",pids);
		response.addCookie(cookie_pids);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
		
		
		
	}
	//按照名字搜索全部商品
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("search");
		ProductService service = new ProductService();
		List<Product> searchProduct = service.findsearchProduct(pname);
		
		request.setAttribute("searchProduct",searchProduct);
		request.setAttribute("search", pname);
		request.getRequestDispatcher("/search_list.jsp").forward(request, response);

	}
	
	//模糊搜索商品
	public void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pname = request.getParameter("word");
		//查询关键字所有商品
		ProductService service = new ProductService();
		List<Object> serchPro = service.getSerchProduct(pname);
		
		//用gson转换工具封装集合
		Gson gson = new Gson();
		String json = gson.toJson(serchPro);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		
	}
	
	
	//查看某个商品的详细信息--
	public void productInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		//页数
		String currentPage = request.getParameter("currentPage");
		String cid = request.getParameter("cid");
		
	/*	//模糊--关键字
		String pname = request.getParameter("search");*/
		
		ProductService service = new ProductService();
		Product productInfo = service.finProductInfoPid(pid);
		
		//存储页数和关键字--页数是为了返回当前页，关键字是为了返回搜索的关键字页
		request.setAttribute("productInfo", productInfo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cid", cid);
		request.setAttribute("pid", pid);
//		request.setAttribute("search", pname);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		
	}
	
	//按分类的id展示商品
	public void productListCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//每页展示条数
		int currentCount = 12;
		//第几页
		String pageStr = request.getParameter("currentPage");
		if(pageStr == null)pageStr="1";
		int currentPage = Integer.parseInt(pageStr);
		
		String cid = request.getParameter("cid");
		ProductService service = new ProductService();
		PageBean pageBean = service.productListCid(cid ,currentCount,currentPage);
		
		//存域，并转发给product_list.jsp
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}
	
	
	public void category(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查找product的所有分类
		ProductService service = new ProductService();
		
		// 先从缓存中查询categoryList 如果有直接使用 没有在从数据库中查询 存到缓存中
		// 1、获得jedis对象 连接redis数据库
		Jedis jedis = JedisPoolUtils.getJedis();
		String categoryListJson = jedis.get("categoryListJson");
		// 2、判断categoryListJson是否为空
		if (categoryListJson == null) {
			System.out.println("缓存没有数据 查询数据库");
			// 准备分类数据
			List<Category> categoryList = service.category();
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categoryList);
			jedis.set("categoryListJson", categoryListJson);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
		
	}
	
	
	
	//index
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService service = new ProductService();
		
		//获取热门商品
		List<Product> productHot = service.findProductHot();
		
		//获取最新商品
		List<Product> productNews = service.findProductNews();
		
		//存域并转发
		request.setAttribute("productHot", productHot);
		request.setAttribute("productNews",productNews);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

	
}