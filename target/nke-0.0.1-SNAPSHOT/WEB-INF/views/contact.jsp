<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<% pageContext.setAttribute("currentYear", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)); %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible"content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Contact Us</title>
  
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

<script>
function ValidationCheck()
{
	var customerName = document.forms["AddProduct"]["customerName"]
	var customerEmail = document.forms["AddProduct"]["customerEmail"]
	var customerPhone = document.forms["AddProduct"]["customerPhone"]
	
	if (customerName.value == "")                                  
    { 
        window.alert("Please enter a NAME"); 
        customerName.focus(); 
        
        return false; 
    } 
	
	if (customerEmail.value == "")                                  
    { 
        window.alert("Please enter an EMAIL ADDRESS"); 
        customerEmail.focus(); 
        
        return false; 
    } 
	
	if (customerPhone.value == "")                                  
    { 
        window.alert("Please enter a PHONE NUMBER"); 
        customerPhone.focus(); 
        
        return false; 
    } 
	
	
	return true;
	
	}
</script>
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
    </ul>
  </div>  
</nav>
	
<section class="container">
	<p>${success}</p>
	<p>Call us at <b>859-919-0567</b> or fill out the contact form below.</p>
	<form:form method="POST" modelAttribute="emailForm" class="form-horizontal" enctype="multipart/form-data" name="AddProduct" onsubmit="return ValidationCheck()">
		<fieldset>
				<div class="form-group">
					<label for="customerName">Name</label>
						<form:input id="customerName" path="customerName" type="text" class="form-control" name="customerName"/>
				</div>
				<div class="form-group">
					<label for="customerEmail">Email</label>
					
						<form:input id="customerEmail" path="customerEmail" type="text" class="form-control" name="customerEmail"/>
					
				</div>
				<div class="form-group">
					<label for="customerPhone">Phone Number</label>
					
						<form:input id="customerPhone" path="customerPhone" type="text" class="form-control" name="customerPhone"/>
					
				</div>
				<div class="form-group">
					<label for="customerInquiry">Leave us a message</label>
					
						<form:textarea rows="4" cols="50" id="customerInquiry" path="customerInquiry" type="text" class="form-control" name="customerMessage" placeholder="I am interested in some equipment!"/>
					
				</div>
				
				
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
				<input type="submit" id="btnAdd" class="btn btn-primary" value ="Contact"/>
				</div>
			</div>
		</fieldset>
	</form:form>
</section>


<div class="jumbotron jumbotron-fluid text-center" style="margin-bottom: 0px; margin-top: 50px">
	<h5>Northern Kentucky Equipment</h5>
	<p>Copyright &copy; <c:out value="${currentYear}" /></p>
</div>
	
</body>
</html>