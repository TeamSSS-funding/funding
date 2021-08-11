<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<title>프로젝트 목록</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>

    function deletefn(id) {
        location.href="/projects/projectList/delete/" +id;
    }

</script>
<style>
    table, tr, td, th{
        border: solid 1px lightcoral;
        border-collapse: collapse;
        text-align: center;
    }
    th{
        background-color: antiquewhite;
    }

    a{
        text-decoration: none;
        color: black;
    }
</style>
<body class="w-100 sans-serif">

<section class="flex items-center">
    <div class="pa4">
        <div class="overflow-auto">
            <table class="f6 w-100 mw8 center">

                <%--                <sec:authorize access="hasAnyRole('ROLE_USER')">--%>
                <%--                    <c:if test="${member.id} eq ${project.memberId}">--%>
                <thead>
                <tr class="stripe-dark">
                    <th class="fw6 tl pa3 bg-white">프로젝트 번호</th>
                    <th class="fw6 tl pa3 bg-white">회원 아이디</th>
                    <th class="fw6 tl pa3 bg-white">회원 이름</th>
                    <th class="fw6 tl pa3 bg-white">목표 금액</th>
                    <th class="fw6 tl pa3 bg-white">프로젝트 내용</th>
                    <th class="fw6 tl pa3 bg-white">시작 날짜</th>
                    <th class="fw6 tl pa3 bg-white">종료 날짜</th>
                    <th class="fw6 tl pa3 bg-white">대표 이미지</th>
                    <th class="fw6 tl pa3 bg-white">상세 내용 이미지</th>
                    <th class="fw6 tl pa3 bg-white">프로젝트 상태</th>
                    <th class="fw6 tl pa3 bg-white">수정</th>
                    <th class="fw6 tl pa3 bg-white">삭제</th>
                </tr>
                </thead>
                <tbody class="lh-copy">
                <c:forEach var="projectList" items="${projectList}">
                    <tr class="stripe-dark">
                        <td>${projectList.id}</td>
                        <td>${projectList.userId}</td>
                        <td>${projectList.name}</td>
                        <td>${projectList.targetAmount}</td>
                        <td>${projectList.content}</td>
                        <td>${projectList.startDate}</td>
                        <td>${projectList.endDate}</td>
                        <td>${projectList.titleImageName}</td>
                        <td><img src="${projectList.contentsImageName}" width="100px" height="100px"></td>
                        <td>${projectList.status}</td>
                        <td><a href="/projects/projectList/update/${projectList.id}">수정</a> </td>
                        <td><button onclick="deletefn('${projectList.id}')">삭제</button></td>
                    </tr>
                </c:forEach>
                </tbody>
                <%--                    </c:if>--%>
                <%--                </sec:authorize>--%>

                <%--                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">--%>
                <%--                    <thead>--%>
                <%--                    <tr class="stripe-dark">--%>
                <%--                        <th class="fw6 tl pa3 bg-white">Id</th>--%>
                <%--                        <th class="fw6 tl pa3 bg-white">Title</th>--%>
                <%--                        <th class="fw6 tl pa3 bg-white">MemberId</th>--%>
                <%--                        <th class="fw6 tl pa3 bg-white">Current Amount</th>--%>
                <%--                        <th class="fw6 tl pa3 bg-white">Total Amount</th>--%>
                <%--                        <th class="fw6 tl pa3 bg-white">Status</th>--%>
                <%--                    </tr>--%>
                <%--                    </thead>--%>
                <%--                    <tbody class="lh-copy">--%>
                <%--                    <c:forEach var="project" items="${projects}">--%>
                <%--                        <tr class="stripe-dark">--%>
                <%--                            <td class="pa3">--%>
                <%--                                <a href="/projects/${project.slug}">--%>
                <%--                                        ${project.id}--%>
                <%--                                </a>--%>
                <%--                            </td>--%>
                <%--                            <td class="pa3">--%>
                <%--                                <a href="/projects/${project.slug}">--%>
                <%--                                        ${project.name}--%>
                <%--                                </a>--%>
                <%--                            </td>--%>
                <%--                            <td class="pa3">${project.memberId}</td>--%>
                <%--                            <td class="pa3">${project.currentAmount}</td>--%>
                <%--                            <td class="pa3">${project.targetAmount}</td>--%>
                <%--                            <td class="pa3">${project.status}</td>--%>
                <%--                        </tr>--%>
                <%--                    </c:forEach>--%>
                <%--                    </tbody>--%>
                <%--                </sec:authorize>--%>

            </table>
            <a href="../">홈</a>
        </div>
    </div>
</section>
</body>
</html>