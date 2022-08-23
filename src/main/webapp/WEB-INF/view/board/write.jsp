<%--
  Created by IntelliJ IDEA.
  User: mingdi
  Date: 2022/07/12
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 글 작성 </title>
</head>
<body>
    <form method = "post" action = "/board/write">
        <input type = "text" placeholder = "글 제목" name = "title">
        <input type = "text" placeholder = "글 내용" name = "content">
        <button type="submit"> 글 작성하기 </button>
    </form>


</body>
</html>
