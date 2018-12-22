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
	<title>Categories</title>
  
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
	   <li>
	     <a class="nav-link" href="${pageContext.request.contextPath}/admin"><span style="color: white;">Admin</span></a>
	    </li>
	    <li>
	     <a class="nav-link" href="${pageContext.request.contextPath}/logout"><span style="color: white;">Logout</span></a>
	    </li>
    </ul>
  </div>  
</nav>
	


<section class="container">
<p>${deleted}</p>
	<div class="row">
		<c:forEach items="${categories}" var="categories">
			<div class="col-sm-6 col-md-4 col-lg-3 align-self-end">
				<div class="card" style="margin-bottom: 10px; border: none; padding-top: 10px">
						<a href="${pageContext.request.contextPath}/admin/categories/products?category=${categories.categoryId }&page=1">
						<img  src=${categories.photoUrl } alt="Category Picture" height=100% width=100% style="object-fit: cover">
						</a>
						<h3 class="card-header" style="text-align: center; border: 1px solid silver">${categories.title}</h3>
				</div>
			</div>
		</c:forEach>
	</div>
	<br>
	<div class="container">
			<ul class="pagination">
				 <c:if test="${pagenumber > 1}">
				  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories?page=${pagenumber - 1}">Previous</a></li>
				</c:if>
				 	<li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories?page=${pagenumber}">${pagenumber}</a></li>
					<c:if test="${pagenumber + 1 <= maxpages}">
				 	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories?page=${pagenumber + 1}">${pagenumber + 1}</a></li>
					</c:if>
					<c:if test="${pagenumber + 2 <= maxpages}">
				 	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories?page=${pagenumber + 2}">${pagenumber + 2}</a></li>
					</c:if>
				<c:if test="${pagenumber + 1 <= maxpages}">
				  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories?page=${pagenumber + 1}">Next</a></li>
				</c:if>
				</ul>
				
				<ul class="pagination">
  					  <c:if test="${pagenumber <= maxpages && pagenumber !=1}">
				  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories?page=1">First</a></li>
				</c:if>
				<c:if test="${pagenumber < maxpages && maxpages != 1}">
				  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/categories?page=${maxpages}">Last</a></li>
				</c:if>
				</ul>
				
				<p><a href="${pageContext.request.contextPath}/admin"><button type="button" class="btn btn-dark">Back to Admin</button></a></p>
	</div>
</section>

<div class="jumbotron jumbotron-fluid text-center" style="margin-bottom: 0px">
	<h5>Northern Kentucky Equipment</h5>
	<p>Copyright &copy; <c:out value="${currentYear}" /></p>
</div>
	
</body>
</html>