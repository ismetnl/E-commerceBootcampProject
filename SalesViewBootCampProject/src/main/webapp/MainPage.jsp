<%@page import="java.util.List"%>
<%@page import="com.ibtech.core.utils.XmlHelper"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="com.ibtech.core.utils.WebHelper"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ibtech.sales.xml.CartProductXml"%>
<%@page import="com.ibtech.shopping.entity.CartProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	String auth = (String)session.getAttribute("auth");
	
	List<CartProduct> cartProductList = new ArrayList<>();
	int size = 0;
	
	if(session.getAttribute("cartId") != null){
		String adressCart = "http://localhost:8080/BootCampProject/api/cart/view?cartId="+(String)session.getAttribute("cartId");
		InputStream inCart = WebHelper.get(adressCart);
		Document documentCart = XmlHelper.parse(inCart);
		cartProductList = CartProductXml.parseList(documentCart);
	}
			
	if(!cartProductList.isEmpty()){
		size = cartProductList.size();
	}


%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/navbar.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<style>
h2 {
text-align: center;
font-family: Arial, Helvetica, sans-serif;
} 

</style>
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<h2>Welcome to main page</h2>
</body>
</html>