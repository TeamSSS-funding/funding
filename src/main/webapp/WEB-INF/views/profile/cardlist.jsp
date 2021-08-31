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
                    <th class="w-10 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100 rounded-tr rounded-br">사용자 고유ID</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var = "card" items="${cardList}">
                <tr>
                    <td class="px-4 py-3"><a href="cards/${card.id}" style="color:blue"> ${card.cardNumber}</a></td>
                    <td class="px-4 py-3">${card.expiredDate}</td>
                    <td class="px-4 py-3">${card.cardPassword}</td>
                    <td class="px-4 py-3">${card.dateOfBirth}</td>
                    <td class="px-4 py-3">${card.userId}</td>

                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</section>

</body>
</html>
