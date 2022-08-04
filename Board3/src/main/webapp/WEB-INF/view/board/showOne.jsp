<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <th> 글 번호: ${b.id } </th>
    </tr>
    <tr>
        <th> 글 제목: ${b.title }  </th>
    </tr>
    <tr>
        <th> 작성일: <fmt:formatDate value="${b.writtenDate.time }"
                                  pattern="yy년 m월 dd일 HH시 mm분 ss초"/></th>
    </tr>
    <tr>
        <th> 작성자: ${nickname } </th>
    </tr>
    <tr>
        <th> 수정일: <fmt:formatDate value="${b.updatedDate.time }"
                                  pattern="yy년 m월 dd일 HH시 mm분 ss초"/></th>
    </tr>
    <tr>
        <th> ${b.content } </th>
    </tr>
    <tr>
        <th>
            <c:if test="${b.imageFileName ne null }">
            <img src="/img/${b.imageFileName}" alt="이미지">
            </c:if>
        </th>
    </tr>
    <tr>
        <td> 조회수: ${b.views} </td>
    </tr>
    <tr>
        <td> 추천수: ${b.good} </td>
    </tr>
</table>

<!--  == eq, ne != 같은 -->
<c:if test="${b.writerId eq logInId }">

    <a href="/board/update/${b.id}"> 수정하기 </a>
    <a href="/board/delete/${b.id}"> 삭제하기 </a>

</c:if>

<br/>
<a href="/board/selectAll"> 목록으로 </a>

<div class="reply">
    <table>
        <thead>
            <tr>
                <th>작성자</th>
                <th>댓글</th>
            </tr>
        </thead>

        <c:forEach items="${replyList}" var="r">
            <tr>
                <td>${nicknameMap[r.writerId]}</td>
                <td>${r.content}</td>
            </tr>
        </c:forEach>

    </table>
</div>

<form method="post" action="/reply/write/${b.id}">
    <input type="text" name="content">
    <button type="submit"> 댓글 작성</button>
</form>


</body>
</html>
