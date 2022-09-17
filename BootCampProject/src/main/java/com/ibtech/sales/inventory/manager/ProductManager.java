package com.ibtech.sales.inventory.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibtech.sales.inventory.entity.Category;
import com.ibtech.sales.inventory.entity.Product;

public class ProductManager extends BaseManager<Product> {

	protected Product parse(ResultSet resultSet) throws SQLException {

		Product product = new Product();

		long productId = resultSet.getLong("productId");
		String productName = resultSet.getString("productName");
		double salesPrice = resultSet.getDouble("salesPrice");
		Category category = getCategory(resultSet.getLong("categoryId"));
		String imagePath = resultSet.getString("imagePath");

		product.setProductId(productId);
		product.setProductName(productName);
		product.setSalesPrice(salesPrice);
		product.setCategory(category);
		product.setImagePath(imagePath);

		return product;
	}

	private Category getCategory(long categoryId) throws SQLException {

		Category category = null;
		CategoryManager categoryManager = new CategoryManager();

		category = categoryManager.find(categoryId);

		return category;
	}
	
	public Product find(long productId) throws SQLException{
		
		Product product = null;
		
		connect();
		String sql = "select * from product p where p.productId =?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, productId);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()){
			product = parse(resultSet);
			disconnect();
			return product;
		}
		
		disconnect();
		
		return product;
	}
	
	public List<Product> listByCategory(long categoryId) throws SQLException{

		List<Product> productList = new ArrayList<>();
		
		connect();
		
		String sql = "select * from product p where p.categoryId =?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, categoryId);
		ResultSet resultSet = statement.executeQuery();
		
		productList = parseList(productList, resultSet);
		
		disconnect();
		
		
		return productList;
	}
	
	public List<Product> list() throws SQLException{

		List<Product> productList = new ArrayList<>();
		
		connect();
		
		String sql = "select * from product p";
		PreparedStatement statement = connection.prepareStatement(sql);;
		ResultSet resultSet = statement.executeQuery();
		
		productList = parseList(productList, resultSet);
		
		disconnect();
		
		
		return productList;
	}
	public List<Product> getListRecord(int start,int total) throws SQLException{

		List<Product> productList = new ArrayList<>();
		
		connect();
		
		String sql = "select * from product limit ? offset ?";
		PreparedStatement statement = connection.prepareStatement(sql);;
		statement.setInt(1, total);
		statement.setInt(2, start);
		ResultSet resultSet = statement.executeQuery();
		
		productList = parseList(productList, resultSet);
		
		disconnect();
		
		
		return productList;
	}
	
	public boolean delete(long productId) throws Exception {

		connect();

		String sql = "delete from product where productId=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, productId);
		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);

		return affected > 0;
	}

	public boolean update(Product product) throws Exception {

		connect();

		String sql = "update product set productName =?, salesPrice=?, categoryId=?,imagePath=? where productId=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, product.getProductName());
		statement.setDouble(2, product.getSalesPrice());
		statement.setLong(3, product.getCategory().getCategoryId());
		statement.setString(4, product.getImagePath());
		statement.setLong(5, product.getProductId());
		
		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);
		
		return affected > 0;
	}

	public boolean insert(Product product) throws Exception {

		connect();

		String sql = "insert into product(productName,salesPrice,categoryId,imagePath) values(?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, product.getProductName());
		statement.setDouble(2, product.getSalesPrice());
		statement.setLong(3, product.getCategory().getCategoryId());
		statement.setString(4, product.getImagePath());
		
		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);
		
		return affected > 0;
	}
	

}
