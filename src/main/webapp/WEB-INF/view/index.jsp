<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 해주세요 ! </title>
</head>
<body>
	<h1> 드디어 인텔리제이로 돌아왔따 ! ! </h1>
	
	<form method = "post" action = "user/logIn">
		<input type = "text" placeholder = "아이디를 입력해" name = "username">
		<input type = "password" placeholder = "비밀번호도 입력해" name= "password">
		<button style = "background-color: #FFC0CB" > 로그인</button> 
	</form>
	<button onclick="location.href='/board/showAll/1'">여기로왕</button>

	<h1> 혹시 회원가입 아직 안 한 사람? </h1>
	<button style = "background-color: #FFC0CB" onclick="location.href ='/user/register'"> 여기로 </button>

	<!-- <img src = "/img/ss.jpg"/>
	<form action="board/upload" method="post" enctype="multipart/form-data">
		<input type = "file" multiple = "multiple" name = "image">
		<button type = "submit">파일 업로드</button>
	</form>
	-->

</body>
</html>