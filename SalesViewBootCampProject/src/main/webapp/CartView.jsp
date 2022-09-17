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

	String auth = (String)session.getAttribute("auth");
	

	List<CartProduct> cartProductList = new ArrayList<>();
	double total = 0;
	int size = 0;
	
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
	
	if(request.getParameter("Chekout") != null && total != 0){
		response.sendRedirect("CartCheckOut.jsp");
	}
	
	
%>   


<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Cart View</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container my-3">
		<form action="CartView.jsp" method="post" class="form-inline">
			<div class="d-flex py-3">
				<h3 style="padding-right: 700px;">Total Price: <%=String.format("%.2f", total) %></h3>  
				<button class="mx-3 btn btn-primary" name="Chekout" value="Chekout">Check Out</button>
			</div> 
			<table class="table table-light">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Price</th> 
						<th scope="col">Quantity</th>
						<th scope="col">Tax Rate</th>
						<th scope="col">Line Amount</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
					for (CartProduct cartProduct : cartProductList) {
					%>
					<tr>
						<td><%=cartProduct.getProductId()%></td>
						<td><%=cartProduct.getSalesPrice()%></td>
						<td>
							<div >
								<a class="btn bnt-sm btn-incre" style="font-size:20px;" href="http://localhost:8080/BootCampProject/quantity-inc-dec?action=inc&id=<%=cartProduct.getProductId()%>"><i class="fa fa-plus-square-o"></i></a> 
								<input type="text" name="quantity" class="form-control"  value="<%=cartProduct.getSalesQuantity()%>" readonly> 
								<%if(cartProduct.getSalesQuantity() == 1){%>
									<a style="pointer-events: none; font-size:20px;" class="btn btn-sm btn-decre" href="http://localhost:8080/BootCampProject/quantity-inc-dec?action=dec&id=<%=cartProduct.getProductId()%>"><i class="fa fa-minus-square-o"></i></a>
								<%}else{%>
									<a class="btn btn-sm btn-decre" style="font-size:20px;" href="http://localhost:8080/BootCampProject/quantity-inc-dec?action=dec&id=<%=cartProduct.getProductId()%>"><i class="fa fa-minus-square-o"></i></a>
								<%}%>
							</div>
						</td>	
						<td><%=String.format("%.1f", cartProduct.getTaxRate())%></td>
						<td><%=String.format("%.2f", cartProduct.getLineAmount())%></td>
						<td align="right"><a href="http://localhost:8080/BootCampProject/api/cart/remove?id=<%=cartProduct.getProductId() %>" class="btn btn-sm btn-danger">Remove</a></td>
					</tr>
	
					<%
					}%>
				</tbody>
			</table>
		</form>
	</div>
	
</body>
</html>