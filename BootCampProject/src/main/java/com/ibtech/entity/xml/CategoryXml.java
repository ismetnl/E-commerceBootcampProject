package com.ibtech.entity.xml;

import static com.ibtech.xml.XmlUtilities.*;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ibtech.sales.inventory.entity.Category;

public class CategoryXml {

	
	public static Document format(Category category) throws ParserConfigurationException{
		
		Document document = create("category");
		Element categoryElement = document.getDocumentElement();
		
		categoryElement.setAttribute("id", String.valueOf(category.getCategoryId()));
		addSingleElementText(document, categoryElement, "categoryName", category.getCategoryName());
		
		return document;
	}
	
	public static Document format(List<Category> categories) throws ParserConfigurationException{
		
		Document document = create("categories");
		Element categoryList = document.getDocumentElement();

		for (Category category : categories) {
			Element categoryElement = document.createElement("category");
			categoryElement.setAttribute("id", String.valueOf(category.getCategoryId()));
			addSingleElementText(document, categoryElement, "categoryName", category.getCategoryName());
			
			categoryList.appendChild(categoryElement);
		}

		return document;
	}
	
}
