package com.ibtech.sales.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ibtech.core.utils.WebHelper;
import com.ibtech.core.utils.XmlHelper;
import com.ibtech.entity.xml.CartProductXml;
import com.ibtech.entity.xml.CartXml;
import com.ibtech.sales.inventory.entity.CartProduct;
import com.ibtech.sales.inventory.entity.Product;
import com.ibtech.sales.inventory.manager.CartProductManager;
import com.ibtech.sales.inventory.manager.ProductManager;


@WebServlet("/api/cart/add")
public class CartProductAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CartProductAdd() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		long productId = Long.parseLong(request.getParameter("productId"));
		CartProductManager cartProductManager = new CartProductManager();
		List<CartProduct> cartProductList = null;
		Product product = null;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset = UTF-8");
			
		ProductManager productManager = new ProductManager();
		
		try {
			product = productManager.find(productId);
			session.setAttribute("product", product);
			System.out.print(product.getProductName());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(session.getAttribute("cartId") == null) {
			String address = "http://localhost:8080/BootCampProject/api/cart/create";

			try {
				InputStream in = WebHelper.get(address);
				Document document = XmlHelper.parse(in);
				String cartId = CartXml.parseid(document);
				session.setAttribute("cartId", cartId);
				
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		
		String cartId = (String)session.getAttribute("cartId");
		long cId = Long.parseLong(cartId);
		try {
			cartProductList = cartProductManager.list(cId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		boolean exist = false;
		if(cartProductList != null) {
			if(!cartProductList.isEmpty()) {
				for(CartProduct cartProduct :cartProductList) {
					if(cartProduct.getProductId() == productId) {
						exist = true;
						out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='http://localhost:8080/SalesViewBootCampProject/ProductList.jsp'>GO to Cart Page</a></h3>");
					}
				}
			}
		}
		if(!exist) {
			try {
								
				response.setContentType("application/xml; charset=UTF-8");
				
				CartProduct cartProduct = new CartProduct();
								
				cartProduct.setCartId(cId);
				cartProduct.setProductId(productId);
				cartProduct.setSalesQuantity(1);
				cartProduct.setSalesPrice(product.getSalesPrice());
				cartProduct.setTaxRate(0.18);
				
				cartProductManager.insert(cartProduct);
					
				response.sendRedirect("http://localhost:8080/SalesViewBootCampProject/ProductView.jsp?cartId="+cartId+"&productId="+String.valueOf(productId));
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}		
	}

}
