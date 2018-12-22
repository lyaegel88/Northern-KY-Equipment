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
	<title>NKE Home</title>
  
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
          
#toggleBackground{          
background-image: url(img/pexels-photo-356977.jpeg); 
background-size: cover; 
background-repeat: no-repeat;

}


/* Portrait tablets and small desktops */
@media (min-width: 768px) and (max-width: 991px) {

           #toggleBackground {
        background-image: none;
    }

}

/* Landscape phones and portrait tablets */          
@media (max-width: 767px) {

          #toggleBackground {
        background-image: none;
    }

}
          
</style>
</head>
<body class="body">
	
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="${pageContext.request.contextPath}/"><picture>
  <source srcset="img/logo_transparent_3.png" media="(max-width: 500px)">
  <source srcset="img/logo_transparent_3.png">
  <img id="change" src="img/logo_transparent_5.png">
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


<section>
<div class="container-fluid">
<div class="row justify-content-center" id="toggleBackground" style="padding-bottom: 20px;">
<div class="col-sm-4 col-md-12 col-lg-4" style="margin-top:15px;">
		<div class="alert alert-secondary text-center"><h4>Featured Brands</h4></div>	
		<div class="container-fluid">
		 <div class="row">
			<div class="col-sm-6 col-md-6 col-lg-6">
				<div class="card" style="margin-top: 10px">
				<a href="https://altoz.com/"><img class="card-img-top" src="img/altoz_logo.png" width="100%" height="100%"></a>
				
					
				
				</div>
			</div>
			
			<div class="col-sm-6 col-md-6 col-lg-6">
				<div class="card" style="margin-top: 10px">
				<a href="https://www.gravely.com/en-us"><img class="card-img-top" src="img/gravely_logo.png" width="100%" height="100%"></a>
				
				
				
				</div>
			</div>
			
			</div>	
			</div>
		</div>
<div class="col-sm-4 col-md-12 col-lg-4" style="margin-top:15px">
			<div id="demo" class="carousel slide" data-ride="carousel">
		
		  <!-- Indicators -->
		  <ul class="carousel-indicators">
		    <li data-target="#demo" data-slide-to="0" class="active"></li>
		    <li data-target="#demo" data-slide-to="1"></li>
		    <li data-target="#demo" data-slide-to="2"></li>
		  </ul>
		
		  <!-- The slideshow -->
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="${random1.photoUrl}" alt="NKYE Photo" width="100%" height="100%">
		        <div class="carousel-caption d-md-block">
		   			 <h4 style="border: 1px solid black; background:rgba(255,255,255,0.5); color: black;">${random1.title}</h4>
		 		 </div>
		    </div>
		    <div class="carousel-item">
		      <img src="${random2.photoUrl}" alt="NKYE Photo" width="100%" height="100%">
		      <div class="carousel-caption d-md-block">
		   			 <h4 style="border: 1px solid black; background:rgba(255,255,255,0.5); color: black;">${random2.title}</h4>
		 		 </div>
		    </div>
		    <div class="carousel-item">
		      <img src="${random3.photoUrl}" alt="NKYE Photo" width="100%" height="100%">
		      <div class="carousel-caption d-md-block">
		   			 <h4 style="border: 1px solid black; background:rgba(255,255,255,0.5); color: black;">${random3.title}</h4>
		 		 </div>
		    </div>
		  </div>
		
		  <!-- Left and right controls -->
		  <a class="carousel-control-prev" href="#demo" data-slide="prev">
		    <span class="carousel-control-prev-icon"></span>
		  </a>
		  <a class="carousel-control-next" href="#demo" data-slide="next">
		    <span class="carousel-control-next-icon"></span>
		  </a>

			</div>
</div>
	<div class="col-sm-4 col-md-12 col-lg-4" style="margin-top:15px">
		<div class="alert alert-secondary text-center"><h4>Recently Added</h4></div>	
		<div class="container-fluid">
		 <div class="row">
			<div class="col-sm-6 col-md-6 col-lg-6">
				<div class="card" style="margin-top: 10px">
				<img class="card-img-top" src="${moreInfo1.photoUrl}" width="100%" height="100%">
				<h4 class="card-header">${moreInfo1.title}</h4>
					<div class="card-body">
				<p>Added: ${moreInfo1.createdAt}</p>
				<c:if test="${moreInfo1.productId ne 10}">
					<p><a href="<spring:url value="/info?id=${moreInfo1.productId}" />"><button type="button" class="btn btn-info">More Info</button></a></p>
				</c:if>
				</div>
				
				</div>
			</div>
			
			<div class="col-sm-6 col-md-6 col-lg-6">
				<div class="card" style="margin-top: 10px">
				<img class="card-img-top" src="${moreInfo2.photoUrl}" width="100%" height="100%">
				<h4 class="card-header">${moreInfo2.title}</h4>
				<div class="card-body">
				<p>Added: ${moreInfo2.createdAt}</p>
				<c:if test="${moreInfo2.productId ne 12}">
					<p><a href="<spring:url value="/info?id=${moreInfo2.productId}" />"><button type="button" class="btn btn-info">More Info</button></a></p>
				</c:if>
				</div>
				
				</div>
			</div>
			
			</div>	
			</div>
		</div>
	</div>
</div>
</section>

<section>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12" style="margin-top: 10px">
		 <div class="alert alert-dark text-center"><h4>SALES &middot MAINTENANCE SERVICE &middot PARTS</h4>
		 <p>TRACTORS &middot ZEROTURN &middot RIDING MOWERS &middot UTV'S &middot IMPLEMENTS &middot MORE</p>
		 </div>
			
		</div>
	</div>
</div>
</section>

<section>
<div class="container-fluid">
<div class="row justify-content-center">
		<div class="col-6" style="margin-top: 10px">
			<h4 style="text-align:center">Location: 3005 Verona Mudlick Rd Verona, KY 41092</h4>
			<p style="text-align:center"><a href="https://www.google.com/maps/dir//3005+Verona-Mudlick+Rd,+Verona,+KY+41092"><button type="button" class="btn btn-dark">Click for Directions!</button></a></p>
		</div>
		
	</div>
</div>
<div class="container-fluid">
<div class="row justify-content-center">
	<div class="col-sm-12 col-md-8 col-lg-8" style="margin-top: 10px">
		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3107.944862137539!2d-84.69419468465155!3d38.83372357958039!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8841e987a9408b25%3A0x5560bef3411d1c0c!2s3005+Verona-Mudlick+Rd%2C+Verona%2C+KY+41092!5e0!3m2!1sen!2sus!4v1538162727278" 
width="100%" height="450px" frameborder="0" style="border:0" allowfullscreen>
		</iframe>
		</div>
	</div>
</div>
</section>

<div class="jumbotron jumbotron-fluid text-center" style="margin-bottom: 0px">
	<h5>Northern Kentucky Equipment</h5>
	<p>Copyright &copy; <c:out value="${currentYear}" /></p>
</div>
	
</body>
</html>