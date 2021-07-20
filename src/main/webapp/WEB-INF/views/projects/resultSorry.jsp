<%--
  Created by IntelliJ IDEA.
  User: hsh
  Date: 2021-07-16
  Time: 오후 2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>거절 페이지</title>
</head>
<body>
<form action="resultSorry">
    거절 사유 : <select name="s_reason">
    <option value="mistake">오탈자</option>
    <option value="similar">아이디어 중복</option>
    <option value="unfitness">부적격한 프로젝트</option>
</select><br>
거절 사유 상세 내용 : <textarea name="s_reasonDetail" cols="30" rows="10"></textarea><br>
    <input type="submit" value="제출하기">
</form>
</body>
</html>
