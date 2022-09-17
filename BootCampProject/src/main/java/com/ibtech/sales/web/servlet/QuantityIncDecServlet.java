package com.ibtech.sales.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibtech.sales.inventory.entity.CartProduct;
import com.ibtech.sales.inventory.manager.CartProductManager;

/**
 * Servlet implementation class QuantityIncDecServlet
 */
@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QuantityIncDecServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			CartProductManager cartProductManager = new CartProductManager();
			
			
			if (action != null && id >= 1) {
				CartProduct cartProduct = cartProductManager.find(id);
				if (action.equals("inc")) {
					cartProduct.setSalesQuantity(cartProduct.getSalesQuantity()+1);
					cartProductManager.update(cartProduct);
					response.sendRedirect("http://localhost:8080/SalesViewBootCampProject/CartView.jsp");
				}
				if (action.equals("dec")) {
					cartProduct.setSalesQuantity(cartProduct.getSalesQuantity()-1);
					cartProductManager.update(cartProduct);
					response.sendRedirect("http://localhost:8080/SalesViewBootCampProject/CartView.jsp");
				}
			}
			else {
				response.sendRedirect("http://localhost:8080/SalesViewBootCampProject/CartView.jsp");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
