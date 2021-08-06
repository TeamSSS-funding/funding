<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>회원정보 수정</title>
</head>
<body>
<%--<sec:authentication property="principal" var="id"></sec:authentication>--%>
<a>회원정보 수정</a>
    <form action="updateprocess" method="post">
        아이디 <input type="text" name="id" value="${member.id}" readonly><br>
        비밀번호 <input type="password" name="password"><br>
        이름 <input type="text" name="name" value="${member.name}"><br>
        전화번호 <input type="text" name="phone" value="${member.phone}"><br>
        이메일 <input type="text" name="email" value="${member.email}"><br>
        <input type="submit" value="정보 수정">
    </form>

<a>비밀번호 수정</a>
    <form action="updateprocess" method="post">
        새 비밀번호 <input type="password" name="password"><br>
        비밀번호 확인 <input type="password"><br>
        <input type="submit" value="비밀번호 수정">
    </form>
</body>
</html>
