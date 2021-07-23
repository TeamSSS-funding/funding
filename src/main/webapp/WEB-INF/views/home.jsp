<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
<html>
<head>
	<title>마이굿서포터</title>
</head>
<header class="text-gray-600 body-font">
	<div class="container mx-auto flex flex-wrap p-5 flex-col md:flex-row items-center">
		<a class="flex title-font font-medium items-center text-gray-900 mb-4 md:mb-0">
			<svg xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-10 h-10 text-white p-2 bg-yellow-400 rounded-full" viewBox="0 0 24 24">
				<path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"></path>
			</svg>
			<span class="ml-3 text-xl">mygoodsupporter</span>
		</a>
		<nav class="md:ml-auto md:mr-auto flex flex-wrap items-center text-base justify-center">
			<a class="mr-5 hover:text-gray-900">진행중인 프로젝트</a>
			<a class="mr-5 hover:text-gray-900">오픈예정 프로젝트</a>
			<a class="mr-5 hover:text-gray-900" href="${pageContext.request.contextPath}/projects">모든 프로젝트</a>
			<a class="mr-5 hover:text-gray-900" href="projectRequestPage">프로젝트 신청</a>
		</nav>
		<button class="inline-flex items-center bg-yellow-400 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base mt-4 md:mt-0"><a href="login">로그인</a>
			<svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
				<path d="M5 12h14M12 5l7 7-7 7"></path>
			</svg>
		</button>
		&nbsp;
		<button class="inline-flex items-center bg-yellow-400 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base mt-4 md:mt-0"><a href="memberJoinPage">회원가입</a>
			<svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
				<path d="M5 12h14M12 5l7 7-7 7"></path>
			</svg>
		</button>
	</div>
</header>
<section class="text-gray-600 body-font">
	<div class="container px-5 py-24 mx-auto">
		<div class="flex flex-wrap -m-4">
			<div class="lg:w-1/4 md:w-1/2 p-4 w-full">
				<a class="block relative h-48 rounded overflow-hidden">
					<img alt="ecommerce" class="object-cover object-center w-full h-full block" src="https://dummyimage.com/420x260">
				</a>
				<div class="mt-4">
					<h3 class="text-gray-500 text-xs tracking-widest title-font mb-1">CATEGORY</h3>
					<h2 class="text-gray-900 title-font text-lg font-medium">The Catalyzer</h2>
					<p class="mt-1">$16.00</p>
				</div>
			</div>
			<div class="lg:w-1/4 md:w-1/2 p-4 w-full">
				<a class="block relative h-48 rounded overflow-hidden">
					<img alt="ecommerce" class="object-cover object-center w-full h-full block" src="https://dummyimage.com/421x261">
				</a>
				<div class="mt-4">
					<h3 class="text-gray-500 text-xs tracking-widest title-font mb-1">CATEGORY</h3>
					<h2 class="text-gray-900 title-font text-lg font-medium">Shooting Stars</h2>
					<p class="mt-1">$21.15</p>
				</div>
			</div>
			<div class="lg:w-1/4 md:w-1/2 p-4 w-full">
				<a class="block relative h-48 rounded overflow-hidden">
					<img alt="ecommerce" class="object-cover object-center w-full h-full block" src="https://dummyimage.com/422x262">
				</a>
				<div class="mt-4">
					<h3 class="text-gray-500 text-xs tracking-widest title-font mb-1">CATEGORY</h3>
					<h2 class="text-gray-900 title-font text-lg font-medium">Neptune</h2>
					<p class="mt-1">$12.00</p>
				</div>
			</div>
			<div class="lg:w-1/4 md:w-1/2 p-4 w-full">
				<a class="block relative h-48 rounded overflow-hidden">
					<img alt="ecommerce" class="object-cover object-center w-full h-full block" src="https://dummyimage.com/423x263">
				</a>
				<div class="mt-4">
					<h3 class="text-gray-500 text-xs tracking-widest title-font mb-1">CATEGORY</h3>
					<h2 class="text-gray-900 title-font text-lg font-medium">The 400 Blows</h2>
					<p class="mt-1">$18.40</p>
				</div>
			</div>
			<div class="lg:w-1/4 md:w-1/2 p-4 w-full">
				<a class="block relative h-48 rounded overflow-hidden">
					<img alt="ecommerce" class="object-cover object-center w-full h-full block" src="https://dummyimage.com/424x264">
				</a>
				<div class="mt-4">
					<h3 class="text-gray-500 text-xs tracking-widest title-font mb-1">CATEGORY</h3>
					<h2 class="text-gray-900 title-font text-lg font-medium">The Catalyzer</h2>
					<p class="mt-1">$16.00</p>
				</div>
			</div>
			<div class="lg:w-1/4 md:w-1/2 p-4 w-full">
				<a class="block relative h-48 rounded overflow-hidden">
					<img alt="ecommerce" class="object-cover object-center w-full h-full block" src="https://dummyimage.com/425x265">
				</a>
				<div class="mt-4">
					<h3 class="text-gray-500 text-xs tracking-widest title-font mb-1">CATEGORY</h3>
					<h2 class="text-gray-900 title-font text-lg font-medium">Shooting Stars</h2>
					<p class="mt-1">$21.15</p>
				</div>
			</div>
			<div class="lg:w-1/4 md:w-1/2 p-4 w-full">
				<a class="block relative h-48 rounded overflow-hidden">
					<img alt="ecommerce" class="object-cover object-center w-full h-full block" src="https://dummyimage.com/427x267">
				</a>
				<div class="mt-4">
					<h3 class="text-gray-500 text-xs tracking-widest title-font mb-1">CATEGORY</h3>
					<h2 class="text-gray-900 title-font text-lg font-medium">Neptune</h2>
					<p class="mt-1">$12.00</p>
				</div>
			</div>
			<div class="lg:w-1/4 md:w-1/2 p-4 w-full">
				<a class="block relative h-48 rounded overflow-hidden">
					<img alt="ecommerce" class="object-cover object-center w-full h-full block" src="https://dummyimage.com/428x268">
				</a>
				<div class="mt-4">
					<h3 class="text-gray-500 text-xs tracking-widest title-font mb-1">CATEGORY</h3>
					<h2 class="text-gray-900 title-font text-lg font-medium">The 400 Blows</h2>
					<p class="mt-1">$18.40</p>
				</div>
			</div>
		</div>
	</div>
</section>
<body>

<a href="memberJoinPage">회원가입</a><br>
<a href="login">로그인</a><br>
<a href="projectRequestPage">프로젝트 신청</a><br>
<a href="${pageContext.request.contextPath}/projects">프로젝트 보기</a><br>
<a href="${pageContext.request.contextPath}/proposals/new">프로젝트 제안서 보기</a><br>
<a href="${pageContext.request.contextPath}/proposals/new">프로젝트 신청 하기</a><br>

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
			<sec:authentication property="principal.authorities" />
		</sec:authorize>
	</div>


</div>
</body>
</html>
