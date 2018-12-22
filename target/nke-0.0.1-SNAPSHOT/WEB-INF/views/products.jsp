<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<% pageContext.setAttribute("currentYear", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)); %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible"content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Products</title>
  
  <link rel="shortcut icon" type="image/png" href="img/lawnmower-24-262350.png"/>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		 
          
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
          <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
          <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
          
<style>
    img {
       margin-top: 0px;
       padding: 0px;
       border: 0px;
          }
          
</style>
</head>
<body class="container-fluid">


	
<div class="jumbotron jumbotron-fluid" style="padding: 0px; margin-bottom: 0px; background-image: url(${pageContext.request.contextPath}/img/field-agriculture-farm-grass-21.jpg); background-size: cover; background-repeat: no-repeat;">
<picture>
  <source srcset="${pageContext.request.contextPath}/img/logo_transparent_3.png" media="(max-width: 500px)">
  <source srcset="${pageContext.request.contextPath}/img/logo_transparent_5.png">
  <img id="change" src="${pageContext.request.contextPath}/img/logo_transparent_5.png">
</picture>
</div>
	

	<nav class="navbar navbar-expand-sm bg-success navbar-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
  	<ul class="navbar-nav">
	    <li class="nav-item active">
	      <a class="nav-link" href="${pageContext.request.contextPath}/"><b>Home</b></a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/about"><span style="color: white;"><b>About</b></span></a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/equipment/categories?page=1"><span style="color: white;"><b>Equipment</b></span></a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/contact"><span style="color: white;"><b>Contact Us</b></span></a>
	    </li>
	      
 	 </ul>
 	 </div>
 	 <ul class="navbar-nav ml-auto">
 	 <li>
	     <a class="nav-link" href="${pageContext.request.contextPath}/admin"><span style="color: white;"><b>Admin</b></span></a>
	    </li>
	    <li>
	     <a class="nav-link" href="${pageContext.request.contextPath}/logout"><span style="color: white;"><b>Logout</b></span></a>
	    </li>
	    </ul>
	</nav>
	


<section class="container">
<p>${deleted}</p>
	<div class="row">
	<div class="col-sm-6 col-md-4 col-lg-3">
		<p>${success}</p>
				<div class="card">
					
						<h3 class="card-header" style="text-align: center">Manage</h3>
						<div class="card-body">
						<p><a href="<spring:url value="/admin/categories/products/add?category=${categoryId }" />"><button type="button" class="btn btn-block btn-primary">Add Product</button></a><p>
						<p><a href="<spring:url value="/admin/categories?page=1" />"><button type="button" class="btn btn-block btn-dark">Back to Categories</button></a><p>
						</div>
					
				</div>
			</div>
		<c:forEach items="${products}" var="products">
			<div class="col-sm-6 col-md-4 col-lg-3 align-self-end">
				<div class="card" style="margin-bottom: 10px; border: none; padding-top: 10px">
						<img  src=${products.photoUrl } alt="Product Picture" height=100% width=100% style="object-fit: cover">
						<h3 class="card-header" style="text-align: center; border: 1px solid silver">${products.title}</h3>
						<div class="card-body" style="border: 1px solid silver; border-top: none">
						<p>Created: ${products.createdAt}</p>
						<p>Updated: ${products.updatedAt}</p>
						<p><a href="<spring:url value="/admin/categories/products/manage?id=${products.productId}&category=${categoryId }&page=${pagenumber}" />"><button type="button" class="btn btn-info">Manage</button></a></p>						
						</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<br>
	<div class="container">
			<ul class="pagination">
				 <c:if test="${pagenumber > 1}">
				  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories/products?category=${categoryId }&page=${pagenumber - 1}">Previous</a></li>
				</c:if>
				 	<li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories/products?category=${categoryId }&page=${pagenumber}">${pagenumber}</a></li>
					<c:if test="${pagenumber + 1 <= maxpages}">
				 	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories/products?category=${categoryId }&page=${pagenumber + 1}">${pagenumber + 1}</a></li>
					</c:if>
					<c:if test="${pagenumber + 2 <= maxpages}">
				 	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories/products?category=${categoryId }&page=${pagenumber + 2}">${pagenumber + 2}</a></li>
					</c:if>
				<c:if test="${pagenumber + 1 <= maxpages}">
				  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories/products?category=${categoryId }&page=${pagenumber + 1}">Next</a></li>
				</c:if>
				</ul>
				
				<ul class="pagination">
  					  <c:if test="${pagenumber <= maxpages && pagenumber !=1}">
				  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories/products?category=${categoryId }&page=1">First</a></li>
				</c:if>
				<c:if test="${pagenumber < maxpages && maxpages != 1}">
				  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories/products?category=${categoryId }&page=${maxpages}">Last</a></li>
				</c:if>
				</ul>
	</div>
</section>

<div class="jumbotron jumbotron-fluid text-center" style="margin-bottom: 0px">
	<h5>Northern Kentucky Equipment</h5>
	<p>Copyright &copy; <c:out value="${currentYear}" /></p>
</div>
	
</body>
</html>