<%@page import="java.util.ArrayList"%>
<%@page import="com.ibtech.sales.inventory.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.ibtech.sales.inventory.manager.CategoryManager"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	CategoryManager categoryManager = new CategoryManager();
	List<Category> categ = categoryManager.list();
	List<Category> categories = new ArrayList<>();
	String spageid=request.getParameter("page");  
    double totaldiv=5;
    int total = 5;
    int pageCount = (int)Math.ceil(categ.size()/totaldiv);
    int pageId;
    
    if(spageid == null){
    	pageId = 0;
    	categories = categoryManager.getListRecord(pageId, total);
    }
    else{
     	pageId = Integer.parseInt(spageid);
     	categories = categoryManager.getListRecord((pageId*total), total);
    }
    
	String deleteCategoryId = request.getParameter("Delete");
	String detailCategoryId = request.getParameter("Detail");
	

	if(deleteCategoryId != null){
		categoryManager.delete(Long.parseLong(deleteCategoryId));
		response.sendRedirect("CategorySummary.jsp");
	}
	if(detailCategoryId != null){
		session.setAttribute("detailCategoryId", detailCategoryId);
		response.sendRedirect("CategoryDetails.jsp");
	}
	if(request.getParameter("Add") != null){
		response.sendRedirect("CategoryCreate.jsp");
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
<title>Category</title>

</head>
<body>
	<%@include file="/includes/adminNavbar.jsp"%>
	<form action="CategorySummary.jsp" method="post">
		<div class="conteiner">
			<div class="row">
					<div class = "col-md-2"></div>
					<div class="col-md-8">
						<h1 class="mb-3">Categories</h1>
						<table class="table table-striped">
							<thead class="thead-dark">
							    <tr>
							    	<th scope="col">#</th>
							        <th scope="col">Name</th>
							        <th></th>
							    </tr>
							</thead>
							<tbody>
								<%for(Category category:categories){%>
									<tr >
										<th scope="row"><%=category.getCategoryId()%></th>
										<td><%=category.getCategoryName()%></td>
										<td align="right">
											<button type="submit" name="Delete" class="btn btn-danger" value="<%=category.getCategoryId()%>">Delete</button>
											<button type="submit" name="Detail" class="btn btn-info" value="<%=category.getCategoryId()%>">Detail</button>
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