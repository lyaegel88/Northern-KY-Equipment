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
	<title>Edit User</title>
  
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
	<div class="row justify-content-center">
			
 <div class="col-sm-12 col-md-12 col-lg-4" style="margin-top: 10px; padding-bottom: 10px; padding-top: 25px">
	<form:form method="POST" modelAttribute="user" class="form-horizontal" enctype="multipart/form-data">
		<fieldset>
				<div class="form-group">
				<form:input type="hidden" id="username" path="username" class="form-control" value="${username}" />
				</div>
				<div class="form-group">
					<label for="currentPassword">Old Password</label>
						<form:input id="currentPassword" path="currentPassword" type="password" class="form-control" />
				</div>
				<div class="form-group">
					<label for="newPassword">New Password</label>
						<form:input id="newPassword" path="newPassword" type="password" class="form-control" />
				</div>

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
				<input type="submit" id="btnAdd" class="btn btn-primary" value ="Save Password" onclick="return confirm('Are you sure you want to update this user?')"/>
				</div>
				<br>
				<p><a href="${pageContext.request.contextPath}/admin/user/manage"><button type="button" class="btn btn-dark">Back to User Info</button></a></p>
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