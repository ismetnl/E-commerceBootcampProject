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
	List<Product> productList = null;
	
	if(request.getParameter("ViewProduct") != null){
		response.sendRedirect("http://localhost:8080/SalesViewBootCampProject/ProductView.jsp?productId="+request.getParameter("ViewProduct"));
	}

	
	if(session.getAttribute("cartId") != null){
		String adress = "http://localhost:8080/BootCampProject/api/cart/view?cartId="+(String)session.getAttribute("cartId");
		InputStream in = WebHelper.get(adress);
		Document document = XmlHelper.parse(in);
		cartProductList = CartProductXml.parseList(document);
	}
	
	if(!cartProductList.isEmpty()){
		size = cartProductList.size();
	}
	String categoryId = request.getParameter("categoryId");
	String adressProduct = "http://localhost:8080/BootCampProject/api/products?categoryId="+categoryId;
	InputStream inProduct = WebHelper.get(adressProduct);
	Document documentProduct = XmlHelper.parse(inProduct);
	productList = ProductXml.parseList(documentProduct);

%>    

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/ProductList.css">
<link rel="stylesheet" href="styles/navbar.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Products</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<form action="ProductList.jsp" method="get">
		<div class = "conteiner">
			<div class="row">
				<div class = "col-md-2"></div>
				<div class = "col-md-8">
					<h1 class="mb-3">Products</h1>
					<%for(Product product:productList){%>
						<div class="card">
						  <img src="<%=product.getImagePath()%>" alt="<%=product.getCategoryName()%>" style="width:100%;height:350px">
						  <h1><%=product.getProductName()%></h1>
						  <p class="price">$<%=product.getSalesPrice()%></p>
						  <p><a class="btn btn-dark" href="http://localhost:8080/SalesViewBootCampProject/ProductView.jsp?productId=<%=product.getProductId()%>" >View Product</a></p>
						</div>
					<%}%>
				</div>
				<div class = "col-md-2"></div>
			</div>
		</div>
	</form>
	
</body>
</html>