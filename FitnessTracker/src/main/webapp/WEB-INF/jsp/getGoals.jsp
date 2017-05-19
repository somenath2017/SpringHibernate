<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Goal Report</title>
</head>
<body>

	<table>
		<tr>
			<th>ID</th><th>MINUTES</th>
		</tr>
		<c:forEach items="${goals}" var="goal">
		<tr>
			<td>${goal.id}</td>
			<td>${goal.minutes}</td>
			
			<td>
				<table>
				
				<tr>
				<th>E ID</th><th>E MINUTES</th><th>E Activity</th>
				</tr>
				
				<c:forEach items="${goal.excercises}" var="exercise">
				<tr>
					<td>${exercise.id}</td><td>${exercise.minutes}</td><td>${exercise.activity}</td>
				</tr>
				</c:forEach>				
				</table>
			</td>
			
		</tr>
		</c:forEach>
	</table>

</body>
</html>