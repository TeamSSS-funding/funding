<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 신청</title>
</head>
<body>
	<form action="projectRequest" method="post">
		프로젝트 제목 <input type="text" name="p_name"><br>
		카테고리 선택 <input type="radio" name="p_c_name" value="fashion">패션
				<input type="radio" name="p_c_name" value="food">음식
				<input type="radio" name="p_c_name" value="movie">영화
				<input type="radio" name="p_c_name" value="play">공연
				<input type="radio" name="p_c_name" value="book">출판 <br>
		프로젝트 시작일 <input type="date" name="p_startdate"><br>
		프로젝트 마감일 <input type="date" name="p_enddate"><br>
		목표금액 <input type="number" name="p_price" min="0" max="999999999" step="50000"><br>
		프로젝트 상세설명 <textarea name="p_contents" rows="10" cols="20"></textarea><br>
		첨부파일 <input type="file" name="p_image"><br>
		<input type="submit" value="신청">
	</form>
</body>
</html>