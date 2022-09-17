<%@page import="com.ibtech.sales.inventory.manager.CategoryManager"%>
<%@page import="com.ibtech.sales.inventory.entity.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	String Id = (String)session.getAttribute("detailCategoryId");
	long categoryId = Long.parseLong(Id);
	CategoryManager categoryManager = new CategoryManager();
	Category category = categoryManager.find(categoryId);
	
	String categoryName = category.getCategoryName();
	String message = "";
	
	if(request.getParameter("Save") != null){
		
		categoryName = request.getParameter("CategoryName");
		
		if(categoryName == ""){
			message = "Kategori alanı boş bırakamazsın";
		}
		else{
			category.setCategoryName(categoryName);
			categoryManager.update(categoryId, category);
		}
		
	}
	if(request.getParameter("Back") != null){
		session.removeAttribute("detailCategoryId");
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
<title>Category Details</title>
</head>
<body>
	<%@include file="/includes/adminNavbar.jsp"%>
	<h1 class="mb-3">Category Details</h1>
	<div class="conteiner">
 		<form action="CategoryDetails.jsp" method="post">
			<div class="form-group">
				<label for="CategoryName" class="text-info">Category Name:</label>
				<input type="text" name="CategoryName" value="<%=categoryName%>" class = "form-group">
			</div>
			<div class="form-group">
				<label style="color: red;"><%=message %></label><br>
				<input type="submit" name="Save" class="btn btn-success" value="Save">
				<input type="submit" name="Back" class="btn btn-danger" value="Back">
			</div>
		</form>
	</div>
</body>
</html>