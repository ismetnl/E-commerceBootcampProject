package com.ibtech.sales.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.ibtech.core.utils.XmlHelper;
import com.ibtech.entity.xml.CategoryXml;
import com.ibtech.sales.inventory.entity.Category;
import com.ibtech.sales.inventory.manager.CategoryManager;


@WebServlet("/api/categories")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CategoryServlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			CategoryManager categoryManager = new CategoryManager();
			
			List<Category> categoryList = categoryManager.list();
			
			Document document = CategoryXml.format(categoryList);
			
			response.setContentType("application/xml; charset=UTF-8");
			
			XmlHelper.dump(document, response.getOutputStream());
			
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}


}
