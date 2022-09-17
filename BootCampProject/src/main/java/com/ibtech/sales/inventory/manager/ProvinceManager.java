package com.ibtech.sales.inventory.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibtech.sales.inventory.entity.Product;
import com.ibtech.sales.inventory.entity.Province;

public class ProvinceManager extends BaseManager<Province> {

	@Override
	protected Province parse(ResultSet resultSet) throws SQLException {
		Province province = new Province();
		
		long provinceId = resultSet.getLong("provinceId");
		String provinceName = resultSet.getString("provinceName");
		
		province.setProvinceId(provinceId);
		province.setProvinceName(provinceName);
		
		return province;
	}
	
	public List<Province> list()  throws SQLException {
		
		List<Province> provinceList = new ArrayList<>();
		
		connect();
		
		String sql = "select * from province p";
		PreparedStatement statement = connection.prepareStatement(sql);;
		ResultSet resultSet = statement.executeQuery();
		
		provinceList = parseList(provinceList, resultSet);
		
		disconnect();
		
		
		return provinceList;
	}

}
