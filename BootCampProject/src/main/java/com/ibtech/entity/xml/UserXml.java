package com.ibtech.entity.xml;

import static com.ibtech.xml.XmlUtilities.addSingleElementText;
import static com.ibtech.xml.XmlUtilities.create;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ibtech.sales.inventory.entity.User;

public class UserXml {

	public static Document format(String userName) throws ParserConfigurationException {
		Document document = create("user");
		Element productElement = document.getDocumentElement();
		addSingleElementText(document, productElement, "userName", userName);

		return document;
	}
}
