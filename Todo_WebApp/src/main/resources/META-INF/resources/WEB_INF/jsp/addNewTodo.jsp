<!-- JST taglib Directive -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>addTodo</title>
<link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="/webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		
		<!-- JSP include Directive -->
		<%@ include file="common/header.jspf" %>
		<!--(JSP include Directive) Include Navigation-Bar file here -->
		<%@ include file="common/navbar.jspf" %>
		
		<h1>Enter Todo Details</h1>
		<form:form method="post" modelAttribute="todo">
			<fieldset class="mb-3">
				<label>Description: </label>
				<form:input type="text" path="description" required="required" />
				<form:errors path="description" cssClass="text-warning"></form:errors>
			</fieldset>
			<fieldset class="mb-3">
				<label>Target Date: </label>
				<form:input type="date" path="targetDate" required="required" />
				<form:errors path="targetDate" cssClass="text-warning"></form:errors>
			</fieldset>
			<fieldset class="mb-3">
				<label>IsDone: </label>
				<form:input path="done" required="required" />
				<form:errors path="done" cssClass="text-warning"></form:errors>
			</fieldset>
			
			<form:input type="hidden" path="id"/>
		<!-- 	<form:input type="hidden" path="done"/>-->
			
			
			<input type="submit" class="btn btn-success" />
		</form:form>
	</div>
	<%@ include file="common/footer.jspf" %>
	<script src="/webjars/jquery/3.6.0/jquery.js"></script>
	<script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>
