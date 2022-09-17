package com.ibtech.sales.inventory.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibtech.sales.inventory.entity.AdminUser;
import com.ibtech.sales.inventory.entity.Cart;
import com.ibtech.sales.inventory.entity.User;

public class AdminUserManager extends BaseManager<AdminUser>{

	
	public AdminUser findByUserNamePassword(String userName,String userPassword) throws SQLException {
		
		AdminUser adminUser = null;
		
		connect();
		
		String sql ="select * from adminuser where username = ? and password = ?";
		PreparedStatement statement =  connection.prepareStatement(sql);
		statement.setString(1, userName);
		statement.setString(2, userPassword);
		ResultSet resultSet = statement.executeQuery();
		
		
		if(resultSet.next()) {
			adminUser = parse(resultSet);
			return adminUser;
		}
		
		disconnect();
		
		return adminUser;
		
		
	}
	
	public AdminUser findByUserName(String userName) throws SQLException {
		
		AdminUser adminUser = null;
		
		connect();
		
		String sql ="select * from adminuser where username = ?";
		PreparedStatement statement =  connection.prepareStatement(sql);
		statement.setString(1, userName);
		ResultSet resultSet = statement.executeQuery();
		
		
		if(resultSet.next()) {
			adminUser = parse(resultSet);
			return adminUser;
		}
		
		disconnect();
		
		return adminUser;
		
		
	}
	
	
	protected AdminUser parse(ResultSet resultSet) throws SQLException{
		
		long adminId = resultSet.getLong("adminid");
		String userName = resultSet.getString("username");
		String userPassword = resultSet.getString("password");
		
		AdminUser adminUser = new AdminUser(adminId,userName,userPassword);
		
		return adminUser;
	}

	
}
