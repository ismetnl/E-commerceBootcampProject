<%@page import="java.util.ArrayList"%>
<%@page import="com.ibtech.sales.inventory.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.ibtech.sales.inventory.manager.ProductManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	ProductManager productManager = new ProductManager();
	List<Product> productList = productManager.list();
	List<Product> products = new ArrayList<>();
	
	String spageid=request.getParameter("page");  
    double totaldiv=5;
    int total = 5;
    int pageCount = (int)Math.ceil(productList.size()/totaldiv);
    int pageId;
    
    if(spageid == null){
    	pageId = 0;
    	products = productManager.getListRecord(pageId, total);
    }
    else{
     	pageId = Integer.parseInt(spageid);
     	products = productManager.getListRecord((pageId*total), total);
    }
	
	String deleteProductId = request.getParameter("Delete");
	String detailProductId = request.getParameter("Detail");
	
	if(deleteProductId != null){
		productManager.delete(Long.parseLong(deleteProductId));
		response.sendRedirect("ProductSummary.jsp");
	}
	if(detailProductId != null){
		session.setAttribute("detailProductId", detailProductId);
		response.sendRedirect("ProductDetails.jsp");
	}
	if(request.getParameter("Add") != null){
		response.sendRedirect("ProductCreate.jsp");
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
<title>Products</title>
</head>
<body>
	<%@include file="/includes/adminNavbar.jsp"%>
	<form action="ProductSummary.jsp" method="post">
		<div class = "conteiner">
			<div class="row">
				<div class = "col-md-2"></div>
				<div class = "col-md-8">
					<h1 class="mb-3">Products</h1>
					<table class="table table-striped">
						<thead class="thead-dark">
						    <tr>
						    	<th scope="col">#</th>
						        <th scope="col">Name</th>		        
						    	<th scope="col">Category</th>	
						    	<th></th>
						    </tr>
						</thead>
						<tbody>
							<%for(Product product:products){%>
								<tr>
									<td scope="row"><%=product.getProductId()%></td>
									<td><%=product.getProductName()%></td>
									<td><%=product.getCategory().getCategoryName() %></td>
									<td align="right">
										<button type="submit" name="Delete" class="btn btn-danger" value="<%=product.getProductId()%>">Delete</button>
										<button type="submit" name="Detail" class="btn btn-info" value="<%=product.getProductId()%>">Detail</button>
									</td>
								</tr>
							<%}%>
						</tbody>		
					</table>
					<table>
							<tbody>
							    <tr>
									<td width="1%" ><button type="submit" name="Add" class="btn btn-success" value="Add">Add</button></td>
									<td width="99%" align="right">
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
							</tbody>
					</table>
				</div>
				<div class = "col-md-2"></div>
			</div>
		</div>
	</form>
</body>
</html>