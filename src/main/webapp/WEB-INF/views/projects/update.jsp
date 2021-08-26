<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>프로젝트 수정</title>
</head>
<body>
<sec:authentication property="principal" var="user"></sec:authentication>

<form action="/projects/projectList/update/${project.id}" method="post" enctype="multipart/form-data">
    <input type="text" name="id" value="${project.id}"><br>
    프로젝트 제목 <input type="text" name="name" value="${project.name}"><br>
    프로젝트 신청자 <input type="text" name="userId" value="${user.id}" readonly><br>
    <%--        카테고리 선택 <input type="radio" name="p_c_name" value="fashion">패션--%>
    <%--        <input type="radio" name="p_c_name" value="food">음식--%>
    <%--        <input type="radio" name="p_c_name" value="movie">영화--%>
    <%--        <input type="radio" name="p_c_name" value="play">공연--%>
    <%--        <input type="radio" name="p_c_name" value="book">출판 <br>--%>
    프로젝트 시작일 <input type="text" name="startDate" value="${project.startDate}"><br>
    프로젝트 마감일 <input type="text" name="endDate" value="${project.endDate}"><br>
    목표금액 <input type="text" name="targetAmount" value="${project.targetAmount}">원<br>
    프로젝트 상세설명 <textarea name="content" rows="10" cols="20">${project.content}</textarea><br>
    대표첨부이미지 <input type="text" name="titleImageName" value="title"><br>
    상세내용첨부이미지 <input type="file" name="contentsImage"><br>
    <input type="hidden" name="status" value="PENDING">
    <input type="submit" value="수정">
</form>

</body>
</html>
