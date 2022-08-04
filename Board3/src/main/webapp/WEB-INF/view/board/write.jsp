<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h2> 글을 작성해주세요.</h2>

    <form method="post" action="/board/write" enctype="multipart/form-data">
        제목 <input type="text" name = "title">
        <br>
        내용 <input type="text" name = "content">
        <br>
        사진 <input type="file" name="image" multiple="multiple">
        <br>
        <button type = "submit"> 글 작성하기 </button>
    </form>
</body>
</html>
