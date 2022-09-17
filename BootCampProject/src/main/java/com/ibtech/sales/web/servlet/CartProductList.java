package com.ibtech.sales.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import com.ibtech.core.utils.XmlHelper;
import com.ibtech.entity.xml.CartProductXml;
import com.ibtech.sales.inventory.entity.CartProduct;
import com.ibtech.sales.inventory.manager.CartProductManager;


@WebServlet("/api/cart/view")
public class CartProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CartProductList() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
			String cartId = request.getParameter("cartId");
			
			if(cartId != null) {
				
				CartProductManager cartProductManager = new CartProductManager();
				
				long cId = Long.parseLong(cartId);
				
				List<CartProduct> categoryList = cartProductManager.list(cId);
				
				Document document = CartProductXml.format(categoryList);
				
				response.setContentType("application/xml; charset=UTF-8");
				
				XmlHelper.dump(document, response.getOutputStream());
			}
				
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			
			e.printStackTrace();
		}
		
	}


}
