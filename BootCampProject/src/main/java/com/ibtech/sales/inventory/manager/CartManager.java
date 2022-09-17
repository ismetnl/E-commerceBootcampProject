package com.ibtech.sales.inventory.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibtech.sales.inventory.entity.Cart;
import com.ibtech.sales.inventory.entity.CartProduct;
import com.ibtech.sales.inventory.entity.Category;

public class CartManager extends BaseManager<Cart>{
	
	public CartManager() {
		super();
	}

	@Override
	protected Cart parse(ResultSet resultSet) throws SQLException {
		
		Cart cart = new Cart();
		
		long cartId = resultSet.getLong("cartId");
		double totalAmount = resultSet.getLong("totalAmount");
		String CustomerName = resultSet.getString("CustomerName");
		
		cart.setCartId(cartId);
		cart.setTotalAmount(totalAmount);
		cart.setCustomerName(CustomerName);
		
		return cart;
	}
	
	public long insert(Cart cart) throws SQLException {
		
		long cartId = -1;
		
		connect();
		
		String sql = "insert into cart(totalAmount,CustomerName) values(?,?)";
		PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		statement.setDouble(1, cart.getTotalAmount());
		statement.setString(2, cart.getCustomerName());
		statement.executeUpdate();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while(resultSet.next()){
			cartId = resultSet.getLong(1);
		}
		
		
		disconnect();
		
		
		return cartId;
	}
	
	public List<Cart> list() throws SQLException {

		List<Cart> cartList = new ArrayList<>();

		connect();

		String sql = "select * from cart";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		cartList = parseList(cartList, resultSet);

		disconnect();

		return cartList;
	}
	
	public List<Cart> getListRecord(int start,int total) throws SQLException {

		List<Cart> cartList = new ArrayList<>();

		connect();

		String sql = "select * from cart limit ? offset ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, total);
		statement.setInt(2, start);
		ResultSet resultSet = statement.executeQuery();
		
		cartList = parseList(cartList, resultSet);

		disconnect();

		return cartList;
	}
	
	public boolean update(Cart cart) throws SQLException {

		connect();

		String sql = "update cart set totalamount=?, customername=? where cartId=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDouble(1,	cart.getTotalAmount());
		statement.setString(2, cart.getCustomerName());
		statement.setLong(3,  cart.getCartId());
		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);
		
		return affected > 0;
	}
	
	
}
