package com.ibtech.sales.inventory.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibtech.sales.inventory.entity.Cart;
import com.ibtech.sales.inventory.entity.User;

public class UserManager extends BaseManager<User>{

	
	public User findByUserNamePassword(String userName,String userPassword) throws SQLException {
		
		User user = null;
		
		connect();
		
		String sql ="select * from \"user\" where username = ? and password = ?";
		PreparedStatement statement =  connection.prepareStatement(sql);
		statement.setString(1, userName);
		statement.setString(2, userPassword);
		ResultSet resultSet = statement.executeQuery();
		
		
		if(resultSet.next()) {
			user = parse(resultSet);
			return user;
		}
		
		disconnect();
		
		return user;
		
		
	}
	
	public User findByUserName(String userName) throws SQLException {
		
		User user = null;
		
		connect();
		
		String sql ="select * from \"user\" where username = ?";
		PreparedStatement statement =  connection.prepareStatement(sql);
		statement.setString(1, userName);
		ResultSet resultSet = statement.executeQuery();
		
		
		if(resultSet.next()) {
			user = parse(resultSet);
			return user;
		}
		
		disconnect();
		
		return user;
		
		
	}
	
	
	protected User parse(ResultSet resultSet) throws SQLException{
		
		long userId = resultSet.getLong("userid");
		String userName = resultSet.getString("username");
		String userPassword = resultSet.getString("password");
		
		User user = new User(userId,userName,userPassword);
		
		return user;
	}
	
	public boolean insert(User user) throws Exception {

		connect();

		String sql = "insert into \"user\"(userName,password) values(?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getUserName());
		statement.setString(2, user.getUserPassword());

		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);
		
		return affected > 0;
	}
	
	public List<User> list() throws SQLException {

		List<User> userList = new ArrayList<>();

		connect();

		String sql = "select * from \"user\"";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		userList = parseList(userList, resultSet);

		disconnect();

		return userList;
	}
	
	public List<User> getListRecord(int start,int total) throws SQLException {

		List<User> userList = new ArrayList<>();

		connect();

		String sql = "select * from \"user\" limit ? offset ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, total);
		statement.setInt(2, start);
		ResultSet resultSet = statement.executeQuery();
		
		userList = parseList(userList, resultSet);

		disconnect();

		return userList;
	}
	
}
