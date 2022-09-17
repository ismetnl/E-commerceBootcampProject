package com.ibtech.entity.xml;

import static com.ibtech.xml.XmlUtilities.addSingleElementText;
import static com.ibtech.xml.XmlUtilities.create;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ibtech.sales.inventory.entity.CartProduct;



public class CartProductXml {

	public static Document format(List<CartProduct> cartProducts) throws ParserConfigurationException{
			
			Document document = create("cartProducts");
			Element cartProductList = document.getDocumentElement();
	
			for (CartProduct cartProduct : cartProducts) {
				Element cartProductElement = document.createElement("cartProduct");
				cartProductElement.setAttribute("id", String.valueOf(cartProduct.getCartProductId()));
				addSingleElementText(document, cartProductElement, "cartId", cartProduct.getCartId());
				addSingleElementText(document, cartProductElement, "productId", cartProduct.getCartProductId());
				addSingleElementText(document, cartProductElement, "salesQuantity", cartProduct.getSalesQuantity());
				addSingleElementText(document, cartProductElement, "salesPrice", cartProduct.getSalesPrice());
				addSingleElementText(document, cartProductElement, "taxRate", cartProduct.getTaxRate());
				addSingleElementText(document, cartProductElement, "lineAmount", cartProduct.getLineAmount());
				
				cartProductList.appendChild(cartProductElement);
			}
	
			return document;
		}

}
