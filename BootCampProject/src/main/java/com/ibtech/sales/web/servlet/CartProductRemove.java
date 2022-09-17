package com.ibtech.sales.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ibtech.sales.inventory.manager.CartProductManager;

/**
 * Servlet implementation class CartProductRemove
 */
@WebServlet("/api/cart/remove")
public class CartProductRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartProductRemove() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		long cartProductId = Long.parseLong(request.getParameter("id"));
		
		CartProductManager cartProductManager = new CartProductManager();
		
		try {
			cartProductManager.delete(cartProductId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("http://localhost:8080/SalesViewBootCampProject/CartView.jsp");
		
	}

}
