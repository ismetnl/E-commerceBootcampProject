package com.ibtech.sales.inventory.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ibtech.sales.inventory.entity.Adress;
import com.ibtech.sales.inventory.entity.Cart;
import com.ibtech.sales.inventory.entity.Product;
import com.ibtech.sales.inventory.entity.Province;

public class AdressManager extends BaseManager<Adress>{

	@Override
	protected Adress parse(ResultSet resultSet) throws SQLException {
		
		Adress adress = new Adress();
		Cart cart = new Cart();
		Province province = new Province();
		
		
		long adressId = resultSet.getLong("adressId");
		String adressLine1 = resultSet.getString("adressLine1");
		String adressLine2 = resultSet.getString("adressLine2");
		cart.setCartId(resultSet.getLong("cartId"));
		province.setProvinceId(resultSet.getLong("provinceId"));
		
		adress.setAdressId(adressId);
		adress.setAdressLine1(adressLine1);
		adress.setAdressLine2(adressLine2);
		adress.setCart(cart);
		adress.setProvince(province);
		
		return adress;
	}
	
	public boolean insert(Adress adress) throws Exception {

		connect();

		String sql = "insert into adress(provinceId,cartId,adressLine1,adressLine2) values(?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, adress.getProvince().getProvinceId());
		statement.setLong(2, adress.getCart().getCartId());
		statement.setString(3, adress.getAdressLine1());
		statement.setString(4, adress.getAdressLine2());
		int affected = statement.executeUpdate();

		disconnect();

		System.out.println("Etkilenmiş " + affected);
		
		return affected > 0;
	}
	
	

}
