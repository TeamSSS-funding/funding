<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<sec:authentication property="userDetails" var="user"/>

<p>${user.id}</p>
<p>${user.password}</p>
<p>${user.name}</p>
<p>${user.email}</p>
<p>${user.phone}</p>
</body>
</html>