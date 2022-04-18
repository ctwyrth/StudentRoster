<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Dorm</title>

	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	
	<script type="text/javascript" src="/js/script.js"></script>
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<h1 class="display-5">Dormitories:</h1>
			<form:form action="/dorms/new" modelAttribute="dorm" method="POST" class="p-3 w-50">
				<div class="input-group mb-3">
					<span class="input-group-text">Name:</span>
					<form:input type="text" path="name" class="form-control" />
				</div>
				<div><form:errors path="name" /></div>
				<input type="submit" value="Create" class="btn btn-sm btn-primary" />
			</form:form>
		</div>
	</div>

</body>
</html>