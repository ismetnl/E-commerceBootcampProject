<%@page import="com.ibtech.sales.inventory.manager.CategoryManager"%>
<%@page import="com.ibtech.sales.inventory.entity.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	CategoryManager categoryManager = new CategoryManager();
	Category category = new Category();
	
	String categoryName = "";
	String message = "";
	
	if(request.getParameter("Create") != null){
		
		categoryName = request.getParameter("CategoryName");
		
		if(categoryName == ""){
			message = "Kategori alanı boş bırakamazsın";
		}
		else{
			category.setCategoryName(categoryName);
			categoryManager.insert(category);
		}
		
	}
	if(request.getParameter("Back") != null){
		response.sendRedirect("CategorySummary.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Category Create</title>
</head>
<body>
	<%@include file="/includes/adminNavbar.jsp"%>
	<div class="conteiner">
		<div class="row">
			<div class = "col-md-1"></div>
			<div class = "col-md-8">
				<h1 class="mb-3" style="padding-bottom: 40px;">Category Create</h1>
		 		<form action="CategoryCreate.jsp" method="post">
					<div class="form-group">
						<label for="CategoryName" class="text-info">Category Name:</label>
						<input type="text" name="CategoryName" value="<%=categoryName%>" class = "form-group">
					</div>
					<div class="form-group">
						<label style="color: red;"><%=message %></label><br>
						<input type="submit" name="Create" class="btn btn-success" value="Create">
						<input type="submit" name="Back" class="btn btn-danger" value="Back">
					</div>
				</form>
			</div>
			<div class = "col-md-3"></div>
		</div>
	</div>
</body>
</html>