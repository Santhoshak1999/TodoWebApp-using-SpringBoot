<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>todolists</title>
<link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet"></link>
<style>
</style>
</head>
<body>
	<div class="container">
		<%@ include file="common/navbar.jspf"%>
		<h1>Your Todos</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is Done</th>
					<th>
					<th>
					<th>
					<th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos }" var="todo">
					<tr>
						<td>${todo.id}</td>
						<td>${todo.name}</td>
						<td>${todo.description}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a href="delete-todo?id=${todo.id}"
							class="btn btn-danger">Delete</a></td>
						<td><a href="update-todo?id=${todo.id}"
							class="btn btn-primary">Update</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="add-todo" class="btn btn-success">Add Todos</a>
		<%@ include file="common/footer.jspf"%>

		<script src="/webjars/jquery/3.6.0/jquery.js"></script>
		<script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	</div>
</body>
</html>