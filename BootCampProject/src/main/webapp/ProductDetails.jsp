<%@page import="com.ibtech.sales.inventory.entity.Product"%>
<%@page import="com.ibtech.sales.inventory.manager.ProductManager"%>
<%@page import="java.util.List"%>
<%@page import="com.ibtech.sales.inventory.entity.Category"%>
<%@page import="com.ibtech.sales.inventory.manager.CategoryManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	//Entity, EntityList and Entity manager creating
	CategoryManager categoryManager = new CategoryManager();
	List<Category> categoryList = categoryManager.list();

	String Id = (String)session.getAttribute("detailProductId");
	long productId = Long.parseLong(Id);
	
	ProductManager productManager = new ProductManager();
	Product product = productManager.find(productId);
		
	 
	//Product and message field
	String productName = product.getProductName();
	double salesPrice = product.getSalesPrice();
	String imagePath = product.getImagePath();
	String salesmessage = "";
	String message = "";
	
	
	if(request.getParameter("Save") != null){
		productName = request.getParameter("ProductName");
		imagePath = request.getParameter("ImagePath");
		try{
			salesPrice = Double.parseDouble(request.getParameter("SalesPrice"));
		}
		catch(NumberFormatException nfe){
			salesmessage = "sales price alanı sayı olmalıdır";
		}
		if(productName == ""){
			message = "Product Name alanı boş olmamalıdır";
		}
		else if(salesmessage != ""){
			message = salesmessage;
		}
		else{
			
			Category category = categoryManager.find(Long.parseLong(request.getParameter("CategorySelect")));
			
			product.setProductId(productId);
			product.setProductName(productName);
			product.setSalesPrice(salesPrice);
			product.setCategory(category);
			product.setImagePath(imagePath);
			
			productManager.update(product);

		}
		
		
	}
	if(request.getParameter("Back") != null){
		session.removeAttribute("detailProductId");
		response.sendRedirect("ProductSummary.jsp");
	}

%>

<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Product Details</title>
</head>
<body>
	<%@include file="/includes/adminNavbar.jsp"%>
	<div class="conteiner">
		<div class = "row">
			<div class = "col-md-1"></div>
	 		<div class = "col-md-10">
	 			<h1 class="mb-3">Product Details</h1>
		 		<form action="ProductDetails.jsp" method="post">
					<table>
						<tbody>
							<tr>
								<td><label for="ProductName" class="text-info">Product Name:</label></td>
								<td><input type="text" name="ProductName" value="<%=productName%>" class="form-group"></td>
							</tr>
							<tr>
								<td><label for="SalesPrice" class="text-info">Sales Price:</label></td>
								<td><input type="number" name="SalesPrice" value="<%=salesPrice%>" class="form-group"></td>
							</tr>
							<tr>
								<td><label for="Category" class="text-info">Category:</label></td>
								<td>
									<select class="form-select" name="CategorySelect">
										<option value="" selected disabled hidden="hidden"><%=product.getCategory().getCategoryName()%></option>
									<%for(Category category:categoryList){%>
										<option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%></option>
									<%}%>
								</select>
							</td>
							</tr>
							<tr>
								<td><label for="ImagePath" class="text-info">Image Path:</label></td>
								<td><input type="text" name="ImagePath" value="<%=imagePath%>" class="form-group"></td>
							</tr>	
						</tbody>
		 			</table> 
					<div class="form-group">
						<label style="color: red;"><%=message%></label><br>
						<input type="submit" name="Save" class="btn btn-success" value="Save">
						<input type="submit" name="Back" class="btn btn-danger" value="Back">
					</div>
				</form>
	 		</div>
	 		<div class = "col-md-1"></div>
		</div>
	</div>
</body>
</html>