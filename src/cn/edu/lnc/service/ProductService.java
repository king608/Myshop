package cn.edu.lnc.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.lnc.dao.ProductDao;
import cn.edu.lnc.domain.Category;
import cn.edu.lnc.domain.Order;
import cn.edu.lnc.domain.PageBean;
import cn.edu.lnc.domain.Product;
import cn.edu.lnc.utils.DataSourceUtils;

public class ProductService {

	//查找热门商品
	public List<Product> findProductHot() {
		ProductDao dao = new ProductDao();
		List<Product> productHot =null;
		try {
			productHot = dao.findProductHot();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productHot;
	}

	//查找最新商品
	public List<Product> findProductNews() {
		ProductDao dao = new ProductDao();
		List<Product> productNews =null;
		try {
			productNews = dao.findProductNews();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productNews;
	}

	//查找product的所有分类
	public List<Category> category() {
		ProductDao dao = new ProductDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.category();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}

	//按分类的id展示商品
	public PageBean productListCid(String cid, int currentCount, int currentPage) {
		
		PageBean pageBean = new PageBean();
		ProductDao dao = new ProductDao();
		//储存页数
//		int currentPage =1;
		pageBean.setCurrentPage(currentPage);
		//储存条数
//		int currentCount = 12;
		pageBean.setCurrentCount(currentCount);
		//存储总条数
		int totalCount =0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		//存储总页数
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		
		//储存页里的数据
		List<Product> productList =null;
		try {
			int index = (currentPage-1)*currentCount;
			productList = dao.productListCid(cid,index,currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setList(productList);
		return pageBean;
	}

	//查看某个商品的详细信息
	public Product finProductInfoPid(String pid) {
		ProductDao dao = new ProductDao();
		Product productInfo = null;
		try {
			productInfo = dao.finProductInfoPid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productInfo;
	}

	public List<Object> getSerchProduct(String pname) {
		ProductDao dao = new ProductDao();
		List<Object> serchPro =null;
		try {
			serchPro = dao.getSerchProduct(pname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serchPro;
	}

	public List<Product> findsearchProduct(String pname) {
		ProductDao dao = new ProductDao();
		List<Product> searchProduct =null;
		try {
			searchProduct = dao.findsearchProduct(pname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchProduct;
	}

	//提交订单 将订单的数据和订单项的数据存储到数据库中
	public void submitOrder(Order order) {
		ProductDao dao = new ProductDao();
		try {
			//开启事务
			DataSourceUtils.startTransaction();
			dao.addOrder(order);
			dao.addOrderItem(order);
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void updateOrderAdrr(Order order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderAdrr(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Order> findOrders(String uid) {
		ProductDao dao = new ProductDao();
		List<Order> order=null;
		try {
			order = dao.findOrders(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	public List<Map<String, Object>> findOrderItems(String oid) {
		ProductDao dao = new ProductDao();
		List<Map<String, Object>> list=null;
		try {
			list = dao.findOrderItems(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

}
