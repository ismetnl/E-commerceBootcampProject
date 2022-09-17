package com.ibtech.entity.xml;

import static com.ibtech.xml.XmlUtilities.addSingleElementText;
import static com.ibtech.xml.XmlUtilities.create;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ibtech.sales.inventory.entity.Product;

public class ProductXml {

	public static Document format(Product product) throws ParserConfigurationException {

		Document document = create("product");
		Element productElement = document.getDocumentElement();

		productElement.setAttribute("id", String.valueOf(product.getProductId()));

		addSingleElementText(document, productElement, "productName", product.getProductName());
		addSingleElementText(document, productElement, "salesPrice", product.getSalesPrice());
		addSingleElementText(document, productElement, "categoryId", product.getCategory().getCategoryId());
		addSingleElementText(document, productElement, "categoryName", product.getCategory().getCategoryName());
		addSingleElementText(document, productElement, "imagePath", product.getImagePath());

		return document;
	}

	public static Document format(List<Product> products) throws ParserConfigurationException {

		Document document = create("products");
		Element productList = document.getDocumentElement();

		for (Product product : products) {
			Element productElement = document.createElement("product");

			productElement.setAttribute("id", String.valueOf(product.getProductId()));

			addSingleElementText(document, productElement, "productName", product.getProductName());
			addSingleElementText(document, productElement, "salesPrice", product.getSalesPrice());
			addSingleElementText(document, productElement, "categoryId", product.getCategory().getCategoryId());
			addSingleElementText(document, productElement, "categoryName", product.getCategory().getCategoryName());
			addSingleElementText(document, productElement, "imagePath", product.getImagePath());

			productList.appendChild(productElement);
		}

		return document;
	}

}
