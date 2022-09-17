<%@page import="com.ibtech.sales.xml.CartXml"%>
<%@page import="com.ibtech.sales.xml.ProductXml"%>
<%@page import="com.ibtech.sales.inventory.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.ibtech.core.utils.XmlHelper"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="com.ibtech.core.utils.WebHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ibtech.sales.xml.CartProductXml"%>
<%@page import="com.ibtech.shopping.entity.CartProduct"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String auth = (String)session.getAttribute("auth");
	List<CartProduct> cartProductList = new ArrayList<>();
	int size = 0;
	
	
	if(request.getParameter("cartId") != null){
		session.setAttribute("cartId",request.getParameter("cartId"));
		String adress = "http://localhost:8080/BootCampProject/api/cart/view?cartId="+(String)session.getAttribute("cartId");
		InputStream in = WebHelper.get(adress);
		Document document = XmlHelper.parse(in);
		cartProductList = CartProductXml.parseList(document);
	}
	
	if(!cartProductList.isEmpty()){
		size = cartProductList.size();
	}
	
	String productId = request.getParameter("productId");
	String adressView = "http://localhost:8080/BootCampProject/api/product?productId="+productId;
	InputStream inView = WebHelper.get(adressView);
	Document documentView = XmlHelper.parse(inView);
	Product product = ProductXml.parse(documentView);
		
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/navbar.css">
<link rel="stylesheet" href="styles/ProductView.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Product View</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container bootdey">
		<div class="col-md-12">
			<div class="row">
		          <div class="col-md-6">
		              <div class="pro-img-details">
		                  <img src="<%=product.getImagePath()%>" alt="">
		              </div>
		          </div>
		          <div class="col-md-6">
		              <div style="padding-top: 80px;">
			              <h4 class="pro-d-title">
			                  <a href="#" class="">
			                      <%=product.getProductName()%>
			                  </a>
			              </h4>
			              <div class="product_meta">
			                  <span class="posted_in"> <strong>Categories:</strong> <a rel="tag" href="#"><%=product.getCategoryName() %></a></span>
			              </div> 
			              
			              <div class="m-bot15" style="padding-bottom: 30px;"> <strong>Price : </strong>  <span class="pro-price"><%=product.getSalesPrice()%></span></div>
			              <p align="right">
			                  <a class="btn btn-dark" href="http://localhost:8080/BootCampProject/api/cart/add?productId=<%=product.getProductId()%>" >Add Cart</a>
			              </p>
		              </div>
		          </div>
			  </div>
		   </div>
  	</div>
</body>
</html>