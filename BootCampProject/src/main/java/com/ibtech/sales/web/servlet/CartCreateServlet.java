package com.ibtech.sales.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ibtech.core.utils.XmlHelper;
import com.ibtech.entity.xml.CartXml;
import com.ibtech.core.utils.StreamHelper;
import com.ibtech.sales.inventory.entity.Cart; 
import com.ibtech.sales.inventory.manager.CartManager;

/**
 * Servlet implementation class CartCreateServlet
 */
@WebServlet("/api/cart/create")
public class CartCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			Cart cart = new Cart();
		
			
			HttpSession session = request.getSession();
			
			cart.setCustomerName((String)session.getAttribute("user"));
			
			CartManager cartManager = new CartManager();

			long cartId = cartManager.insert(cart);
				
			Document document = CartXml.formatid(cartId);
			
			response.setContentType("application/xml; charset=UTF-8");
			
			XmlHelper.dump(document, response.getOutputStream());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
