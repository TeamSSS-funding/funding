<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>

function idoverlap(){
	
	var inputId=document.getElementById('m_id').value;
	var idexp = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,10}$/;
    var id = document.getElementById('m_id').value;
    var result = document.getElementById('idcheck');
    
	console.log(inputId);
	$.ajax({
		type: 'post',
		url: 'idcheck',
		data: {'id': inputId},
		dataType: 'text',
		async: false,
		success: function(idcheck){
			
			console.log('잘되고있음!!');
			console.log(idcheck);
			if(idcheck=="ok"){
				
				if(id.match(idexp)){ 
		            result.style.color = 'green';
		            result.innerHTML = '사용가능한 아이디입니다.';

		        } else if(id.length == 0){
		            result.style.color = 'red';
		            result.innerHTML = '필수정보입니다.';
		      
		        } else {
		            result.style.color = 'red';
		            result.innerHTML = '아이디는 6~10자리,소문자,숫자를 포함하여 만들어주세요.';
		        }
				
//				checkResult.style.color = 'green';
//				checkResult.innerHTML = '사용가능한 아이디 입니다.';
			} else {
				result.style.color = 'red';
				result.innerHTML = '이미 사용중인 아이디 입니다.';
				
			}
			
		},
		error: function(){
			console.log('뭔가 잘못됨...');
		}
		
	});
	
}
	

	
    function password1(){
        
        var password = document.getElementById('m_password').value;
        var pwdresult = document.getElementById('passwordresult');
        var passwordexp = /^(?=.*[a-z])(?=.*\d)(?=.*[!#$-])[a-zA-Z\d!#$-]{8,16}$/;
        if(password.length==0){
            pwdresult.style.color ='red';
            pwdresult.innerHTML = '필수 입력 정보 입니다.';
        }else if(!password.match(passwordexp)){
            pwdresult.style.color ='red';
            pwdresult.innerHTML ='비밀번호는 8~16자리, 특수문자(!#$-),숫자를 포함하여 만들어주세요.'
        }else{
            pwdresult.style.color = 'green';
            pwdresult.innerHTML = '사용 가능한 비밀번호 입니다.'
        }

    }
    
    function namecheck(){
        var name= document.getElementById('m_name').value;
        var nameresult = document.getElementById('result');
        if(name.length==0){
            nameresult.style.color ='red';
            nameresult.innerHTML = '필수 입력 정보 입니다.';

        } else {
            nameresult.style.color ='green';
            nameresult.innerHTML = '이름 입력 완료.';
        }
    }
	
    
    function phonecheck(){
        // 세자리숫자 - 네자리숫자 - 네자리숫자 (대시 - 포함)
        var exp = /^\d{3}-\d{4}-\d{4}$/;
        var m_phone = document.getElementById('m_phone').value;
        var result = document.getElementById('phoneresult');
        if(m_phone.match(exp)){
            result.style.color = 'green';
            result.innerHTML = '유효한형식';
        } else {
            result.style.color = 'red';
            result.innerHTML = '유효하지않은 전화번호 형식';
        }

    }
    
    function email1(){
        var email = document.getElementById('emailaddress').value;
        document.getElementById('domain').value = email;
  
        }
</script>


<body>
<form action="${pageContext.request.contextPath}/memberJoin" method="post">
<section class="text-gray-600 body-font">
	<div class="container px-5 py-24 mx-auto flex flex-wrap items-center">

		<div class="lg:w-2/6 md:w-1/2 bg-gray-100 rounded-lg p-8 flex flex-col mx-auto w-full mt-10 md:mt-0">
			<h2 class="text-gray-900 text-lg font-medium title-font mb-5">회원가입</h2>
			<div class="relative mb-4">
				<label for="m_id" class="leading-7 text-sm text-gray-600">아이디</label>
				<input type="text" id="m_id" name="id" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
				<span id = idcheck></span><br>
			</div>
			<div class="relative mb-4">
				<label for="m_password" class="leading-7 text-sm text-gray-600">비밀번호</label>
				<input type="password" id="m_password" name="password" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
			</div>
			<div class="relative mb-4">
				<label for="pwcheck" class="leading-7 text-sm text-gray-600">비밀번호 체크</label>
				<input type="password" name="pwcheck" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
				<span id = passwordresult></span>
			</div>
			<div class="relative mb-4">
				<label for="m_name" class="leading-7 text-sm text-gray-600">이름</label>
				<input type="text" id="m_name" name="name" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
				<span id = result></span>
			</div>
			<div class="relative mb-4">
				<label for="email" class="leading-7 text-sm text-gray-600">이메일</label>
				<input type="text" name="email" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
			</div>
			<div class="relative mb-4">
				<label for="full-name" class="leading-7 text-sm text-gray-600">전화번호</label>
				<input type="text" id="m_phone" name="phone" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
				<span id = "phoneresult"></span>
			</div>
			<button class="text-black bg-yellow-300 border-0 py-2 px-8 focus:outline-none hover:bg-yellow-400 rounded text-lg">회원가입</button>

		</div>
	</div>
</section>
</form>

<%--<form action="${pageContext.request.contextPath}/memberJoin" method="post">--%>
<%--		아이디: <input type="text" name="id" id="m_id" onblur="idoverlap()"><br>--%>
<%--			<span id = idcheck></span><br>--%>
<%--		비밀번호: <input type="password" name="password" id="m_password" onblur="password1()"><br>--%>
<%--		비밀번호 확인: <input type="password" name="pwcheck"><br>--%>
<%--			<span id = passwordresult></span><br>--%>
<%--		이름: <input type="text" name="name" id="m_name" onblur="namecheck()"><br>--%>
<%--			<span id = result></span><br>--%>
<%--		이메일　　　 : <input type="text" name ="email"><br>--%>

<%--        전화번호: <input type = "text" id="m_phone" name="phone" onkeyup = "phonecheck()"><br>--%>
<%--  		 <span id = "phoneresult"></span><br>--%>



<%--    <input type="submit" value="가입">--%>

<%--	</form>--%>
<%--<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>--%>
<%--<a href="https://kauth.kakao.com/oauth/authorize?client_id=631b84f94a27727f5c58546dfe62d1d3&amp;redirect_uri=http://localhost:8081/kakao_callback&amp;response_type=code&amp;client_secret=0mR4VqB8bK4jNBSDvv5ceoIE1w8qrB99">--%>
<%--<img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="222">--%>
<%--</a>--%>
<%--<script>--%>
<%--Kakao.init('6d3d4490cc515e838c6039dfcd781f48'); //★ 수정 할 것 : SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.--%>
<%--console.log(Kakao.isInitialized()); // SDK 초기화 여부를 판단합니다.--%>

<%--function loginWithKakao() {--%>
<%--    Kakao.Auth.authorize({--%>
<%--        redirectUri: encodeURI('http://localhost:8081/kakao_callback') --%>
<%--    })--%>
<%--}      --%>
<%--</script>--%>

</body>
</html>