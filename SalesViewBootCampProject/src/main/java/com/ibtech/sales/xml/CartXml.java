package com.ibtech.sales.xml;

import static com.ibtech.xml.XmlUtilities.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class CartXml {

	public static String[] parseid(Document document) throws ParserConfigurationException, SAXException, IOException {

		Element cartElement = document.getDocumentElement();
		long id = getAttribute(cartElement, "id", 0);
		String categoryId = getSingleElementText(cartElement, "categoryId", "");
		String[] cartResponse = {String.valueOf(id),categoryId};
		
		return cartResponse;
	}
	
}
