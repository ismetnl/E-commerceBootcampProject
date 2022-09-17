package com.ibtech.sales.inventory.test;

import java.sql.SQLException;
import java.util.List;

import com.ibtech.sales.inventory.entity.Product;
import com.ibtech.sales.inventory.manager.ProductManager;

public class ProductTest {
	public static void main(String[] args) throws SQLException {
		
		//Product product = null;
		List<Product> productList = null;
		//long productId = 1;
		long categoryId = 2;
		
		ProductManager productManager = new ProductManager();
		
		productList = productManager.listByCategory(categoryId);
		
		/*
		System.out.println(product.getProductId()+" "
				+product.getProductName()+" "
				+product.getSalesPrice()+" "
				+product.getCategory().getCategoryId()+" "
				+product.getCategory().getCategoryName()+" "
				+product.getImagePath());
		*/
		
		for(Product product: productList) {
			
			System.out.println(product.getProductId()+" "
					+product.getProductName()+" "
					+product.getSalesPrice()+" "
					+product.getCategory().getCategoryId()+" "
					+product.getCategory().getCategoryName()+" "
					+product.getImagePath());
		}
	}
}
