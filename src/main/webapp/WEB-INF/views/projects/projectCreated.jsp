<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>내가 만든 프로젝트</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<section class="text-gray-600 body-font">
    <div class="container px-5 py-24 mx-auto">
        <div class="flex flex-col text-center w-full mb-20">
            <sec:authentication property="principal" var="user"></sec:authentication>
            <h1 class="sm:text-4xl text-3xl font-medium title-font mb-2 text-gray-900">${user.username}님이 만든 프로젝트</h1>

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
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">상세 내용 이미지</th>
                    <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100">프로젝트 상태</th>
                    <th class="w-10 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-100 rounded-tr rounded-br"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var = "project" items="${projectList}">
                    <tr>
                        <td class="px-4 py-3">${project.id}</td>
                        <td class="px-4 py-3">${project.title}</td>
                        <td class="px-4 py-3">${project.subtitle}</td>
                        <td class="px-4 py-3">${project.targetAmount}</td>
                        <td class="px-4 py-3">${project.targetAmount}</td>
                        <td class="px-4 py-3">
                            <javatime:format value="${project.startDate}" var="startDate" pattern="yyyy-MM-dd"/>
                            ${startDate}
                        </td>
                        <td class="px-4 py-3">
                            <javatime:format value="${project.endDate}" var="endDate" pattern="yyyy-MM-dd"/>
                                ${endDate}
                        </td>
                        <td class="px-4 py-3"><img src="${project.titleImageUrl}" width="100px" height="100px"></td>
                        <td class="px-4 py-3"><img src="${project.contentsImageUrl}" width="100px" height="100px" align="상세내용이미지"></td>
                        <td class="px-4 py-3">${project.status}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</section>

</body>
</html>
