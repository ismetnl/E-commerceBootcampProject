package com.ibtech.entity.xml;

import static com.ibtech.xml.XmlUtilities.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class CartXml {
	
	public static Document formatid(long cartId)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {

		Document document = create("cart");
		Element cartElement = document.getDocumentElement();
		cartElement.setAttribute("id", String.valueOf(cartId));
		
		return document;
	}

	public static String parseid(Document document) throws ParserConfigurationException, SAXException, IOException {

		Element cartElement = document.getDocumentElement();
		long id = getAttribute(cartElement, "id", 0);
		return String.valueOf(id);
	}

}
