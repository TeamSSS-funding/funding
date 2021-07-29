<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원목록</title>
    <style>
        table,th,tr,td {
            border-collapse: collapse;
            border: lightblue 2px solid;
            text-align: center;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>전화번호</th>
            <th>이메일</th>
        </tr>
        <c:forEach var="list" items="${memberList}">
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>
            <td>${list.phone}</td>
            <td>${list.email}</td>
        </tr>
        </c:forEach>
    </table>
    <a href="../">홈으로</a>
</body>
</html>
