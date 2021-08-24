<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
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
                <thead>
                <tr class="stripe-dark">
                    <th class="fw6 tl pa3 bg-white">프로젝트 번호</th>
                    <th class="fw6 tl pa3 bg-white">프로젝트 제목</th>
                    <th class="fw6 tl pa3 bg-white">프로젝트 부제목</th>
                    <th class="fw6 tl pa3 bg-white">목표 금액</th>
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
                <c:forEach var="project" items="${projectList}">
                    <tr class="stripe-dark">
                        <td>${project.id}</td>
                        <td>${project.title}</td>
                        <td>${project.subtitle}</td>
                        <td>
                            <fmt:formatNumber value="${project.targetAmount}" type="number" pattern="#,###"/>won
                        </td>
                        <td>
                            <javatime:format value="${project.startDate}" var="startDate" pattern="yyyy-MM-dd"/>
                                ${startDate}</td>
                        <td>
                            <javatime:format value="${project.endDate}" var="endDate" pattern="yyyy-MM-dd"/>
                                ${endDate}</td>
                        <td><img src="${project.titleImageUrl}" width="100px" height="100px"></td>
                        <td><img src="${project.contentsImageUrl}" width="100px" height="100px" align="상세내용이미지"></td>
                        <td>${project.status}</td>
                        <td><a href="/projects/${project.userId}/${project.id}/edit/basics">수정</a> </td>
                        <td><a href="/projects/${project.userId}/${project.id}/edit/basics">2단계 기본정보입력</a></td>
                        <td><button onclick="deletefn('${project.id}')">삭제</button></td>
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