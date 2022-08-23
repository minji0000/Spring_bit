<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main class="form-signin w-100 m-auto">
		<form method = "post" action = "/user/register">
			<div class= "mb-6">
				아이디 <input type ="text" name="username" class="form-control">
			</div>
			<div class= "mb-6">
				비밀번호 <input type ="password" name="password"  class="form-control">
			</div>
			<div class= "mb-6">	
				닉네임 <input type ="text" name="nickname"  class="form-control">
			</div>
			<div class= "mb-12 row justity-content-center">
				<button type = "submit" class="btn btn-outline">회원가입</button>
			</div>
		</form>
	</main>




</body>
</html>