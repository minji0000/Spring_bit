<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h2> hi 난 카리 </h2>
    <form method="post" action="/user/logIn">
        아이디 <input type ="text" name ="username">
        <br><br>
        비밀번호 <input type ="password" name ="password">
        <br><br>
        <button type="submit">로그인</button>
    </form>

    <button onclick="location.href ='/user/register'">회원가입</button>
</center>
</body>
</html>
