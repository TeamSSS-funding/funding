<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>로그인</title>
	<style>
		.center {
			display: flex;
			justify-content: center;
		}
	</style>
</head>

<body>
<header class="text-gray-600 body-font">
	<div class="container mx-auto flex flex-wrap p-5 flex-col md:flex-row items-center">
		<a class="flex title-font font-medium items-center text-gray-900 mb-4 md:mb-0">
			<svg xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-10 h-10 text-white p-2 bg-yellow-400 rounded-full" viewBox="0 0 24 24">
				<path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"></path>
			</svg>
			<span class="ml-3 text-xl"><a href="/"> mygoodsupporter</a></span>
		</a>
		<nav class="md:ml-auto md:mr-auto flex flex-wrap items-center text-base justify-center">
			<a class="mr-5 hover:text-gray-900">진행중인 프로젝트</a>
			<a class="mr-5 hover:text-gray-900">오픈예정 프로젝트</a>
			<a class="mr-5 hover:text-gray-900">모든 프로젝트</a>
			<a class="mr-5 hover:text-gray-900">프로젝트 올리기</a>
		</nav>
		<button class="inline-flex items-center bg-gray-100 border-0 py-1 px-3 focus:outline-none hover:bg-gray-200 rounded text-base mt-4 md:mt-0">Login
			<svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
				<path d="M5 12h14M12 5l7 7-7 7"></path>
			</svg>
		</button>
	</div>
</header>

<form action="${pageContext.request.contextPath}/login" method="post">
<section class="text-gray-600 body-font">
	<div class="container px-5 py-24 mx-auto flex flex-wrap items-center">
<%--		<div class="lg:w-3/5 md:w-1/2 md:pr-16 lg:pr-0 pr-0">--%>
<%--			<h1 class="title-font font-medium text-3xl text-gray-900">Slow-carb next level shoindcgoitch ethical authentic, poko scenester</h1>--%>
<%--			<p class="leading-relaxed mt-4">Poke slow-carb mixtape knausgaard, typewriter street art gentrify hammock starladder roathse. Craies vegan tousled etsy austin.</p>--%>
<%--		</div>--%>
		<div class="lg:w-2/6 md:w-1/2 bg-gray-100 rounded-lg p-8 flex flex-col mx-auto w-full mt-10 md:mt-0">
			<h2 class="text-gray-900 text-lg font-medium title-font mb-5">로그인</h2>
			<div class="relative mb-4">
				<label for="full-name" class="leading-7 text-sm text-gray-600">아이디</label>
				<input type="text" id="full-name" name="m_id" class="w-full bg-white rounded border border-gray-300 focus:border-blue-500 focus:ring-2 focus:ring-blue-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
			</div>
			<div class="relative mb-4">
				<label for="email" class="leading-7 text-sm text-gray-600">비밀번호</label>
				<input type="password" id="email" name="m_password" class="w-full bg-white rounded border border-gray-300 focus:border-blue-500 focus:ring-2 focus:ring-blue-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
			</div>
			<button class="text-white bg-yellow-400 border-0 py-2 px-8 focus:outline-none hover:bg-yellow-300 rounded text-lg">로그인</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			&nbsp;
			<div class="center">
				<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=631b84f94a27727f5c58546dfe62d1d3&redirect_uri=http://localhost:8080/oauth_kakao&response_type=code&scope=account_email">
					<img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="222">
				</a>
			</div>
		</div>


	</div>



</section>
<%--</form>--%>
<%--	<form action="${pageContext.request.contextPath}/login" method="post">--%>
<%--		아이디: <input type="text" name="m_id"><br>--%>
<%--		비밀번호: <input type="password" name="m_password"><br>--%>
<%--		<input type="submit" value="로그인">--%>
<%--		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--		<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>--%>
<%--		<a href="https://kauth.kakao.com/oauth/authorize?client_id=631b84f94a27727f5c58546dfe62d1d3&amp;redirect_uri=http://localhost:8081/oauth_kakao&response_type=code">--%>
<%--			<img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="222">--%>
<%--		</a>--%>
<%--	</form>--%>
</body>
</html>