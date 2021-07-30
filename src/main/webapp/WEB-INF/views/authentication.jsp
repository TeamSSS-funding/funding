<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>mygoodsupporter</title>
    <script defer src="dist/main.js"></script><link href="dist/tailwind.css" rel="stylesheet">
</head>
<body>
<header class="text-gray-600 body-font">
    <nav class="container mx-auto flex flex-wrap p-5 justify-between items-center">
        <!-- Logo -->
        <div class="flex items-center title-font font-medium text-gray-900 mb-4 md:mb-0">
            <!-- Hamburger -->
            <button id="navbar-hamburger">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-10 h-10 text-white p-2 bg-purple-500 rounded-full" viewBox="0 0 24 24">
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
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="${pageContext.request.contextPath}/projects">모든 프로젝트</a>
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="projectRequestPage">프로젝트 신청</a>
        </div>
        <!-- CTA -->
        <div class="flex items-center mb-4 md:mb-0">
            <button class="inline-flex justify-center items-center bg-purple-500 border-0 py-1 px-3 focus:outline-none hover:bg-purple-600 rounded text-white text-base"><a href="login">로그인</a>
                <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                    <path d="M5 12h14M12 5l7 7-7 7"></path>
                </svg>
            </button>
            &nbsp;
            <button class="inline-flex items-center bg-purple-500 border-0 py-1 px-3 focus:outline-none hover:bg-purple-600 rounded text-white text-base"><a href="memberJoinPage">회원가입</a>
                <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                    <path d="M5 12h14M12 5l7 7-7 7"></path>
                </svg>
            </button>
        </div>
    </nav>
</header>

<main class="mt-12 lg:mt-32 text-gray-600 body-font">
    <section class="container mx-auto px-6">
        <div class="w-full md:flex md:flex-wrap lg:flex lg:flex-wrap items-center -m-4">
            <sec:authorize access="isAuthenticated()">
                <div class="w-full p-4 text-center">
                    <form method="post" action = "/logout">
                        <input type="submit" value="로그아웃" class="px-4 py-2 text-white bg-purple-500 hover:bg-purple-600 rounded">
                    </form>
                    <p>로그아웃 버튼은 로그인한 사용자만 볼 수 있습니다</p>
                </div>
            </sec:authorize>

            <sec:authorize access="isAnonymous()">
                <div class="w-full p-4 text-center">
                    <p>누구나 볼 수 있습니다</p>
                </div>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <div class="w-full p-4 text-center">
                    <p>로그인한 사용자만 볼 수 있습니다</p>
                </div>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <div class="w-full p-4 text-center">
                    <sec:authentication property="principal.username" />
                    <sec:authentication property="principal.email"/>
                    <p>로그인한 사용자의 이름과 이메일을 볼 수 있습니다</p>
                </div>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <div class="w-full p-4 text-center">
                    <p>principal 객체가 UserDetails의 구현체인 경우 아래와 같이 변수로 스코프에 저장한뒤 사용 할 수 있습니다</p>
                    <sec:authentication property="principal" var="user"></sec:authentication>
                    <p>${user.id}</p>
                    <p>${user.username}</p>
                    <p>${user.email}</p>
                </div>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="w-full p-4 text-center">
                    <sec:authentication property="principal.id" />
                    <sec:authentication property="principal.username" />
                    <sec:authentication property="principal.email"/>
                    <sec:authentication property="principal.authorities"/>
                    <p>"ROLE_USER" 권한을 가진 사용자만 볼 수 있습니다</p>
                </div>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="w-full p-4 text-center">
                    <sec:authentication property="principal.id" />
                    <sec:authentication property="principal.username" />
                    <sec:authentication property="principal.email"/>
                    <sec:authentication property="principal.authorities"/>
                    <p>"ROLE_ADMIN" 권한을 가진 사용자만 볼 수 있습니다</p>
                </div>
            </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                <div class="w-full p-4 text-center">
                <sec:authentication property="principal.id" />
                <sec:authentication property="principal.username" />
                <sec:authentication property="principal.email"/>
                <sec:authentication property="principal.authorities"/>
                <p>"ROLE_USER"이나 "ROLE_ADMIN" 권한을 가진 사용자만 볼 수 있습니다</p>
                </div>
            </sec:authorize>
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
