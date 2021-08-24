<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>mygoodsupporter</title>
    <link href="/dist/tailwind.css" rel="stylesheet">
</head>
<body>
<header class="text-gray-600 body-font">
    <nav class="container mx-auto flex flex-wrap p-5 justify-between items-center">
        <!-- Logo -->
        <a href="/" class="flex items-center title-font font-medium text-gray-900 mb-4 md:mb-0">
            <!-- Hamburger -->
            <button id="navbar-hamburger">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-10 h-10 text-white p-2 bg-yellow-400 rounded-full" viewBox="0 0 24 24">
                    <path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"></path>
                </svg>
            </button>
            <span class="ml-3 text-xl">mygoodsupporter</span>
        </a>
        <!-- Menu -->
        <div class="hidden w-full order-last lg:flex lg:w-auto lg:order-none text-base"
             id="navbar-collapse-menu" >
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900">진행중인 프로젝트</a>
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900">오픈예정 프로젝트</a>
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="projectList">모든 프로젝트</a>
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="projectRequestPage">프로젝트 신청</a>
        </div>
        <!-- CTA -->
        <div class="flex items-center mb-4 md:mb-0">
            <button class="inline-flex justify-center items-center bg-yellow-300 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-400 rounded text-base text-white"><a href="login">로그인</a>
                <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                    <path d="M5 12h14M12 5l7 7-7 7"></path>
                </svg>
            </button>
            &nbsp;
            <button class="inline-flex items-center bg-yellow-300 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base text-white"><a href="memberJoinPage">회원가입</a>
                <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                    <path d="M5 12h14M12 5l7 7-7 7"></path>
                </svg>
            </button>
        </div>
    </nav>
</header>

<main class="mt-0 lg:mt-12 text-gray-600 body-font">
    <section class="container mx-auto px-6">
        <div class="w-full md:flex md:flex-wrap lg:flex lg:flex-wrap justify-center items-center">
            <div class="p-4 w-full md:w-2/3 lg:w-3/5 md:flex md:flex-wrap lg:flex lg:flex-wrap items-center justify-center">
                <div class="flex flex-col w-full justify-center text-center">
                    <h1>${project.title}</h1>
                    <h2>by ${project.userId}</h2>
                    <div>
                        <span>icon</span>
                        <a href="/projects/${project.userId}/${project.id}/edit/preview">Preview</a>
                    </div>
                </div>

                <div class="flex flex-col w-full mt-8">
                    <h1 class="text-lg">Project Overview</h1>

                    <ul class="mt-4">
                        <li class="mt-4">
                            <a href="/projects/${project.userId}/${project.id}/edit/basics">
                                <article>
                                    <h1>기본</h1>
                                    <p>기본적인 정보를 입력해주세요</p>
                                </article>
                            </a>
                        </li>
                        <li class="mt-4">
                            <a href="/projects/${project.userId}/${project.id}/edit/rewards">
                                <article>
                                    <h1>선물</h1>
                                    <p>후원자가 받을 보상을 만들어주세요</p>
                                </article>
                            </a>
                        </li>
                        <li class="mt-4">
                            <a href="/projects/${project.userId}/${project.id}/edit/story">
                                <article>
                                    <h1>스토리</h1>
                                    <p>프로젝트만의 이야기를 들려주세요</p>
                                </article>
                            </a>
                        </li>
                        <li class="mt-4">
                            <a href="/projects/${project.userId}/${project.id}/edit/people">
                                <article>
                                    <h1>창작자</h1>
                                    <p>창작자 정보를 수정주세요</p>
                                </article>
                            </a>
                        </li>
                        <li class="mt-4">
                            <a href="/projects/${project.userId}/${project.id}/edit/payment">
                                <article>
                                    <h1>결제</h1>
                                    <p>후원받을 계좌 정보를 등록해주세요</p>
                                </article>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="flex flex-col w-full mt-4">
                    심사 신청하기
                </div>

                <div class="flex flex-col w-full mt-4">
                    Promotion
                </div>

                <form method="post" action="/projects/${project.userId}/${project.id}/delete"
                      class="mt-4">
                    <input type="submit" value="프로젝트 지우기">
                </form>
            </div>
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