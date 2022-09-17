package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ibtech.core.utils.WebHelper;
import com.ibtech.core.utils.XmlHelper;
import com.ibtech.sales.xml.CartProductXml;
import com.ibtech.shopping.entity.CartProduct;

public class test {
	public static void main(String[] args) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {

		String adress = "http://localhost:8080/BootCampProject/api/cart/view";
		InputStream in = WebHelper.get(adress);
		Document document = XmlHelper.parse(in);
		List<CartProduct> cartProductList = CartProductXml.parseList(document);
		
		
	}
}
