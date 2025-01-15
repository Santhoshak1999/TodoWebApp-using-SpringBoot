<html>
<head>
<title>Welcome</title>
<link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet"></link>
</head>
<body>
	<div class="container">
		<%@ include file="common/navbar.jspf"%>
		<h2>Welcome ${name}</h2>
		<div>
			<a href="/list-todos">Manage</a> Your Todos
		</div>
	</div>
	<%@ include file="common/footer.jspf"%>
</body>

<script src="/webjars/jquery/3.6.0/jquery.js"></script>
<script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>




</html>