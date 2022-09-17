<%@page import="org.apache.catalina.ha.backend.Sender"%>
<%@page import="com.ibtech.sales.xml.ProvinceXml"%>
<%@page import="com.ibtech.sales.inventory.entity.Province"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ibtech.sales.xml.CartProductXml"%>
<%@page import="com.ibtech.shopping.entity.CartProduct"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="com.ibtech.core.utils.XmlHelper"%>
<%@page import="com.ibtech.core.utils.WebHelper"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	List<Province> provinces = new ArrayList<>();
	String AdressLine1="";
	String AdressLine2="";
	String provinceId ="";
	String auth = null;
	String cartId = null;
	double total = 0;
	int size = 0;
	
	auth = (String)session.getAttribute("auth");
	cartId = (String)session.getAttribute("cartId");
	System.out.print(request.getParameter("Chekout"));

	
	String adressProvince = "http://localhost:8080/BootCampProject/api/provinces";
	InputStream inProvince = WebHelper.get(adressProvince);
	Document documentProvince = XmlHelper.parse(inProvince);
	provinces = ProvinceXml.parseList(documentProvince);
	
	
	
	if(auth == null){
		String backURL = "CartCheckOut.jsp";
		session.setAttribute("backURL", backURL);
		response.sendRedirect("Login.jsp");
	}
	
	List<CartProduct> cartProductList = new ArrayList<>();
	
	if(session.getAttribute("cartId") != null){
		String adress = "http://localhost:8080/BootCampProject/api/cart/view?cartId="+(String)session.getAttribute("cartId");
		InputStream in = WebHelper.get(adress);
		Document document = XmlHelper.parse(in);
		cartProductList = CartProductXml.parseList(document);
	}
			
	if(!cartProductList.isEmpty()){
		
		for(CartProduct cartProduct : cartProductList){
			total += cartProduct.getLineAmount();
		}
		size = cartProductList.size();
	}
	
	if(request.getParameter("Chekout") != null){
		session.removeAttribute("cartId");
		provinceId = request.getParameter("ProvinceSelect");
		AdressLine1 = request.getParameter("AdressLine1");
		AdressLine2 = request.getParameter("AdressLine2");
		
		String checkOutAdress = "http://localhost:8080/BootCampProject/api/cart/checkout";
		response.sendRedirect(checkOutAdress+"?cartId="+cartId+"&name="
		+auth+"&total="+String.valueOf(total)+"&provinceId="+provinceId
		+"&AdressLine1="+AdressLine1+"&AdressLine2="+AdressLine2);
		
		
		
	}	
	
%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> 
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script> 
<meta charset="UTF-8">
<title>Chekout</title>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<form action="CartCheckOut.jsp" method="post">
	<div class="conteiner">
 		<div class = "row">
 			<div class = "col-md-2"></div>
 			<div class = "col-md-8"  style="padding-top: 60px">
 				<table>
 					<tbody>  
	 					<tr>
	 						<td><label for="ProductName" class="text-info">Adress Line 1</label></td>
	 						<td><input type="text" name="AdressLine1" value="<%=AdressLine1%>" class="form-group"></td>
	 					</tr>
	 					<tr>
	 						<td><label for="SalesPrice" class="text-info">Adress Line 2</label></td>
	 						<td><input type="text" name="AdressLine2" value="<%=AdressLine2%>" class="form-group"></td>
	 					</tr> 
	 					<tr>
	 						<td><label for="Province" class="text-info">Province:</label></td>
	 						<td><select class="form-select" name="ProvinceSelect" aria-label="Default select example">
								<%for(Province province:provinces){%>
									<option value="<%=province.getProvinceId()%>"><%=province.getProvinceName()%></option>
								<%}%>
							</select></td>
	 					</tr> 
	 					<tr>
	 						<td></td>
	 						<td align="right"><button class="btn btn-primary" name="Chekout" value="Chekout">Buy Now</button></td>
	 					</tr>
 					</tbody>
 				</table>   
 			</div>
 			<div class = "col-md-1"></div>
 		</div>
	</div>
</form>


</body>
</html>