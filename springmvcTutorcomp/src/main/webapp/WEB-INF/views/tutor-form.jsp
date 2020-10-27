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
			<h2 class="text-center">Spring MVC 5 + Hibernate 5 + JSP + MySQL
				Example</h2>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Add Tutor</div>
				</div>
				<div class="panel-body">
					<form:form action="saveTutor" cssClass="form-horizontal"
						method="post" modelAttribute="tutor">

						<!-- need to associate this data with tutor id -->
						<form:hidden path="id" />
						<form:hidden path="userId" />
						<div class="form-group">
							<label for="name" class="col-md-3 control-label">First
								Name</label>
							<div class="col-md-9">
								<form:input path="name" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="subject" class="col-md-3 control-label">Subject</label>
							<div class="col-md-9">
								<form:input path="subject" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-md-3 control-label">Email</label>
							<div class="col-md-9">
								<form:input path="email" cssClass="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="userId" class="col-md-3 control-label">User Id</label>
							<div class="col-md-9">
								<form:input path="userId" cssClass="form-control"  disabled="true" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="userName" class="col-md-3 control-label">User Name</label>
							<div class="col-md-9">
								<form:input path="userName" cssClass="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="password" class="col-md-3 control-label">Password</label>
							<div class="col-md-9">
								<form:input path="password" cssClass="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<form:button cssClass="btn btn-primary">Submit</form:button>
							</div>
						</div>

					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>