<%@page import="java.util.ArrayList"%>
<%@page import="com.ibtech.sales.inventory.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.ibtech.sales.inventory.manager.UserManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
	UserManager userManager = new UserManager();
	List<User> userList = userManager.list();
	List<User> users = new ArrayList<>();
	String spageid=request.getParameter("page");  
	double totaldiv=5;
	int total = 5;
	int pageCount = (int)Math.ceil(userList.size()/totaldiv);
	int pageId;
	
	if(spageid == null){
		pageId = 0;
		users = userManager.getListRecord(pageId, total);
	}
	else{
	 	pageId = Integer.parseInt(spageid);
	 	users = userManager.getListRecord((pageId*total), total);
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
	<form action="CartSummary.jsp" method="get">
		<div class="conteiner">
			<div class="row">
					<div class = "col-md-2"></div>
					<div class="col-md-8">
						<h1 class="mb-3">Users</h1>
						<table class="table table-striped">
							<thead class="thead-dark">
							    <tr>
							    	<th scope="col">#</th>
							        <th scope="col">Name</th>
							    </tr>
							</thead>
							<tbody>
								<%for(User user:users){%>
									<tr >
										<th scope="row"><%=user.getUserId()%></th>
										<td><%=user.getUserName()%></td>
									</tr>
								<%}%>
							</tbody>
							<tfoot>
								 <tr>
									<td colspan="2" align="right">
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