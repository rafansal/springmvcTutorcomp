<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>javaguides.net</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2>CRM - Tutor Relationship Manager</h2>
			<hr />

			<input type="button" value="Add Tutor"
				onclick="window.location.href='showFormTutor'; return false;"
				class="btn btn-primary" /> <br /> <br />
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Tutor List</div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>Name</th>
							<th>Subject</th>
							<th>Email</th>
							<th>Action</th>
						</tr>

						<!-- loop over and print our tutors -->
						<c:forEach var="tempTutor" items="${tutors}">

							<!-- construct an "update" link with tutor id -->
							<c:url var="updateLink" value="/admin/updateFormTutor">
								<c:param name="tutorId" value="${tempTutor.id}" />
							</c:url>

							<!-- construct an "delete" link with tutor id -->
							<c:url var="deleteLink" value="/admin/deleteTutor">
								<c:param name="tutorId" value="${tempTutor.id}" />
							</c:url>

							<tr>
								<td>${tempTutor.name}</td>
								<td>${tempTutor.subject}</td>
								<td>${tempTutor.email}</td>

								<td>
									<!-- display the update link --> <a href="${updateLink}">Update</a>
									| <a href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete this tutor?'))) return false">Delete</a>
								</td>

							</tr>

						</c:forEach>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>
</html>