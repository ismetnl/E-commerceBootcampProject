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

	ProductManager productManager = new ProductManager();
	Product product = new Product();
		
	 
	//Product and message field
	String productName = "";
	double salesPrice = 0;
	String imagePath = "";
	String message = "";
	long categoryId = 0;
	
	if(request.getParameter("Create") != null){
		productName = request.getParameter("ProductName");
		imagePath = request.getParameter("ImagePath");
		
		try{
			salesPrice = Double.parseDouble(request.getParameter("SalesPrice"));
		}
		catch(NumberFormatException nfe){
			message = "sales price alanı sayı olmalıdır";
		}
		try{
			categoryId = Long.parseLong(request.getParameter("CategorySelect"));
		}
		catch(NumberFormatException nfe){
			message = "Category seçmeniz lazım";
		}
		
		if(productName == ""){
			message = "Product Name alanı boş olmamalıdır";
		}
		
		if(message == ""){
			
			Category category = categoryManager.find(categoryId);
			
			product.setProductName(productName);
			product.setSalesPrice(salesPrice);
			product.setCategory(category);
			product.setImagePath(imagePath);
			
			productManager.insert(product);
		}
		
		response.sendRedirect("ProductSummary.jsp");
	}
	if(request.getParameter("Back") != null){
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
<title>Product Create</title>
</head>
<body>
	<%@include file="/includes/adminNavbar.jsp"%>
	<div class="conteiner">
 		<div class = "row">
 			<div class = "col-md-1"></div>
 			<div class = "col-md-10">
				<h1 class="mb-3">Product Create</h1>
 				<form action="ProductCreate.jsp" method="post">
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
									<option value="" selected disabled hidden="hidden">Choice Category</option>
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
						<label style="color: red;"><%=message%></label>
						<input type="submit" name="Create" class="btn btn-success" value="Create">
						<input type="submit" name="Back" class="btn btn-danger" value="Back">
					</div>
				</form>
 			</div>
 			<div class = "col-md-1"></div>
 			
 		</div>
	</div>
</body>
</html>