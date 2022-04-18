<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Show Dorm</title>

	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	
	<script type="text/javascript" src="/js/script.js"></script>
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<h1 class="display-4"><c:out value="${dorm.name}" /> DORM:</h1>
			<div class="container mx-auto">
				<form action="/dorms/${dorm.id}/addstudent" class="p-3 w-50" method="POST">
					<input type="hidden" name="_method" value="PUT" />
					<div class="input-group mb-3">
						<span class="input-group-text">Unhoused Students:</span>
						<select name="student" class="form-select">
							<option selected>Choose student to add...</option>
							<c:forEach var="student" items="${homelessStudents}">
								<option value="${student.id}"><c:out value="${student.firstName}" /> <c:out value="${student.lastName}" /></option>
							</c:forEach>
						</select>				
					</div>
					<input type="submit" value="Add" class="btn btn-sm btn-primary" />
				</form>
			</div>
			<div class="container mx-auto mt-3">
				<h4>Students in the dorm:</h4>
				<table class="table">
					<thead class="table-secondary">
						<tr>
							<th>Name</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dStudent" items="${dorm.students}">
							<tr>
								<td><c:out value="${dStudent.firstName}" /> <c:out value="${dStudent.lastName}" /></td>
								<td><a href="/dorms/${dorm.id}/remove?student=${dStudent.id}" class="text-decoration-none">Remove</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				${dorm.students}
			</div>
		</div>
	</div>

</body>
</html>