<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>배송지 목록</title>
</head>
<style>
    table,th,tr,td {
        border-collapse: collapse;
        border: lightblue 2px solid;
        text-align: center;
    }
    a { text-decoration: none; }
</style>
<body>
<table>
    <tr>
        <th>수령인</th>
        <th>주소</th>
        <th>연락처</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>
    <c:forEach var="list" items="${addressList}">
        <tr>
            <td>${list.name}</td>
            <td>${list.postcode}<br>${list.road}${list.jibun}${list.detail}${list.chamgo}</td>
            <td>${list.phone}</td>
            <td><a href="/users/deliveryList/edit/${list.number}">수정</a></td>
            <td><a href="/users/deliveryList/delete/${list.number}">삭제</a></td>
        </tr>
    </c:forEach>
</table>
    <a href="/users/deliveryWritePage">배송지 추가</a>
</body>
</html>
