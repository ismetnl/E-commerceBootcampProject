package com.ibtech.sales.xml;

import static com.ibtech.xml.XmlUtilities.getAttribute;
import static com.ibtech.xml.XmlUtilities.getSingleElementText;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibtech.sales.inventory.entity.Product;


public class ProductXml {

	
	public static List<Product> parseList(Document document) throws ParserConfigurationException, SAXException, IOException, SQLException {
		
		Element productListElement = document.getDocumentElement();
		
		List<Product> productList = new ArrayList<>(); 
		
		NodeList productsElement = productListElement.getElementsByTagName("product");
		
		for(int i = 0; i < productsElement.getLength(); i++) {
			
			Element productElement = (Element)productsElement.item(i);
			long productId = getAttribute(productElement, "id", 0);
			String productName = getSingleElementText(productElement, "productName","");
			String salesPrice = getSingleElementText(productElement, "salesPrice", "");
			String categoryId = getSingleElementText(productElement, "categoryId", "");
			String categoryName = getSingleElementText(productElement, "categoryName","");
			String imagePath = getSingleElementText(productElement, "imagePath", "");
		
			Product product = new Product();
			
			product.setProductId(productId);
			product.setProductName(productName);
			product.setSalesPrice(Double.parseDouble(salesPrice));
			product.setImagePath(imagePath);
			product.setCategoryId(Long.parseLong(categoryId));
			product.setCategoryName(categoryName);
		
			
			productList.add(product);
		}
		
		
		return productList;
	}
	
	public static Product parse(Document document) throws ParserConfigurationException, SAXException, IOException, SQLException {
		
		Element productElement = document.getDocumentElement();
			
		long productId = getAttribute(productElement, "id", 0);
		String productName = getSingleElementText(productElement, "productName","");
		String salesPrice = getSingleElementText(productElement, "salesPrice", "");
		String categoryId = getSingleElementText(productElement, "categoryId", "");
		String categoryName = getSingleElementText(productElement, "categoryName","");
		String imagePath = getSingleElementText(productElement, "imagePath", "");
	
		Product product = new Product();
		
		product.setProductId(productId);
		product.setProductName(productName);
		product.setSalesPrice(Double.parseDouble(salesPrice));
		product.setImagePath(imagePath);
		product.setCategoryId(Long.parseLong(categoryId));
		product.setCategoryName(categoryName);
	
		return product;
	}

}
