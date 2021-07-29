<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<form:form action="${pageContext.request.contextPath}/join" method="post" modelAttribute="registrationForm">
<section class="text-gray-600 body-font">
	<div class="container px-5 py-24 mx-auto flex flex-wrap items-center">
		<div class="lg:w-2/6 md:w-1/2 bg-gray-100 rounded-lg p-8 flex flex-col mx-auto w-full mt-10 md:mt-0">
			<h2 class="text-gray-900 text-lg font-medium title-font mb-5">회원가입</h2>
			<div class="relative mb-4">
				<label for="username" class="leading-7 text-sm text-gray-600">아이디</label>
<%--				<form:input path="id" type="text" id="m_id" name="id" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">--%>
				<form:input path="username" id="username"
							cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
							cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
				<span><form:errors path="username" /></span>
				<span id = idcheck></span><br>


			</div>
			<div class="relative mb-4">
				<label for="password" class="leading-7 text-sm text-gray-600">비밀번호</label>
<%--				<input type="password" id="password" name="password" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">--%>
				<form:input path="password" id="password"
							cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
							cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
				<span><form:errors path="password" /></span>
			</div>
			<div class="relative mb-4">
				<label for="pwcheck" class="leading-7 text-sm text-gray-600">비밀번호 체크</label>
				<input type="password" id="pwcheck" name="pwcheck" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
				<span id = passwordresult></span>
			</div>
			<div class="relative mb-4">
				<label for="name" class="leading-7 text-sm text-gray-600">이름</label>
<%--				<input type="text" id="name" name="name"  class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">--%>
				<form:input path="name" id="name"
							cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
							cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
				<span><form:errors path="name" /></span>
			</div>
			<div class="relative mb-4">
				<label for="email" class="leading-7 text-sm text-gray-600">이메일</label>
<%--				<input type="text" name="email"  class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">--%>
				<form:input path="email" id="email"
							cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
							cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
				<span><form:errors path="email" /></span>
			</div>
			<div class="relative mb-4">
				<label for="phone" class="leading-7 text-sm text-gray-600">전화번호</label>
<%--				<input type="text" id="m_phone" name="phone"  class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">--%>
				<form:input path="phone" id="phone"
							cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
							cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
				<span><form:errors path="phone" /></span>
			</div>
			<button class="text-black bg-yellow-300 border-0 py-2 px-8 focus:outline-none hover:bg-yellow-400 rounded text-lg">회원가입</button>

		</div>
	</div>
</section>
</form:form>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</body>
</html>