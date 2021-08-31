<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>mygoodsupporter</title>
	<script defer src="main.js"></script>
	<link href="/dist/tailwind.css" rel="stylesheet">
</head>
<body>
<header class="text-gray-600 body-font">
	<nav class="container mx-auto flex flex-wrap p-5 justify-between items-center">
		<!-- Logo -->
		<div class="flex items-center title-font font-medium text-gray-900 mb-4 md:mb-0">
			<!-- Hamburger -->
			<button id="navbar-hamburger">
				<svg xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-10 h-10 text-white p-2 bg-amber-200 rounded-full" viewBox="0 0 24 24">
					<path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"></path>
				</svg>
			</button>
			<span class="ml-3 text-xl">mygoodsupporter</span>
		</div>
		<!-- Menu -->
		<div class="hidden w-full order-last lg:flex lg:w-auto lg:order-none text-base"
			 id="navbar-collapse-menu" >
			<a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900">진행중인 프로젝트</a>
			<a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900">오픈예정 프로젝트</a>
			<a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="${pageContext.request.contextPath}/projects/projectList">모든 프로젝트</a>
			<a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="${pageContext.request.contextPath}/projects/new">프로젝트 신청</a>
		</div>
		<!-- CTA -->
		<div class="flex items-center mb-4 md:mb-0">
			<sec:authorize access="isAnonymous()">
			<button class="inline-flex justify-center items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="login">로그인</a>
				<svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
					<path d="M5 12h14M12 5l7 7-7 7"></path>
				</svg>
			</button>
			&nbsp;
			<button class="inline-flex items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="joinPage">회원가입</a>
				<svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
					<path d="M5 12h14M12 5l7 7-7 7"></path>
				</svg>
			</button>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<button class="inline-flex justify-center items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="logout">로그아웃</a>
					<svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
						<path d="M5 12h14M12 5l7 7-7 7"></path>
					</svg>
				</button>
				&nbsp;
				<button class="inline-flex items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="profile">Mypage</a>
					<svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
						<path d="M5 12h14M12 5l7 7-7 7"></path>
					</svg>
				</button>
			</sec:authorize>
		</div>
	</nav>
</header>

<main class="mt-12 lg:mt-32 text-gray-600 body-font">
	<section class="container mx-auto px-6">
		<div class="w-full md:flex md:flex-wrap lg:flex lg:flex-wrap items-center -m-4">
			<form action="${pageContext.request.contextPath}/checkouts/new" method="post">
				<c:forEach var="reward" items="${rewards}">
					<div>
						<input type="radio" value="${reward.id}" name="rewardId">${reward.amount}원
						<h1>${reward.title}</h1>

						<h2>${reward.description}</h2>
						<div>
							<c:forEach var="rewardItem" items="${reward.items}">
								<p>${rewardItem.item.title}</p>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
				<button type="submit" class="w-full text-black bg-yellow-300 border-0 py-2 px-8 focus:outline-none hover:bg-yellow-400 rounded text-lg">
					결제하기
				</button>
			</form>
		</div>
	</section>
</main>
<script>
	document.addEventListener('DOMContentLoaded', () => {
		let hamburger = document.querySelector('#navbar-hamburger')

		hamburger.addEventListener('click', () => {
			let menu = document.querySelector('#navbar-collapse-menu')
			menu.classList.toggle("hidden")
			menu.classList.toggle("block")
		})
	})
</script>


</body>
</html>
