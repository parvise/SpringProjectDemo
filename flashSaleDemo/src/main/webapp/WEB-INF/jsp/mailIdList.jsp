<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Mail Added Success</h1>
	<h2>${sucess}</h2>
	<c:if test="${!empty list}">
		<table class="tg" border=1>
			<tr>
				<th width="120">Name</th>
				<th width="120">MailAddress</th>
				<th width="120">Contact</th>
				<th width="120">Status</th>
			</tr>
			<c:forEach items="${list}" var="mail">
				<tr>
					<td>${mail.name}</td>
					<td>${mail.emailAddress}</td>
					<td>${mail.mobileNumber}</td>
					<td>${mail.registerdStatus}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<a href="<c:url value='/home' />">AddEmail</a> ||
	<a href="<c:url value='/publishMail' />">Send Invitation</a>

</body>
</html>