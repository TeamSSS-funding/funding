<%--
  Created by IntelliJ IDEA.
  User: hsh
  Date: 2021-07-16
  Time: 오후 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 목록페이지</title>

    <script>
        function deletefn(id) {
            location.href = "productdelete?pronumber=" + id;
        }
    </script>

    <style type="text/css">
        table, tr, td, th {
            border: 1px black solid;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<table>
    <tr>
        <th>상품번호</th>
        <th>상품명</th>
        <th>가격</th>
        <th>상품이미지1</th>
        <th>상품이미지2</th>
        <th>상품이미지3</th>
        <th>상품상세 설명</th>
        <th>카테고리 번호</th>
        <th>정보 수정</th>
        <th>삭제</th>
    </tr>
    <c:forEach var="productdto" items="${product}">
        <tr>
            <td>${productdto.pronumber}</td>
            <td>${productdto.proname}</td>
            <td><fmt:formatNumber value="${productdto.proprice}"
                                  pattern="#,###" />원</td>
            <td><img
                    src="resources/productImages/${productdto.proimagename1}"
                    height="200" width="200"></td>
            <td><img
                    src="resources/productImages/${productdto.proimagename2}"
                    height="200" width="200"></td>
            <td><img
                    src="resources/productImages/${productdto.proimagename3}"
                    height="200" width="200"></td>
            <td>${productdto.procontents}</td>
            <td>${productdto.procano}</td>
            <td><a href="productupdate?pronumber=${productdto.pronumber}">정보수정</a></td>
            <td><button onclick="deletefn('${productdto.pronumber}')">삭제</button></td>
        </tr>
    </c:forEach>
</table>
<a href="..">홈으로</a>
</body>
</html>
