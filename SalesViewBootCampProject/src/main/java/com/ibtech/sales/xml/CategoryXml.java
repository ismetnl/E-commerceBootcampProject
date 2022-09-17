package com.ibtech.sales.xml;

import static com.ibtech.xml.XmlUtilities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibtech.sales.inventory.entity.Category;

public class CategoryXml {

	
	public static List<Category> parseList(Document document) throws ParserConfigurationException, SAXException, IOException {
		
		Element categoryListElement = document.getDocumentElement();
		
		List<Category> categoryList = new ArrayList<>(); 
		
		NodeList categoriesElement = categoryListElement.getElementsByTagName("category");
		
		for(int i = 0; i < categoriesElement.getLength(); i++) {
			
			Element categoryElement = (Element)categoriesElement.item(i);
			long categoryId = getAttribute(categoryElement, "id", 0);
			String categoryName = getSingleElementText(categoryElement, "categoryName", "");
			
			Category category = new Category();
			
			category.setCategoryId(categoryId);
			category.setCategoryName(categoryName);
			
			categoryList.add(category);
		}
		
		
		return categoryList;
	}
	
}
