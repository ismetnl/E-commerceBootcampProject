package com.ibtech.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlUtilities {

	private static DocumentBuilderFactory factory;

	public static DocumentBuilderFactory getFactory() {
		if (factory == null) {
			factory = DocumentBuilderFactory.newInstance();
		}

		return factory;
	}

	public static Document parse(String path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = getFactory().newDocumentBuilder();
		Document document = builder.parse(path);
		return document;

	}

	public static String getSingleElementText(Element parent, String tag, String defaultValue) {

		NodeList elementList = parent.getElementsByTagName(tag);
		if (elementList.getLength() == 0) {
			return defaultValue;
		}
		return elementList.item(0).getTextContent();
	}

	public static double getSingleElementText(Element parent, String tag, double defaultValue) {

		String string = getSingleElementText(parent, tag, Double.toString(defaultValue));
		return Double.parseDouble(string);
	}
	
	public static long getSingleElementText(Element parent, String tag, long defaultValue) {

		String string = getSingleElementText(parent, tag, Long.toString(defaultValue));
		return Long.parseLong(string);
	}
	
	public static int getSingleElementText(Element parent, String tag, int defaultValue) {

		String string = getSingleElementText(parent, tag, Integer.toString(defaultValue));
		return Integer.parseInt(string);
	}
	

	public static String getAttribute(Element parent, String name, String defaultValue) {
		if (parent.getAttribute(name) == null) {
			return defaultValue;
		}
		return parent.getAttribute(name);
	}

	public static long getAttribute(Element parent, String name, long defaultValue) {
		String string = getAttribute(parent, name, Long.toString(defaultValue));
		return Long.parseLong(string);
	}

	public static Document create(String root) throws ParserConfigurationException {
		DocumentBuilder builder = getFactory().newDocumentBuilder();
		Document document = builder.newDocument();
		Element player = document.createElement(root);
		document.appendChild(player);
		return document;
	}

	public static void addSingleElementText(Document document, Element parent, String tag, String content) {
		Element name = document.createElement(tag);
		name.setTextContent(content);
		parent.appendChild(name);
	}

	public static void addSingleElementText(Document document, Element parent, String tag, double content) {
		String string = Double.toString(content);
		addSingleElementText(document, parent, tag, string);
	}

	public static void dump(Document document, String path) throws IOException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource data = new DOMSource(document);
		FileWriter writer = new FileWriter(new File(path));
		StreamResult result = new StreamResult(writer);
		transformer.transform(data, result);
		writer.close();
	}

}
