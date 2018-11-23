<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome To Admin, Please click Send button for Mail Address to add a Flash
		Sale....</h1>

	<form:form action="/admin/publish/mail" modelAttribute="emailBean"
		method="post">
		<table>
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="emailAddress">
						<spring:message text="Email" />
					</form:label></td>
				<td><form:input path="emailAddress" /></td>
			</tr>
			<tr>
				<td><form:label path="mobileNumber">
						<spring:message text="MobileNumber" />
					</form:label></td>
				<td><form:input path="mobileNumber" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Send Mail" name="mail" /></td>
				<td><input type="submit" value="Add Mail" name="mail" /></td>
				<td><input type="reset" value="<spring:message text="Cancel"/>" /></td>

			</tr>
		</table>


	</form:form>

</body>
</html>