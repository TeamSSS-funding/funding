<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
    <link href="/dist/tailwind.css" rel="stylesheet">
    <title>배송지 목록</title>
    <style>
        /*table,th,tr,td {*/
        /*    border-collapse: collapse;*/
        /*    border: rgb(248,240,150) 2px solid;*/
        /*    text-align: center;*/
        /*}*/
        a {
            text-decoration: none;
        }
    </style>
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
<div class="mt-10">
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
<section class="text-gray-600 body-font">
    <div class="container px-5 py-24 mx-auto mt-10">
        <div class="flex flex-col w-full text-left mb-10">
            <sec:authentication property="principal" var="user"></sec:authentication>
            <h1 class="sm:text-4xl text-3xl font-medium title-font mb-2 text-gray-900 text-left">배송지 목록</h1>
            <p class="text-sm">자주 사용하시는 배송지는 주소록에 등록 해 두시면 편리하게 이용하실 수 있습니다.</p>
        </div>
        <div class="w-full mx-auto overflow-auto">
            <div class="flex justify-end text-lg font-semibold tracking-wide mb-3 mr-3 text-gray-700 underline">
                <a href="/profile/addresses/new" class="hover:text-yellow-600">+ 배송지 추가하기</a>
            </div>
            <table class="table-auto w-full text-left whitespace-nomal border-collapse border border-yellow-200 text-center">
                <thead>
                <tr>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-base bg-yellow-200 rounded-tl rounded-bl">수령인</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-base bg-yellow-200">주소</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-base bg-yellow-200">연락처</th>
                    <th colspan="2" class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-base bg-yellow-200">관리</th>
<%--                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-yellow-300">삭제</th>--%>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="list" items="${addressList}">
                    <tr class="border border-yellow-200">
                        <td>${list.name}</td>
                        <td>${list.postcode}<br>${list.road}${list.jibun}${list.detail}${list.chamgo}</td>
                        <td>${list.phone}</td>
<%--                        <td><a href="/profile/addresses/${list.id}">수정</a></td>--%>
                        <td><button type="button" onclick="location.href='/profile/addresses/${list.id}'"
                                    class="text-black bg-yellow-200 border-0 mx-2 py-2 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base">수정</button></td>
                        <td>
                            <form action="/profile/addresses/${list.id}/delete" method="post">
<%--                                <input type="submit" value="삭제">--%>
                                <button class="text-black bg-yellow-200 border-0 mt-4 mx-2 py-2 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>
    <footer class="my-20">
        <div class="text-center">
<%--            <p class="mb-2">My Good Supporter</p>--%>
            <p class="text-xs mb-1">https://github.com/mygoodsupporter/mygoodsupporter</p>
            <p class="text-xs">COPYRIGHT © MYGOODSUPPORTER ALL RIGHTS RESERVED.</p>
        </div>
    </footer>
</div>
</body>
</html>
