<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<html>
<head>
<title>Person Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>

<script>
	angular
			.module('myApp', [])
			.controller(
					'myCtrl',
					[
							'$scope',
							'$http',
							'$window',
							function($scope, $http, $window) {
								$scope.myFunc = function() {
									var language = $scope.myValue;
									var text = "http://localhost:9090/SpringFileUpload/changeLanguage?siteLanguage="
											+ language;
									$scope.myValue = language;
									$http({
										method : 'GET',
										url : text
									})
											.then(
													function successCallback(
															response) {
														//$scope.employees = response.data;
														$window.location.href = text;
														$scope.myValue = language;
													},
													function errorCallback(
															response) {
														console
																.log("---> Error "
																		+ response.statusText);
													});
								};
							} ]);
</script>

</head>
<body ng-app="myApp" ng-controller="myCtrl">
	<h1>Add Message ${message}</h1>
	<%-- <br>
	<h6>Add Action ${addAction}</h6>
	<h6>Add EditDetails ${EditDetails}</h6>
	<h6>Add commandName ${commandName}</h6>  --%>

	<c:url var="addAction" value="${addAction}"></c:url>
	<c:url var="commandName" value="${commandName}"></c:url>

	<a href="<c:url value='/person/student' />">Student</a> ||
	<a href="<c:url value='/person/employee' />">Employee</a> ||
	<select name="language" ng-change="myFunc()" ng-model="myValue"><option
			value="">Change Language</option>
		<c:if test="${!empty languages}">
			<%--<form:select path="language" items="${languages }"></form:select>--%>
			<c:forEach var="language" items="${languages}">
				<option value="${language.getCode()}">${language.name()}</option>
			</c:forEach>
		</c:if>

	</select>


	<form:form action="${addAction}" commandName="${commandName}"
		enctype="multipart/form-data">
		<c:if test="${!empty EditDetails}">
			<table>
				<tr>
					<td><img src="/SpringFileUpload/images/${EditDetails.imgUrl}"
						alt="${EditDetails.imgUrl}" width="100px" height="100px" /></td>
				</tr>
			</table>
		</c:if>

		<table>
			<c:if test="${!empty EditDetails}">
				<tr>
					<td><form:label path="id">
							<spring:message text="ID" />
						</form:label></td>
					<td><form:input path="id" readonly="true" size="8"
							disabled="true" value="${EditDetails.id}" /> <form:hidden
							path="id" value="${EditDetails.id}" /></td>
				</tr>
			</c:if>
			<form:hidden path="fileId" value="${EditDetails.fileId}" />
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" value="${EditDetails.name}" /></td>
			</tr>
			<tr>
				<td><form:label path="file">
						<spring:message text="File" />
					</form:label></td>
				<td><form:input type="file" path="file" id="file" /></td>
			</tr>
			<tr>
				<td><form:label path="description">
						<spring:message text="Description" />
					</form:label></td>
				<td><form:input path="description"
						value="${EditDetails.description}" /></td>
			</tr>
			<tr>
				<td><form:label path="country">
						<spring:message text="Country" />
					</form:label></td>
				<td><form:input path="country" value="${EditDetails.country}" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty EditDetails.name}">
						<input type="submit"
							value="<spring:message text="Edit ${message}"/>" name="btnValue" />
						<input type="submit" value="<spring:message text="Back"/>"
							name="btnValue" />
					</c:if> <c:if test="${empty EditDetails.name}">
						<input type="submit"
							value="<spring:message text="Add ${message}"/>" name="btnValue" />
					</c:if></td>
				<td><input type="reset" value="<spring:message text="Cancel"/>" /></td>
				<td><a href="<c:url value='/${message}/generateXL' />">Generate
						XLS</a></td>
			</tr>
		</table>
	</form:form>
	<br>
	<h3>${message}List</h3>
	<c:if test="${!empty listPersons}">
		<table class="tg">
			<tr>
				<th width="90">${message}ID</th>
				<th width="120">${message}Name</th>
				<th width="120">${message}Country</th>
				<th width="120">${message}Photo</th>
				<th width="100">Edit / Delete</th>
			</tr>
			<c:forEach items="${listPersons}" var="person">
				<tr>
					<td>${person.id}</td>
					<td>${person.name}</td>
					<td>${person.country}</td>
					<td>${person.fileName}</td>
					<td><a href="<c:url value='/edit/${message}/${person.id}' />">Edit</a>
						/ <a href="<c:url value='/remove/${message}/${person.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>
