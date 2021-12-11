package cn.edu.lnc.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	//总数
	private double total;
	//该购物车中存储的n个购物项
	private Map<String, CartItem> cartItemList = new HashMap<String, CartItem>();
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Map<String, CartItem> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(Map<String, CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}
	
}
