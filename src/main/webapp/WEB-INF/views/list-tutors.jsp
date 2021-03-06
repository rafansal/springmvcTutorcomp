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
			<h2>Tutor List</h2>
			<hr />

			<input type="button" value="Add Tutor"
				onclick="window.location.href='showFormTutor'; return false;"
				class="btn btn-primary" />
						<button onclick="goBack()">Go Back</button>
						<script>
							function goBack() {
							  window.history.back();
							}
						</script>
					
				<tb><a href="/springmvcTutorcomp/login">Logout</a></th>
				<br /> <br />
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Tutor List</div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Status</th>
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
								<td>${tempTutor.firstName}</td>
								<td>${tempTutor.lastName}</td>
								<td>${tempTutor.email}</td>
								<td>
									<c:choose>
								        <c:when test="${tempTutor.status == 1}">Active</c:when>
								        <c:when test="${tempTutor.status == 2}">Modified</c:when>
								        <c:when test="${tempTutor.status == 3}">Deleted</c:when>
								        <c:otherwise>undefined</c:otherwise>
								    </c:choose>
							    </td>

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