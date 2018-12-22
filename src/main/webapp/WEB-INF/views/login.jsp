<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>NKE Login</title>
	</head>
	
<body style="background-image: url(img/pexels-photo-55766.jpeg); background-size: cover; background-repeat: no-repeat;">

<div class="container">
		<div class="row" style="margin-top: 15%">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="text-align: center"><b>NKE Login</b></h3>
					</div>
				<div class="panel-body">
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post" class="form-horizontal">
			<c:if test="${param.error != null}">
			<div class="alert alert-danger">
				<p>Invalid username and password.</p>
			</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">
					<p>You have been logged out successfully.</p>
				</div>
			</c:if>
			<c:if test="${param.accessDenied !=null}">
				<div class="alert alert-danger">
					<p>Access Denied: You are not authorized! </p>
				</div>
			</c:if>
			<div class="input-group input-sm">
				<label class="input-group-addon" for="username">
					<i class="fa fa-user"></i>Username</label>
			<input type="text" class="form-control" id="userId" name="userId" placeholder="Enter Username" required>
			</div>
			<div class="input-group input-sm">
				<label class="input-group-addon" for="password">
					<i class="fa fa-lock"></i>Password</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
			</div>
			<div class="form-actions">
				<input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
			</div>
			</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>