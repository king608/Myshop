package cn.edu.lnc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.lnc.domain.Category;
import cn.edu.lnc.domain.Order;
import cn.edu.lnc.domain.OrderItem;
import cn.edu.lnc.domain.Product;
import cn.edu.lnc.utils.DataSourceUtils;
import javafx.scene.layout.ColumnConstraints;

public class ProductDao {

	//查找热门商品
	public List<Product> findProductHot() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot=? limit ?,?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),1,0,9);
	}
	
	//查找最新商品
	public List<Product> findProductNews() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate desc limit ?,? ";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),0,9);
	}

	//查找所有分类
	public List<Category> category() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	//按分类的id展示商品
	public List<Product> productListCid(String cid, int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=? limit ?,?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),cid,index,currentCount);
	}

	public int getCount(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(),cid);
		return query.intValue();
	}

	//查看某个商品的详细信息
	public Product finProductInfoPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?";
		return runner.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	public List<Object> getSerchProduct(String pname) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pname like ? limit ?,?";
		return runner.query(sql, new ColumnListHandler("pname"),"%"+pname+"%",0,8);
	}

	public List<Product> findsearchProduct(String pname) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pname like ?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),"%"+pname+"%");
	}

	//储存orderitem信息
	public void addOrderItem(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn = DataSourceUtils.getConnection();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			
			runner.update(conn,sql, orderItem.getItemid(),orderItem.getCount(),orderItem.getSubtotal(),
					orderItem.getProduct().getPid(),orderItem.getOrder().getOid());
		}
	}

	//储存orders信息
	public void addOrder(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn = DataSourceUtils.getConnection();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		runner.update(conn, sql,order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),
						order.getName(),order.getTelephone(),order.getUser().getUid());
	}

	//更新订单信息
	public void updateOrderAdrr(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set address=?,name=?,telephone=? where oid=?";
		runner.update(sql, order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}

	//查询uid的所有订单
	public List<Order> findOrders(String uid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid=?";
		return runner.query(sql, new BeanListHandler<Order>(Order.class),uid);
	}

	//多表查询订单信息
	public List<Map<String, Object>> findOrderItems(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select p.pimage,p.pname,p.shop_price,i.count,i.subtotal "+
					 " from product p,orderitem i "+
					 " where p.pid=i.pid and i.oid=?";
		return runner.query(sql, new MapListHandler(),oid);
	}

}
