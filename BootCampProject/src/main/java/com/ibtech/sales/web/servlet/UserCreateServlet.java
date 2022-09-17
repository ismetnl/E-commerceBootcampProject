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
import com.ibtech.entity.xml.UserXml;
import com.ibtech.sales.inventory.entity.User;
import com.ibtech.sales.inventory.manager.UserManager;


@WebServlet("/api/user/create")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserManager userManager = new UserManager();
		Document document;
		User user = null;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		try {
			
			user = userManager.findByUserName(username);
			
			if(user != null) {
				document = UserXml.format("wrong");
			}
			else {
				user = new User();
				
				user.setUserName(username);
				user.setUserPassword(password);
				
				userManager.insert(user);
				document = UserXml.format("success");
			}
			response.setContentType("application/xml; charset=UTF-8");
			XmlHelper.dump(document, response.getOutputStream());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
