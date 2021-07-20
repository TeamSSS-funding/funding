<%--
  Created by IntelliJ IDEA.
  User: hsh
  Date: 2021-07-16
  Time: 오후 2:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>심사 페이지</title>
    <script type="text/javascript">
        function approval() {
            var result=confirm("프로젝트 승인을 하시겠습니까?");

            if(result === "ok"){
                location.href="projectList";
            } else {
                location.href="resultSorry";
            }
        }
    </script>
</head>
<body>
<form action="projectSimsa">
    프로젝트 제목 :${pdto.p_name}<br>
    카테고리 : ${pdto.p_c_name}<br>
    프로젝트: ${pdto.p_startdate}<br>
    프로젝트 마감일 : ${pdto.p_enddate}<br>
    목표금액 : <fmt:formatNumber value="${pdto.p_price}"
                             pattern="#,###" />원<br>
    프로젝트 상세설명 :<textarea rows="10" cols="20">${pdto.p_contents}</textarea><br>
    첨부파일 :<img src="resources/productImages/${pdto.p_imgname}"
               height="200" width="200"><br>
    <button onclick="approval()">확인</button>

</form>
</body>
</html>
