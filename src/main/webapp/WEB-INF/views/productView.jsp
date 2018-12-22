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
	<title>View Product</title>
  
  <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/lawnmower-24-262350.png"/>
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
<body class="body">
	
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="${pageContext.request.contextPath}/"><picture>
  <source srcset="${pageContext.request.contextPath}/img/logo_transparent_3.png" media="(max-width: 500px)">
  <source srcset="${pageContext.request.contextPath}/img/logo_transparent_3.png">
  <img id="change" src="${pageContext.request.contextPath}/img/logo_transparent_5.png">

</picture></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav ml-auto">
       <li class="nav-item active">
	      <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/about"><span style="color: white;">About</span></a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/equipment/categories?page=1"><span style="color: white;">Equipment</span></a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/contact"><span style="color: white;">Contact Us</span></a>
	    </li>
	     <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/finance"><span style="color: white;">Financing</span></a>
	    </li>
    </ul>
  </div>  
</nav>
	


<section class="container">
	<div class="row justify-content-center">
	<div class="col-sm-12 col-md-12 col-lg-8" style="margin-top: 10px; padding-bottom: 10px">
				<img src= ${ product.photoUrl}  alt="Category Photo" width="100%" style="object-fit: contain; margin-top: 10px;">
			</div>
		
		<div class="col-sm-12 col-md-12 col-lg-4" style="margin-top: 10px; padding-bottom: 10px">
			<div class="card">
						<h3 class="card-header" style="text-align: center; border: 1px solid silver">${product.title}</h3>
						<div class="card-body" style="border: 1px solid silver; border-top: none">
						<p><b>Price:</b> ${product.price}</p>
						<p><b>Brand/Type:</b> ${productCategory }</p>
						<p><b>Added:</b> ${product.createdAt}</p>
						<p><b>Description:</b> ${product.description}</p>
						<p><a href="${pageContext.request.contextPath}/equipment/categories/products?category=${product.categoryId }&page=${pagenumber}"> <button type="button" class="btn btn-block btn-dark">Back</button></a></p>
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