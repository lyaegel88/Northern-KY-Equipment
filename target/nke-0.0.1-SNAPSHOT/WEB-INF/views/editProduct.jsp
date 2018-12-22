<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<% pageContext.setAttribute("currentYear", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)); %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible"content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Edit Product</title>
  
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
	<div class="row justify-content-center">
	<p>${success}</p>
	<div class="col-sm-12 col-md-12 col-lg-6" style="margin-top: 10px; padding-bottom: 10px">
				<h3 style="text-align: center; border: 1px solid black;">Current Photo:</h3>
				<img src= ${ product.photoUrl}  alt="Product Photo" width="100%" style="object-fit: contain; margin-top: 10px;">
			</div>
			
 <div class="col-sm-12 col-md-12 col-lg-4" style="margin-top: 10px; padding-bottom: 10px; padding-top: 25px">
	<form:form method="POST" modelAttribute="product" class="form-horizontal" enctype="multipart/form-data">
		<fieldset>
				<div class="form-group">
				<form:input type="hidden" id="productId" path="productId" class="form-control" value="${product.productId}" />
				</div>
				<div class="form-group">
				<form:input type="hidden" id="categoryId" path="categoryId" class="form-control" value="${product.categoryId}" />
				</div>
				<div class="form-group">
				<form:input type="hidden" id="photoVersion" path="photoVersion" class="form-control" value="${product.photoVersion}" />
				</div>
				<div class="form-group">
				<form:input type="hidden" id="photoId" path="photoId" class="form-control" value="${product.photoId}" />
				</div>
				<div class="form-group">
				<form:input type="hidden" id="publicId" path="publicId" class="form-control" value="${product.publicId}" />
				</div>
				<div class="form-group">
					<label for="title">Product Name</label>
						<form:input id="title" path="title" type="text" class="form-control" placeholder="${product.title }" />
				</div>
				<div class="form-group">
					<label for="price">Price</label>
						<form:input id="price" path="price" type="text" class="form-control" placeholder="${product.price }" />
				</div>
				<div class="form-group">
					<label for="description">Description</label>
						<form:input id="description" path="description" type="text" class="form-control" placeholder="${product.description }" />
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="productImage">Upload New Image?</label>
					<div class="col-lg-10">
					<form:input id="productImage" path="productImage" type="file" class="form:input-large" />
					</div>
				</div>

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
				<input type="submit" id="btnAdd" class="btn btn-primary" value ="Update" onclick="return confirm('Are you sure you want to update this product?')"/>
				</div>
				<br>
				<p><a href="${pageContext.request.contextPath}/admin/categories/products/manage?id=${product.productId }&category=${categoryId }&page=${pagenumber}"><button type="button" class="btn btn-dark">Back to Product</button></a></p>
			</div>
		</fieldset>
	</form:form>
	</div>
 </div>
</section>

<div class="jumbotron jumbotron-fluid text-center" style="margin-bottom: 0px; margin-top: 50px;">
	<h5>Northern Kentucky Equipment</h5>
	<p>Copyright &copy; <c:out value="${currentYear}" /></p>
</div>
	
</body>
</html>