<%@page import="java.util.ArrayList"%>
<%@page import="com.ibtech.sales.inventory.entity.CartProduct"%>
<%@page import="java.util.List"%>
<%@page import="com.ibtech.sales.inventory.manager.CartProductManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	long cartId = Long.parseLong(request.getParameter("cartId"));
	CartProductManager cartProductManager = new CartProductManager();
	List<CartProduct> cartProductList = cartProductManager.list(cartId);
	List<CartProduct> cartProducts = new ArrayList<>();
	String spageid=request.getParameter("page");  
	double totaldiv=5;
	int total = 5;
	int pageCount = (int)Math.ceil(cartProductList.size()/totaldiv);
	int pageId;
	
	if(spageid == null){
		pageId = 0;
		cartProducts = cartProductManager.getListRecord(cartId,pageId, total);
	}
	else{
	 	pageId = Integer.parseInt(spageid);
	 	cartProducts = cartProductManager.getListRecord(cartId,pageId, total);
	}



%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="styles/pagination.css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/includes/adminNavbar.jsp"%>
	<form action="CartDetails.jsp" method="get">
		<div class="conteiner">
			<div class="row">
					<div class = "col-md-2"></div>
					<div class="col-md-8">
					<h1 class="mb-3">Cart Products</h1>
						<table class="table table-striped">
							<thead class="thead-dark">
							    <tr>
							    	<th scope="col">#</th>
							        <th scope="col">ProductId</th>
							        <th scope="col">Sales Price</th>
							        <th scope="col">Sales Quantity</th>
							        <th scope="col">Tax Rate</th>
							        <th scope="col">Line Amount</th>
							    </tr>
							</thead>
							<tbody>
								<%for(CartProduct cartProduct:cartProducts){%>
									<tr >
										<th scope="row"><%=cartProduct.getCartProductId()%></th>
										<td><%=cartProduct.getProductId()%></td>
										<td><%=cartProduct.getSalesPrice()%></td>
										<td><%=cartProduct.getSalesQuantity()%></td>
										<td><%=cartProduct.getTaxRate()%></td>
										<td><%=cartProduct.getLineAmount()%></td>
									</tr>
								<%}%>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="6" align="right">
										<div class="pagination">
											<button type="submit" name="page_first" class="btn bg-transparent btn-primary-outline" value="&laquo;">&laquo;</button>
											<%
											for(int i=0;i<pageCount;i++){
											%>
												<button type="submit" name="page" class="btn bg-transparent btn-primary-outline" value="<%=i%>"><%=i+1%></button>
											<%}%>
											<button type="submit" name="page_last" class="btn bg-transparent btn-primary-outline" value="&raquo;">&raquo;</button>
										</div>
									</td>														
								</tr>
							</tfoot>
						</table>
						
					</div>
					<div class = "col-md-2"></div>
			</div>
			
		</div>

	</form>
</body>
</html>