<%
	if(session.getAttribute("auth") == null){
		response.sendRedirect("AdminLogin.jsp");
	}


%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="AdminPage.jsp">Admin</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="AdminPage.jsp">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="CategorySummary.jsp">Categories</a>
      <a class="nav-item nav-link" href="ProductSummary.jsp">Product</a>
      <a class="nav-item nav-link" href="CartSummary.jsp">Cart</a>
      <a class="nav-item nav-link" href="UserSummary.jsp">User</a>
    </div>
   	<div class="navbar-nav ml-auto">
   	  <a class="nav-item nav-link" href="Logout.jsp">Logout</a>
   	</div>
  </div>
</nav>
