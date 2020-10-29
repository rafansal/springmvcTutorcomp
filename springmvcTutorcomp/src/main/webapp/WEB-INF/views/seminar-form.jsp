<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TUTOR COMP</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h2 class="text-center">Seminar Creation</h2>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Add Seminar</div>
				</div>
				<div class="panel-body">
					<form:form action="saveSeminar" cssClass="form-horizontal"
						method="post" modelAttribute="seminar">

						<!-- need to associate this data with tutor id -->
						<form:hidden path="id" />
						<div class="form-group">
						<label for="lastName" class="col-md-3 control-label">Student Name</label>
						<div class="col-md-9">
						<form:select path="studentId">
							<%
								{
											Class.forName("com.mysql.jdbc.Driver").newInstance();
											Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root",
													"admin");
											String Query = "select * from student";
											Statement stm = con.createStatement();
											ResultSet rs = stm.executeQuery(Query);
											while (rs.next()) {
							%>
							<option value="<%=rs.getInt("id")%>"><%=rs.getString("firstName")%>
							</option>
							<%
								}
										}
							%>
						</form:select>
						</div>
						</div>
						<div class="form-group">
						<label for="lastName" class="col-md-3 control-label">Tutor Name</label>
						<div class="col-md-9">
						<form:select path="tutorId">
							<%
								{
											Class.forName("com.mysql.jdbc.Driver").newInstance();
											Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root",
													"admin");
											String Query = "select * from tutor";
											Statement stm = con.createStatement();
											ResultSet rs = stm.executeQuery(Query);
											while (rs.next()) {
							%>
							<option value="<%=rs.getInt("id")%>"><%=rs.getString("firstName")%>
							</option>
							<%
								}
										}
							%>
						</form:select>
						</div>
						</div>
						<div class="form-group">
							<label for="dateString" class="col-md-3 control-label">Date and Time(dd/MM/yyyy HH:mm)</label>
							<div class="col-md-9">
								<form:input path="dateString" cssClass="form-control" />
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