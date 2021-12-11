package cn.edu.lnc.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.edu.lnc.dao.AdminProductDao;
import cn.edu.lnc.domain.Category;
import cn.edu.lnc.domain.Order;
import cn.edu.lnc.domain.Product;

public class AdminProductService {

	public List<Category> findCategory() {
		AdminProductDao dao = new AdminProductDao();
		List<Category> category =null;
		try {
			category = dao.findCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	
	public void addCategory(Category category) {
		AdminProductDao dao = new AdminProductDao();
		try {
			dao.addCategory(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Category findCategoryByCid(String cid) {
		AdminProductDao dao = new AdminProductDao();
		Category category =null;
		try {
			category = dao.findCategoryByCid(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}


	public void updateCategory(Category category) {
		AdminProductDao dao = new AdminProductDao();
		try {
			dao.updateCategory(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void delCategory(String cid) {
		AdminProductDao dao = new AdminProductDao();
		try {
			dao.delCategory(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public List<Product> findProduct() {
		AdminProductDao dao = new AdminProductDao();
		List<Product> productList =null;
		try {
			productList = dao.findProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}


	public void addProduct(Product product) {
		AdminProductDao dao = new AdminProductDao();
		try {
			dao.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Product findProductByPid(String pid) {
		AdminProductDao dao = new AdminProductDao();
		Product product =null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}


	public void updateProduct(Product product) {
		AdminProductDao dao = new AdminProductDao();
		try {
			dao.updateProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void delProduct(String pid) {
		AdminProductDao dao = new AdminProductDao();
		try {
			dao.delProduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Order> findAllOrder() {
		AdminProductDao dao = new AdminProductDao();
		List<Order> orders =null;
		try {
			orders = dao.findAllOrder();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}


	public List<Map<String, Object>> findOrderInfoByOid(String oid) {
		AdminProductDao dao = new AdminProductDao();
		List<Map<String, Object>> map =null;
		try {
			map = dao.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
