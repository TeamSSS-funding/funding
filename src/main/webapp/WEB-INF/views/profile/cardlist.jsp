<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카드 목록 조회</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<section class="text-gray-600 body-font">
    <div class="container px-5 py-24 mx-auto">
        <div class="flex flex-col text-center w-full mb-20">
            <sec:authentication property="principal" var="user"></sec:authentication>
            <h1 class="sm:text-4xl text-3xl font-medium title-font mb-2 text-gray-900">${user.username}님의 카드 목록 조회</h1>

        </div>
        <div class="lg:w-2/3 w-full mx-auto overflow-auto">
            <table class="table-auto w-full text-left whitespace-no-wrap">
                <thead>
                <tr>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100 rounded-tl rounded-bl">카드 번호(12자리)</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">카드 유효기간(MM/YY)</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">카드 비밀번호 앞 2자리</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">소유주 생년월일</th>
                    <th class="w-10 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100 rounded-tr rounded-br"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var = "cardinfo" items="${cardList}">
                <tr>

                    <td class="px-4 py-3">${cardinfo.card_number}</td>
                    <td class="px-4 py-3">${cardinfo.expired_date}</td>
                    <td class="px-4 py-3">${cardinfo.card_password}</td>
                    <td class="px-4 py-3">${cardinfo.date_of_birth}</td>
                    <td class="px-4 py-3">${cardinfo.userId}</td>
                    <td class="w-10 text-center"><label>
                        <input name="plan" type="radio">
                    </label></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="flex pl-4 mt-4 lg:w-2/3 w-full mx-auto">
            <button class="flex ml-auto text-white bg-indigo-500 border-0 py-2 px-6 focus:outline-none hover:bg-indigo-600 rounded">수정</button>
            <button class="flex ml-auto text-white bg-indigo-500 border-0 py-2 px-6 focus:outline-none hover:bg-indigo-600 rounded">삭제</button>
        </div>
    </div>
</section>

</body>
</html>
