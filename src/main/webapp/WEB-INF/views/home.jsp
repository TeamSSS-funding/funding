<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="memberJoinPage">회원가입</a><br>
<a href="login">로그인</a><br>
<a href="projectRequestPage">프로젝트 신청</a><br>


<sec:authorize access="isAuthenticated()">
	<form method="post" action = "/logout">
		<input type="submit" value="logout">
	</form>
</sec:authorize>


<div>
	<div>
		<sec:authorize access="isAnonymous()">
			anonymous
		</sec:authorize>
	</div>
	<div>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.username" />
			<br>
			<sec:authentication property="principal.email"/>
			authenticated
		</sec:authorize>
	</div>
	<div>
		<sec:authorize access="hasRole('ROLE_USER')">
			<sec:authentication property="principal.username" />
			<sec:authentication property="principal.authorities" />
			This content will only be visible to users who have the "ROLE_USER" authority in their list of <tt>GrantedAuthority</tt>s.
		</sec:authorize>
	</div>
	<div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<sec:authentication property="principal.username"/>
			ADMIN
		</sec:authorize></div>
	<div>
		<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
			<sec:authentication property="principal.username" />
			user admin
		</sec:authorize>
	</div>






</div>
</body>
</html>
