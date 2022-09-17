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
import com.ibtech.sales.inventory.entity.Province;

public class ProvinceXml {

	public static List<Province> parseList(Document document) throws ParserConfigurationException, SAXException, IOException, SQLException {
		
		Element productListElement = document.getDocumentElement();
		
		List<Province> provinceList = new ArrayList<>(); 
		
		NodeList provincesElement = productListElement.getElementsByTagName("province");
		
		for(int i = 0; i < provincesElement.getLength(); i++) {
			
			Element provinceElement = (Element)provincesElement.item(i);
			long provinceId = getAttribute(provinceElement, "id", 0);
			String provinceName = getSingleElementText(provinceElement, "provinceName","");
			
		
			Province province = new Province();
			
			province.setProvinceId(provinceId);
			province.setProvinceName(provinceName);
			
		
			
			provinceList.add(province);
		}
		
		
		return provinceList;
	}
}
