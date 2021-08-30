<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<title>모든 프로젝트 목록</title>
<script defer src="main.js"></script>
<link href="/dist/tailwind.css" rel="stylesheet">
<script>

    function deletefn(id) {
        location.href="/projects/projectList/delete/" +id;
    }

</script>

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
<section class="text-gray-600 body-font">
    <div class="container px-5 py-24 mx-auto">
        <div class="flex flex-col text-center w-full mb-20">
            <h1 class="sm:text-4xl text-3xl font-medium title-font mb-2 text-gray-900">모든 프로젝트</h1>

        </div>
        <div class="lg:w-2/3 w-full mx-auto overflow-auto">
            <table class="table-auto w-full text-left whitespace-no-wrap">
                <thead>
                <tr>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100 rounded-tl rounded-bl">프로젝트 번호</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">프로젝트 제목</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">프로젝트 부제목</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">목표 금액</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">시작 날짜</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">종료 날짜</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">대표 이미지</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">프로젝트 상태</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">수정</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">2단계 정보수정</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">삭제</th>
                    <th class="w-10 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100 rounded-tr rounded-br"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="project" items="${projectList}">
                    <tr class="stripe-dark">
                        <td class="px-4 py-3">${project.id}</td>
                        <td class="px-4 py-3">${project.title}</td>
                        <td class="px-4 py-3">${project.subtitle}</td>
                        <td class="px-4 py-3">
                            <fmt:formatNumber value="${project.targetAmount}" type="number" pattern="#,###"/>won
                        </td>
                        <td class="px-4 py-3">
                            <javatime:format value="${project.startDate}" var="startDate" pattern="yyyy-MM-dd"/>
                                ${startDate}</td>
                        <td>
                            <javatime:format value="${project.endDate}" var="endDate" pattern="yyyy-MM-dd"/>
                                ${endDate}</td>
                        <td class="px-4 py-3"><img src="${project.titleImageUrl}" width="100px" height="100px"></td>
                        <td class="px-4 py-3">${project.status}</td>
                        <td class="px-4 py-3"><a href="/projects/${project.userId}/${project.id}/edit/basics">수정</a> </td>
                        <td class="px-4 py-3"><a href="/projects/${project.userId}/${project.id}/edit/basics">2단계 기본정보입력</a></td>
                        <td class="px-4 py-3"><button onclick="deletefn('${project.id}')">삭제</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/">홈</a>
            </div>

        </div>
    </section>


</body>
</html>