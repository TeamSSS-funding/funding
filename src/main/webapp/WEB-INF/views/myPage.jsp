<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>마이페이지</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
    <link href="/dist/tailwind.css" rel="stylesheet">
</head>
<body>
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
<div class="">
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
            <span class="ml-3 text-xl"><a href="/">mygoodsupporter</a></span>
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
                <button class="inline-flex items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="/profile">Mypage</a>
                    <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                        <path d="M5 12h14M12 5l7 7-7 7"></path>
                    </svg>
                </button>
            </sec:authorize>
        </div>
    </nav>
</header>
<section class="text-gray-600 body-font">
    <div class="container px-5 py-24 mx-auto">
        <div class="flex flex-row w-full text-center mb-16 justify-start items-center">
            <div class="rounded-full h-32 w-32 flex items-center justify-center bg-yellow-200 border-0 border-yellow-300"></div>
            <sec:authentication property="principal" var="user"></sec:authentication>
            <div class="ml-8 text-3xl">${user.username}님 환영합니다.</div>
        </div>
        <div class="w-full mx-auto overflow-auto mt-10 flex flex-wrap md:flex md:flex-wrap border-t-2 border-gray-400">
            <div class="mt-8 pb-7 w-full md:w-1/2 ">
                <a class="pl-5 text-xl" href="profile/cards/new">결제 카드 등록</a>
            </div>
            <div class="mt-8 pb-7 w-full md:w-1/2 ">
                <a class="pl-5 text-xl" href="profile/cards">등록된 결제 정보 조회</a>
            </div>
            <div class="mt-8 pb-7 w-full md:w-1/2 ">
                <a class="pl-5 text-xl" href="payments/selectedReward">결제 테스트</a>
            </div>
            <div class="mt-8 pb-7 w-full md:w-1/2 ">
                <a class="pl-5 text-xl" href="profile/addresses">배송지 관리</a>
            </div>
            <div class="mt-8 pb-7 w-full md:w-1/2 ">
                <a class="pl-5 text-xl" href="/projects/created">내가 만든 프로젝트</a>
            </div>
            <div class="mt-8 pb-7 w-full md:w-1/2 ">
                <a class="pl-5 text-xl" href="">요렇게 저렇게</a>
            </div>
        </div>
    </div>
</section>
    <footer class="mt-20 h-1/6 bg-yellow-100">
        <div class="text-center">
            <%--            <p class="mb-2">My Good Supporter</p>--%>
            <p class="text-xs pt-10 mb-1">https://github.com/mygoodsupporter/mygoodsupporter</p>
            <p class="text-xs">COPYRIGHT © MYGOODSUPPORTER ALL RIGHTS RESERVED.</p>
        </div>
    </footer>
</div>

</body>
</html>
