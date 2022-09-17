package com.ibtech.entity.xml;

import static com.ibtech.xml.XmlUtilities.addSingleElementText;
import static com.ibtech.xml.XmlUtilities.create;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ibtech.sales.inventory.entity.Province;

public class ProvinceXml {

public static Document format(List<Province> provinces) throws ParserConfigurationException{
		
		Document document = create("provinces");
		Element provinceList = document.getDocumentElement();

		for (Province province : provinces) {
			Element categoryElement = document.createElement("province");
			categoryElement.setAttribute("id", String.valueOf(province.getProvinceId()));
			addSingleElementText(document, categoryElement, "provinceName", province.getProvinceName());
			
			provinceList.appendChild(categoryElement);
		}

		return document;
	}
}
