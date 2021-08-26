<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
    <title>배송지 목록</title>
    <style>
        table,th,tr,td {
            border-collapse: collapse;
            border: rgb(248,240,150) 2px solid;
            text-align: center;
        }
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<section class="text-gray-600 body-font">
    <div class="container px-5 py-24 mx-auto">
        <div class="flex flex-col text-center w-full mb-20">
            <sec:authentication property="principal" var="user"></sec:authentication>
            <h1 class="sm:text-4xl text-3xl font-medium title-font mb-2 text-gray-900">배송지 목록</h1>

        </div>
        <div class="lg:w-2/3 w-full mx-auto overflow-auto">
            <div class="flex justify-end text-lg font-semibold tracking-wide mb-3 mr-3 text-gray-700 underline">
                <a href="/profile/addresses/new" class="hover:text-yellow-600">+ 배송지 추가하기</a>
            </div>
            <table class="table-auto w-full text-left whitespace-no-wrap">
                <thead>
                <tr>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-yellow-300 rounded-tl rounded-bl">수령인</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-yellow-300">주소</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-yellow-300">연락처</th>
                    <th colspan="2" class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-yellow-300">관리</th>
<%--                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-yellow-300">삭제</th>--%>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="list" items="${addressList}">
                    <tr>
                        <td>${list.name}</td>
                        <td>${list.postcode}<br>${list.road}${list.jibun}${list.detail}${list.chamgo}</td>
                        <td>${list.phone}</td>
<%--                        <td><a href="/profile/addresses/${list.id}">수정</a></td>--%>
                        <td><button type="button" onclick="location.href='/profile/addresses/${list.id}'"
                                    class="text-black bg-yellow-300 border-0 mx-2 py-2 px-3 focus:outline-none hover:bg-yellow-400 rounded text-base">수정</button></td>
                        <td>
                            <form action="/profile/addresses/${list.id}/delete" method="post">
<%--                                <input type="submit" value="삭제">--%>
                                <button class="text-black bg-yellow-300 border-0 mt-4 mx-2 py-2 px-3 focus:outline-none hover:bg-yellow-400 rounded text-base">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>

    </div>
</section>

</body>
</html>
