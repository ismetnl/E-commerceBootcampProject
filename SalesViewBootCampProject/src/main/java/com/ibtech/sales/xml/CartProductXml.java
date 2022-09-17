package com.ibtech.sales.xml;

import static com.ibtech.xml.XmlUtilities.getAttribute;
import static com.ibtech.xml.XmlUtilities.getSingleElementText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibtech.shopping.entity.CartProduct;



public class CartProductXml {

	
	public static List<CartProduct> parseList(Document document) throws ParserConfigurationException, SAXException, IOException {
		
		Element cartProductListElement = document.getDocumentElement();
		
		List<CartProduct> cartProductList = new ArrayList<>(); 
		
		NodeList  cartProductsElement = cartProductListElement.getElementsByTagName("cartProduct");
		
		for(int i = 0; i < cartProductsElement.getLength(); i++) {
			
			Element cartProductElement = (Element)cartProductsElement.item(i);
			
			long cartProductId = getAttribute(cartProductElement, "id", 0);
			long cartId = getSingleElementText(cartProductElement, "cartId", 0);
			long productId = getSingleElementText(cartProductElement, "productId", 0);
			int salesQuantity = getSingleElementText(cartProductElement, "salesQuantity", 0);
			String salesPrice = getSingleElementText(cartProductElement, "salesPrice", "");
			String taxRate = getSingleElementText(cartProductElement, "taxRate", "");
	
			CartProduct cartProduct = new CartProduct();
			
			cartProduct.setCartProductId(cartProductId);
			cartProduct.setCartId(cartId);
			cartProduct.setProductId(productId);
			cartProduct.setSalesQuantity(salesQuantity);
			cartProduct.setSalesPrice(Double.parseDouble(salesPrice));
			cartProduct.setTaxRate(Float.parseFloat(taxRate));
			
			cartProductList.add(cartProduct);
		}
	
		
		return cartProductList;
	}
	
}
