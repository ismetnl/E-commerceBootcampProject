package com.ibtech.sales.inventory.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseManager<E>{

	private String url = "jdbc:postgresql://localhost/sales";
	private String user = "postgres";
	private String password = "209152Ok";
	private String driver = "org.postgresql.Driver";
	
	protected Connection connection = null;
	
	public BaseManager() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() throws SQLException {
		connection = DriverManager.getConnection(url, user, password);
	}
	
	public void disconnect() throws SQLException {
		
		if(connection != null) {
			connection.close();
		}
		else {
			System.out.println("İletişim kurulmamış bağlantıyı koparamazsın");
		}
	}
	
	protected List<E> parseList(List<E> entityList, ResultSet resultSet) throws SQLException {
		
		while(resultSet.next()) {
			
			E entity = parse(resultSet);
			entityList.add(entity);
		}
		return entityList;
	}
	
	protected abstract E parse(ResultSet resultSet) throws SQLException;	
}
