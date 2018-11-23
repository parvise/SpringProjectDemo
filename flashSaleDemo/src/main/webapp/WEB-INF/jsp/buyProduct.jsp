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
	<h1>Welcome To ${user}, Please click Buy button for Purchase a
		Order. Flash Sale is Available for 1hr Only</h1>
	<h2>${message}</h2>
	<h2>Total Stock Available : ${totalStock}</h2>
	<h2>Only ${stockAvailable} Products Left :</h2>
	<!-- /soldProduct -->
	<form:form action="/soldProduct" method="Post"
		modelAttribute="regEntity">
		<table>
			<tr>
				<td><form:hidden path="mailId" value="${mailId}" /></td>
			</tr>
			<tr>
				<td><form:hidden path="regId" value="${regId}" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Buy Product" name="mail" ${buyBtnDisable} /></td>
				<%-- 				//disabled=${buyBtnDisable} --%>
			</tr>
		</table>
	</form:form>
</body>
</html>