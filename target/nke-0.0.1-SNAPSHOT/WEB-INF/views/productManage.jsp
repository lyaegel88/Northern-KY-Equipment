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
	<title>Manage Product</title>
  
  <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}img/lawnmower-24-262350.png"/>
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
	<div class="row justify-content-center">
	<div class="col-sm-12 col-md-12 col-lg-8" style="margin-top: 10px; padding-bottom: 10px">
	<p>${success}</p>
				<img src= ${ product.photoUrl}  alt="Category Photo" width="100%" style="object-fit: contain; margin-top: 10px;">
			</div>
		
		<div class="col-sm-12 col-md-12 col-lg-4" style="margin-top: 10px; padding-bottom: 10px">
			<div class="card">
						<h3 class="card-header" style="text-align: center; border: 1px solid silver">${product.title}</h3>
						<div class="card-body" style="border: 1px solid silver; border-top: none">
						<p>Product ID: ${product.productId}</p>
						<p>Price: ${product.price}</p>
						<p>Brand/Type: ${productCategory }</p>
						<p>Created: ${product.createdAt}</p>
						<p>Updated: ${product.updatedAt}</p>
						<p>Photo Location: ${product.photoUrl}</p>
						<p>Photo Version: ${product.photoVersion}</p>
						<p>Photo Public ID: ${product.publicId}</p>
						<p>Description: ${product.description}</p>
						<p><a href="${pageContext.request.contextPath}/admin/categories/products/edit?id=${product.productId }&category=${product.categoryId }&page=${pagenumber}"> <button type="button" class="btn btn-block btn-warning">Edit</button></a></p>
						<form method="POST" action="/admin/categories/products/delete">
							<input type="hidden" id="id" name="id" value="${product.productId}" />
							<input type="hidden" id="category" name="category" value="${product.categoryId}" />
							<input type="submit" id="btnAdd" class="btn btn-block btn-danger" value ="Delete" onclick="return confirm(' Are you sure you want to delete this?')"/>
						</form>
						<br>
						<p><a href="${pageContext.request.contextPath}/admin/categories/products?category=${product.categoryId }&page=${pagenumber}"> <button type="button" class="btn btn-block btn-dark">Back</button></a></p>
						</div>
				</div>
		</div>
	</div>
</section>

<div class="jumbotron jumbotron-fluid text-center" style="margin-bottom: 0px; margin-top: 50px">
	<h5>Northern Kentucky Equipment</h5>
	<p>Copyright &copy; <c:out value="${currentYear}" /></p>
</div>
	
</body>
</html>