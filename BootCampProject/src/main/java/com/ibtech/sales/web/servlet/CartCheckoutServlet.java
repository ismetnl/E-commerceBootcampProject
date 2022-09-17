package com.ibtech.sales.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibtech.sales.inventory.entity.Adress;
import com.ibtech.sales.inventory.entity.Cart;
import com.ibtech.sales.inventory.entity.Province;
import com.ibtech.sales.inventory.manager.AdressManager;
import com.ibtech.sales.inventory.manager.CartManager;

/**
 * Servlet implementation class CartCheckoutServlet
 */
@WebServlet("/api/cart/checkout")
public class CartCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartCheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();
			
			AdressManager adressManager = new AdressManager();
			CartManager cartManager = new CartManager();
			String customerName = request.getParameter("name");
			long cartId = Long.parseLong(request.getParameter("cartId"));
			double total = Double.parseDouble(request.getParameter("total"));
			long provinceId = Long.parseLong(request.getParameter("provinceId"));
			String adressLine1 = request.getParameter("AdressLine1");
			String adressLine2 = request.getParameter("AdressLine2");
			
			Province province = new Province();
			Cart cart = new Cart();
			Adress adress = new Adress();
			
			province.setProvinceId(provinceId);
			
			cart.setCartId(cartId);
			cart.setCustomerName(customerName);
			cart.setTotalAmount(total);
			
			adress.setAdressLine1(adressLine1);
			adress.setAdressLine2(adressLine2);
			adress.setCart(cart);
			adress.setProvince(province);
			
			adressManager.insert(adress);
			cartManager.update(cart);
			
			session.removeAttribute("cartId");
			response.sendRedirect("http://localhost:8080/SalesViewBootCampProject/CheckoutWelcome.jsp");
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		

		
		
		
	}

	

}
