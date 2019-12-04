<jsp:useBean id="user" class="nure.cs.vodotyka.usermanagment.User" scope="session"></jsp:useBean>
<html>
	<head>
		<title>User management: details</title>
	</head>
	<body>
		<form action="<%=request.getContextPath() %>>/browse" method="get">
			<input type="hidden" name="id" value="${user.id} }">
			First name ${user.firstName}<br>
			Last name ${user.lastName}<br>
			Date of birth <fmt:formatDate value="${user.dateOfBirth}" type="date" dateStyle="medium"/>
			Age ${user.age }<br>
			
			<input type="submit" name="cancelButton" value="Cancel">
		</form>
		<c:if test="${requestScope.error != null}">
		<script>
			alert('${requestScope.error}');
		</script>
		</c:if>
	</body>
</html>