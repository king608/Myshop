package cn.edu.lnc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.edu.lnc.domain.Category;
import cn.edu.lnc.domain.Order;
import cn.edu.lnc.domain.Product;
import cn.edu.lnc.utils.DataSourceUtils;

public class AdminProductDao {

	//查找所有的分类
	public List<Category> findCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	//添加分类
	public void addCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?,?)";
		runner.update(sql, category.getCid(),category.getCname());
		
	}

	//按id查分类
	public Category findCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid=?";
		return runner.query(sql, new BeanHandler<Category>(Category.class),cid);
	}

	//更新分类信息
	public void updateCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname=? where cid=?";
		runner.update(sql , category.getCname(),category.getCid());
	}

	//删除分类
	public void delCategory(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category where cid=?";
		runner.update(sql,cid);
	}

	public List<Product> findProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	//添加商品
	public void addProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql,product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),
				product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),
				product.getCategory().getCid());
		
	}

	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?";
		return runner.query(sql, new BeanHandler<Product>(Product.class),pid);

	}

	public void updateProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,is_hot=?,pdesc=?,cid=? where pid=?";
		runner.update(sql , product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getCategory().getCid(),product.getPid());
		
	}

	public void delProduct(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where pid=?";
		runner.update(sql,pid);
	}

	public List<Order> findAllOrder() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders";
		return runner.query(sql, new BeanListHandler<Order>(Order.class));
	}

	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select p.pimage,p.pname,p.shop_price,i.count,i.subtotal "+
				" from orderitem i,product p "+
				" where p.pid=i.pid and i.oid=?";
	return runner.query(sql, new MapListHandler(),oid);
	}

}
