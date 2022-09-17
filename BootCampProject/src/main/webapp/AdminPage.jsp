<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Admin</title>
<style>
h1 {
text-align: center;
font-family: Arial, Helvetica, sans-serif;
} 

</style>
</head>
<body > 
	<%@include file="/includes/adminNavbar.jsp"%>
	<h1>Welcome <%=session.getAttribute("auth") %></h1> 
</body>
</html>