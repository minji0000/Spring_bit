<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>글 번호</th>
					<th>글 제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>수정일</th>
				</tr>
			</thead>

			
			<c:forEach items="${list }" var="b">

				<tr onclick= "location.href ='/board/selectOne/${b.id}'">
					<td>${b.id}</td>
					<td>${b.title}</td>
					<td>${nicknameMap[b.writerId]}</td>
					<td><fmt:formatDate value="${b.writtenDate.time }" pattern= "20yy년 MM월 dd일 HH:mm:ss" /></td>
					<td><fmt:formatDate value="${b.updatedDate.time }" pattern= "20yy년 MM월 dd일 HH:mm:ss" /></td>
				</tr>

			</c:forEach>

		</table>
		<div class = "row">
			<div class = "col">

				<div class="btn btn-primary" style="background-color: #FFC0CB; border-color: #FFC0CB;"
					onclick="location.href ='/board/write'">글 작성하기</div>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col">
				<a href='/board/showAll/1'> [<<] </a>
				
				<c:choose>
					
					<c:when test="${currentPage < 3}">
						<c:forEach begin="1" end="5" var="pageNo">
							<c:choose>
								<c:when test="${pageNo ne currentPage }">
									<a href="/board/showAll/${pageNo }">${pageNo }</a>
								</c:when>
							
								<c:otherwise>
									<b> ${pageNo }</b>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					
					<c:when
						test="${currentPage >= 3 and currentPage < lastPageNo - 2 }">
						<c:forEach begin="${currentPage - 2}" end="${currentPage + 2 }"
							var="pageNo">
							<c:choose>
								<c:when test="${pageNo ne currentPage }">
									<a href="/board/showAll/${pageNo }">${pageNo }</a>
								</c:when>
								<c:otherwise>
									<b> ${pageNo }</b>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					
					<c:otherwise>
						<c:forEach begin="${lastPageNo - 4}" end="${lastPageNo}"
							var="pageNo">
							<c:choose>
								<c:when test="${pageNo ne currentPage }">
									<a href="/board/showAll/${pageNo }">${pageNo }</a>
								</c:when>
								<c:otherwise>
									<b> ${pageNo }</b>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<a href='/board/showAll/${lastPageNo }'> [>>] </a>
			</div>
		</div>
	</div>
</body>
</html>