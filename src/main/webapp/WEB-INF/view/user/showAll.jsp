<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	<c:forEach items = "${list}" var="u">
		<tr>
			<td> ${u.id } </td>
			<td> ${u.username}</td>
		</tr>
	</c:forEach>
	</table>

</body>
</html>