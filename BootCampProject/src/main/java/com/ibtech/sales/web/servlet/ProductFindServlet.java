package com.ibtech.sales.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.ibtech.core.utils.XmlHelper;
import com.ibtech.entity.xml.ProductXml;
import com.ibtech.sales.inventory.entity.Product;
import com.ibtech.sales.inventory.manager.ProductManager;


@WebServlet("/api/product")
public class ProductFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ProductFindServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			long productId = Long.parseLong(request.getParameter("productId"));
			
			ProductManager productManager = new ProductManager();
			
			Product product = productManager.find(productId);
			
			Document document = ProductXml.format(product);
			
			response.setContentType("application/xml; charset=UTF-8");
			
			XmlHelper.dump(document, response.getOutputStream());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {

			e.printStackTrace();
		}
		
	}

	
}
