<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome To User Registration Page</h1>


	<form:form action="/register" modelAttribute="registerBean"
		method="post">
		<table>
			<tr>
				<td><form:hidden path="mailId" value="${mailId}" /></td>
			</tr>
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="address">
						<spring:message text="Address" />
					</form:label></td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td><form:label path="contactNo">
						<spring:message text="ContactNumber" />
					</form:label></td>
				<td><form:input path="contactNo" /></td>
			</tr>
			<tr>
				<td><form:label path="gender">
						<spring:message text="Gender" />
					</form:label></td>
				<td><form:radiobutton path="gender" value="Male" /> <spring:message
						text="Male" /></td>
				<td><form:radiobutton path="gender" value="Female" /> <spring:message
						text="Female" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Register" name="register" /></td>
				<td><input type="reset" value="<spring:message text="Cancel"/>" /></td>

			</tr>
		</table>


	</form:form>

</body>
</html>