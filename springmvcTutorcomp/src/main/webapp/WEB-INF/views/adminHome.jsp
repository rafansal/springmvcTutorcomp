<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring MVC 5 - form handling | Java Guides</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h2 class="text-center">Admin Home</h2>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Add Customer</div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th style="text-align:center"><a href="/springmvcTutorcomp/admin/studentsList">Student
									List</a></th>
							<th style="text-align:center"><a href="/springmvcTutorcomp/admin/studentsList">Tutor
									List</a></th>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>