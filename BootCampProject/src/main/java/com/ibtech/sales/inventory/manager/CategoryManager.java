package com.ibtech.sales.inventory.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibtech.sales.inventory.entity.Category;

public class CategoryManager extends BaseManager<Category> {

	public CategoryManager() {
		super();
	}

	protected Category parse(ResultSet resultSet) throws SQLException {

		Category category = new Category();

		long categoryId = resultSet.getLong("categoryId");
		String categoryName = resultSet.getString("categoryName");

		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);

		return category;
	}

	public List<Category> list() throws SQLException {

		List<Category> categoryList = new ArrayList<>();

		connect();

		String sql = "select * from category";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		categoryList = parseList(categoryList, resultSet);

		disconnect();

		return categoryList;
	}
	
	public List<Category> getListRecord(int start,int total) throws SQLException {

		List<Category> categoryList = new ArrayList<>();

		connect();

		String sql = "select * from category limit ? offset ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, total);
		statement.setInt(2, start);
		ResultSet resultSet = statement.executeQuery();
		
		categoryList = parseList(categoryList, resultSet);

		disconnect();

		return categoryList;
	}

	public Category find(long categoryId) throws SQLException {

		Category category = null;
		connect();

		String sql = "select * from category c where c.categoryId =?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, categoryId);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			category = parse(resultSet);
			disconnect();
			return category;
		}

		disconnect();
		return category;
	}
	
	public boolean delete(long categoryId) throws Exception {

		connect();

		String sql = "delete from product where categoryId in("
				+ "select categoryId from category where categoryId = ?)";
		PreparedStatement statementProduct = connection.prepareStatement(sql);
		statementProduct.setLong(1, categoryId);
		statementProduct.executeUpdate();
		
		
		sql = "delete from category where categoryId=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, categoryId);
		int affectedCategory = statement.executeUpdate();

		disconnect();


		return affectedCategory > 0;
	}

	public boolean update(long categoryId,Category category) throws Exception {

		connect();

		String sql = "update category set categoryName =? where categoryId=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, category.getCategoryName());
		statement.setLong(2, categoryId);

		
		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);
		
		return affected > 0;
	}

	public boolean insert(Category category) throws Exception {

		connect();

		String sql = "insert into category(categoryName) values(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, category.getCategoryName());

		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);
		
		return affected > 0;
	}
}
