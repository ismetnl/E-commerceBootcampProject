package com.ibtech.sales.inventory.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibtech.sales.inventory.entity.CartProduct;

public class CartProductManager extends BaseManager<CartProduct>{
	
	@Override
	protected CartProduct parse(ResultSet resultSet) throws SQLException {
		
		long cartProductId = resultSet.getLong("cartProductId");
		long cartId =  resultSet.getLong("cartId");
		long productId = resultSet.getLong("productId");
		int salesQuantity = resultSet.getInt("salesQuantity");
		double salesPrice = resultSet.getDouble("salesPrice");
		float taxRate = resultSet.getFloat("taxRate");
	
		CartProduct cartProduct = new CartProduct();
		
		cartProduct.setCartProductId(cartProductId);
		cartProduct.setCartId(cartId);
		cartProduct.setProductId(productId);
		cartProduct.setSalesPrice(salesPrice);
		cartProduct.setSalesQuantity(salesQuantity);
		cartProduct.setTaxRate(taxRate);
		
		
		return cartProduct;
	}
	
	public boolean delete(long cartProductId) throws SQLException {
		
		connect();
		
		String sql = "delete from cartProduct where cartProductId=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartProductId);
		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);

		return affected > 0;
	}
	
	public List<CartProduct> list(long cartid) throws SQLException{
		
		List<CartProduct> cartProductList = new ArrayList<>();
		
		connect();

		String sql = "select * from cartProduct c where c.cartid = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartid);
		ResultSet resultSet = statement.executeQuery();
		
		cartProductList = parseList(cartProductList, resultSet);
		
		disconnect();
		
		return cartProductList;
		
	}
	
	public CartProduct find(long cartProductId) throws SQLException{
		
		CartProduct cartProduct = null;
		
		connect();

		String sql = "select * from cartProduct c where c.cartProductId = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartProductId);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()){
			cartProduct = parse(resultSet);
			disconnect();
			return cartProduct;
		}
		
		disconnect();
		
		return cartProduct;
		
	}
	
	public List<CartProduct> getListRecord(long cartid,int start,int total) throws SQLException{
		
		List<CartProduct> cartProductList = new ArrayList<>();
		
		connect();

		String sql = "select * from cartProduct c where c.cartid = ? limit ? offset ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartid);
		statement.setInt(2, total);
		statement.setInt(3, start);
		ResultSet resultSet = statement.executeQuery();
		
		cartProductList = parseList(cartProductList, resultSet);
		
		disconnect();
		
		return cartProductList;
		
	}
	
	public boolean update(CartProduct cartProduct) throws SQLException {

		connect();

		String sql = "update cartProduct set salesQuantity=? where cartProductId=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, cartProduct.getSalesQuantity());
		statement.setLong(2, cartProduct.getCartProductId());
		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);
		
		return affected > 0;
	}
	
	
	public long insert(CartProduct cartProduct) throws SQLException {

		long cartProductId = 0;
		
		connect();

		String sql = "insert into cartProduct(cartId,productId,salesQuantity,salesPrice,taxRate) values(?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, cartProduct.getCartId());
		statement.setLong(2, cartProduct.getProductId());
		statement.setInt(3, cartProduct.getSalesQuantity());
		statement.setDouble(4, cartProduct.getSalesPrice());
		statement.setDouble(5, cartProduct.getTaxRate());
		statement.executeUpdate();

		ResultSet resultSet = statement.getGeneratedKeys();
		
		while(resultSet.next()){
			cartProductId = resultSet.getLong("cartProductId");
		}
		
		
		disconnect();
		
		
		return cartProductId;
	}

}
