<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>카드 등록</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<form action="${pageContext.request.contextPath}/profile/cardRegister" method="post">
<section class="text-gray-600 body-font">
    <div class="container px-5 py-24 mx-auto flex flex-wrap items-center">
        <div class="lg:w-3/5 md:w-1/2 md:pr-16 lg:pr-0 pr-0">
            <h1 class="title-font font-medium text-3xl text-gray-900">선택한 리워드</h1>
            <p class="leading-relaxed mt-4">리워드 설명</p>
        </div>
        <div class="lg:w-2/6 md:w-1/2 bg-gray-100 rounded-lg p-8 flex flex-col md:ml-auto w-full mt-10 md:mt-0">
            <h2 class="text-gray-900 text-lg font-medium title-font mb-5">결제 정보 입력</h2>
            <div class="relative mb-4">
                <label for="cardNumber" class="leading-7 text-sm text-gray-600">카드 번호(12자리)</label>
                <input type="text" id="cardNumber" name="cardNumber" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
            </div>
            <div class="relative mb-4">
                <label for="expiredDate" class="leading-7 text-sm text-gray-600">카드 유효기간(MM/YY)</label>
                <input type="text" id="expiredDate" name="expiredDate" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
            </div>
            <div class="relative mb-4">
                <label for="cardPassword" class="leading-7 text-sm text-gray-600">카드 비밀번호 앞 2자리</label>
                <input type="password" id="cardPassword" name="cardPassword" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
            </div>
            <div class="relative mb-4">
                <label for="dateOfBirth" class="leading-7 text-sm text-gray-600">소유주 생년월일</label>
                <input type="text" id="dateOfBirth" name="dateOfBirth" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
            </div>
            <button class="text-white bg-yellow-500 border-0 py-2 px-8 focus:outline-none hover:bg-yellow-600 rounded text-lg">결제하기</button>
            <p class="text-xs text-gray-500 mt-3"><input type="checkbox">결제사 정보제공 동의</p>
        </div>
    </div>
</section>
</form>

</body>
</html>
