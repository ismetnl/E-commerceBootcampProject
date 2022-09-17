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
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="styles/navbar.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<title>Checkout Welcome</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
    <body>
        <div class="d-flex align-items-center justify-content-center vh-100" style="padding-top: 100px">
            <div class="text-center">
                <h3 class="fs-3"> <span class="text-danger">Congratulations!</span></h3>
                <p class="lead">
					Thanks for shopping from us.
                  </p>
                <a href="MainPage.jsp" class="btn btn-primary">Go Home</a>
            </div>
        </div>
    </body>
</body>
</html>