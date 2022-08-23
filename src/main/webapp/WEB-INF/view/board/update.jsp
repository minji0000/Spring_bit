<%--
  Created by IntelliJ IDEA.
  User: mingdi
  Date: 2022/07/12
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 수정해즈세여~ </title>
</head>
<body>
    <form method = "post" action = "/board/update/${id}">
        <input type = "text" placeholder = "글 제목" name = "title">
        <input type = "text" placeholder = "글 내용" name = "content">
        <button type="submit"> 글 수정하기 </button>
    </form>
</body>
</html>
