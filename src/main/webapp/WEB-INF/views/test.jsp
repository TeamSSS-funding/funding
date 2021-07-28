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

<sec:authentication property="memberDetails" var="member"/>

<p>${member.id}</p>
<p>${member.password}</p>
<p>${member.name}</p>
<p>${member.email}</p>
<p>${member.phone}</p>
</body>
</html>