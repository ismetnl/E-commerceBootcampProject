package com.ibtech.sales.inventory.test;

import java.sql.SQLException;
import java.util.List;

import com.ibtech.sales.inventory.entity.Category;
import com.ibtech.sales.inventory.manager.CategoryManager;

public class CategoryTest {
	public static void main(String[] args) throws SQLException {
		
		//Category category = null;
		List<Category> categoryList = null;
		//long categoryId = 2;
		
		CategoryManager categoryManager = new CategoryManager();
		
		//category = categoryManager.find(categoryId);
		categoryList = categoryManager.list();
		
		/*
		System.out.println(category.getCategoryId()+ " "
				+category.getCategoryName());
		*/
		
		for(Category category: categoryList) {
			System.out.println(category.getCategoryId()+ " "
					+category.getCategoryName());
		}
	}
}
