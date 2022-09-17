package com.ibtech.sales.xml;

import static com.ibtech.xml.XmlUtilities.getSingleElementText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UserXml {
	
	public static String parseUserName(Document document) {
		
		String userName = null;
		
		Element userElement = document.getDocumentElement();

		userName = getSingleElementText(userElement, "userName", "");
		
		return userName;
		
	}
}
