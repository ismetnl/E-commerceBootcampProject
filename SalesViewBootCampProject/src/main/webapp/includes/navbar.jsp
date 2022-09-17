<%@page import="com.ibtech.sales.xml.CategoryXml"%>
<%@page import="com.ibtech.sales.inventory.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.ibtech.core.utils.XmlHelper"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="com.ibtech.core.utils.WebHelper"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ibtech.sales.xml.CartProductXml"%>
<%@page import="com.ibtech.shopping.entity.CartProduct"%>
<%
	List<Category> categories = null;
	categories = new ArrayList<>();
	String adress = "http://localhost:8080/BootCampProject/api/categories";
	InputStream in = WebHelper.get(adress);
	Document document = XmlHelper.parse(in);
	categories = CategoryXml.parseList(document);
	
%> 
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="MainPage.jsp">e-Commerce</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">  
				<li class="nav-item"><a class="nav-link" href="MainPage.jsp">Home</a></li>
				<div class="btn-group">
				  <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:gray;">
				    Categories
				  </button>
				  <div class="dropdown-menu">
				    <%for(Category category:categories){%>
						<a class="dropdown-item" href="ProductList.jsp?categoryId=<%=category.getCategoryId()%>"><%=category.getCategoryName()%></a>
					<%}%>
				  </div>
				</div> 
				<li class="nav-item"><a class="nav-link" href="CartView.jsp">Cart <span class="badge badge-danger"><%=size%></span> </a></li>
				<%if (auth != null) {%>
					<li class="nav-item"><a class="nav-link" href="Logout.jsp">Logout</a></li>
				<%
				}else{
				%>
					<li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a></li>
				<%}%>
			</ul>
		</div>
	</div>
</nav>