<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="memberJoin" method="post">
		아이디 <input type="text" name="m_id"><br>
		비밀번호 <input type="password" name="m_password"><br>
		비밀번호 확인 <input type="password" name="m_pwcheck"><br>
		이름 <input type="text" name="m_name"><br>
		이메일 <input type="text" name="m_email">@
        <select name="m_email">
            <option selected>선택</option>
            <option value="naver.com">네이버</option>
            <option value="hanmail.net">다음</option>
            <option value="gmail.com">지메일</option>
        </select> <br>
        <input type="submit" value="가입">
	</form>
</body>
</html>