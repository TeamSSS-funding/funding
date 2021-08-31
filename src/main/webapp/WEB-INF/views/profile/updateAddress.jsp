<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>배송지 수정</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
    <link href="/dist/tailwind.css" rel="stylesheet">
</head>
<body>
<div class="">
    <header class="text-gray-600 body-font">
        <nav class="container mx-auto flex flex-wrap p-5 justify-between items-center">
            <!-- Logo -->
            <div class="flex items-center title-font font-medium text-gray-900 mb-4 md:mb-0">
                <!-- Hamburger -->
                <button id="navbar-hamburger">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-10 h-10 text-white p-2 bg-amber-200 rounded-full" viewBox="0 0 24 24">
                        <path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"></path>
                    </svg>
                </button>
                <span class="ml-3 text-xl"><a href="/">mygoodsupporter</a></span>
            </div>
            <!-- Menu -->
            <div class="hidden w-full order-last lg:flex lg:w-auto lg:order-none text-base"
                 id="navbar-collapse-menu" >
                <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900">진행중인 프로젝트</a>
                <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900">오픈예정 프로젝트</a>
                <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="${pageContext.request.contextPath}/projects/projectList">모든 프로젝트</a>
                <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="${pageContext.request.contextPath}/projects/new">프로젝트 신청</a>
            </div>
            <!-- CTA -->
            <div class="flex items-center mb-4 md:mb-0">
                <sec:authorize access="isAnonymous()">
                    <button class="inline-flex justify-center items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="login">로그인</a>
                        <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                            <path d="M5 12h14M12 5l7 7-7 7"></path>
                        </svg>
                    </button>
                    &nbsp;
                    <button class="inline-flex items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="joinPage">회원가입</a>
                        <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                            <path d="M5 12h14M12 5l7 7-7 7"></path>
                        </svg>
                    </button>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <button class="inline-flex justify-center items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="logout">로그아웃</a>
                        <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                            <path d="M5 12h14M12 5l7 7-7 7"></path>
                        </svg>
                    </button>
                    &nbsp;
                    <button class="inline-flex items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="/profile">Mypage</a>
                        <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                            <path d="M5 12h14M12 5l7 7-7 7"></path>
                        </svg>
                    </button>
                </sec:authorize>
            </div>
        </nav>
    </header>
<form:form action="/profile/addresses/${addressForm.id}/edit" method="post" modelAttribute="addressForm">
    <section class="text-gray-600 body-font">
        <div class="container px-5 py-24 mt-10 mx-auto flex flex-wrap items-center">
            <div class="lg:w-2/6 md:w-1/2 bg-gray-50 rounded-lg p-8 flex flex-col mx-auto w-full mt-10 md:mt-0">
                <h2 class="text-gray-900 text-2xl font-medium title-font mb-5 text-center">배송지 수정</h2>
                <div class="relative mb-4">
                    <form:input path="id" id="id" value="${addressForm.id}" cssClass="hidden"/>
                    <label for="name" class="leading-7 text-sm text-gray-600">수령인</label>
                    <form:input path="name" id="name" value="${addressForm.name}"
                                cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
                    <span><form:errors path="name" /></span>
                </div>

                <div class="form-group flex flex-wrap">
                    <label for="postcode" class="w-full block leading-7 text-sm text-gray-600">우편번호</label>
                    <div class="flex flex-wrap">
                        <form:input path="postcode" type="text" class="form-control" id="sample4_postcode" name="postcode"  onblur="addcheck()" value="${addressForm.postcode}"
                                    cssClass="mr-2 w-1/2 bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                    cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
                        <span><form:errors path="postcode" /></span>
                        <span id="addcheck"></span>
                        <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"
                               class="flex-grow text-gray-700 bg-yellow-200 border-0 py-2 px-8 focus:outline-none hover:bg-yellow-300 rounded text-base"><br>
                    </div>
                </div>

                <div class="form-group">
                    <label for="jibun" class="leading-7 text-sm text-gray-600">지번주소</label>
                    <form:input path="jibun" type="text" class="form-control" id="sample4_jibunAddress" name="jibun" value="${addressForm.jibun}"
                                cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
                    <span><form:errors path="jibun" /></span>
                    <span id="guide" class="form-control" style="color:#999;display:none"></span>
                </div>

                <div class="form-group">
                    <label for="chamgo" class="leading-7 text-sm text-gray-600">참고항목</label>
                    <form:input path="chamgo" type="text" class="form-control" id="sample4_extraAddress" name="chamgo" value="${addressForm.chamgo}"
                                cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
                    <span><form:errors path="chamgo" /></span>
                </div>

                <div class="form-group">
                    <label for="road" class="leading-7 text-sm text-gray-600">도로명주소</label>
                    <form:input path="road" type="text" class="form-control" id="sample4_roadAddress" name="road" value="${addressForm.road}"
                                cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
                    <span><form:errors path="road" /></span>
                </div>

                <div class="form-group">
                    <label for="detail" class="leading-7 text-sm text-gray-600">상세주소</label>
                            <form:input path="detail" type="text" class="form-control" id="sample4_detailAddress" name="detail" value="${addressForm.detail}"
                                        cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                        cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
                        <span><form:errors path="detail" /></span>
                </div>

                <div class="relative mb-4">
                    <label for="phone" class="leading-7 text-sm text-gray-600">연락처</label>
                    <form:input path="phone" id="phone" value="${addressForm.phone}"
                                cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"/>
                    <span><form:errors path="phone" /></span>
                </div>
                <div class="flex mt-3">
                <button class="flex-auto mr-1 text-black bg-yellow-200 border-0 py-2 px-8 focus:outline-none hover:bg-yellow-300 rounded text-lg">주소수정</button>
                <button class="flex-auto ml-1 text-black bg-yellow-200 border-0 py-2 px-8 focus:outline-none hover:bg-yellow-300 rounded text-lg"
                        type="button" onclick="location.href='/profile/addresses'">돌아가기</button>
                </div>
            </div>
        </div>
    </section>

<%--    수령인 <input type="text" name="name" value="${address.name}">--%>

<%--    <div class="form-group">--%>
<%--        <input type="text" class="form-control" id="sample4_postcode" name="postcode" placeholder="우편번호" value="${address.postcode}" onblur="addcheck()">--%>
<%--        <span id="addcheck"></span>--%>
<%--        <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--        <input type="text" class="form-control" id="sample4_jibunAddress" name="jibun" placeholder="지번주소" value="${address.jibun}">--%>
<%--        <span id="guide" class="form-control" style="color:#999;display:none"></span>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--        <input type="text" class="form-control" id="sample4_extraAddress" name="chamgo" placeholder="참고항목" value="${address.chamgo}">--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--        <input type="text" class="form-control" id="sample4_roadAddress" name="road" placeholder="도로명주소" value="${address.road}">--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--        <input type="text" class="form-control" id="sample4_detailAddress" name="detail" placeholder="상세주소" value="${address.detail}">--%>
<%--    </div>--%>
<%--    연락처 <input type="text" name="phone" value="${address.phone}"><br>--%>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
        function sample4_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                    // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var roadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 참고 항목 변수
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraRoadAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraRoadAddr !== ''){
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }
                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('sample4_postcode').value = data.zonecode;
                    document.getElementById("sample4_roadAddress").value = roadAddr;
                    document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                    // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                    if(roadAddr !== ''){
                        document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                    } else {
                        document.getElementById("sample4_extraAddress").value = '';
                    }
                    var guideTextBox = document.getElementById("guide");
                    // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                    if(data.autoRoadAddress) {
                        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                        guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                        guideTextBox.style.display = 'block';
                    } else if(data.autoJibunAddress) {
                        var expJibunAddr = data.autoJibunAddress;
                        guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                        guideTextBox.style.display = 'block';
                    } else {
                        guideTextBox.innerHTML = '';
                        guideTextBox.style.display = 'none';
                    }
                }
            }).open();
        }
    </script>
</form:form>
<%--     city<input type="text" id="city" value="${address.city}"><br>--%>
<%--    city<form:input path="city" id="city"--%>
<%--    cssClass="w-full bg-white rounded border border-gray-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"--%>
<%--    cssErrorClass="w-full bg-white rounded border border-red-300 focus:border-yellow-500 focus:ring-2 focus:ring-border-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"--%>
<%--    value="${address.city}"--%>
<%--    />--%>
<%--    <span><form:errors path="city"/></span><br>--%>
<%--    street<form:input path="street" id="street" value="${address.street}"/>--%>

<%--    <span><form:errors path="street"/></span><br>--%>
<%--    zipcode<form:input path="zipcode" id="zipcode" value="${address.zipcode}"/>--%>

<%--    <span><form:errors path="zipcode"/></span><br>--%>
<%--    street<input type="text" name="street" value="${address.street}"><br>--%>
<%--    zipcode<input type="text" name="zipcode" value="${address.zipcode}"><br>--%>
<%--    <input type="submit" value="수정">--%>
    <footer class="mt-20 h-1/6 border-t-2 border-gray-200">
        <div class="text-center">
            <%--            <p class="mb-2">My Good Supporter</p>--%>
            <p class="text-xs pt-10 mb-1">https://github.com/mygoodsupporter/mygoodsupporter</p>
            <p class="text-xs">COPYRIGHT © MYGOODSUPPORTER ALL RIGHTS RESERVED.</p>
        </div>
    </footer>
</div>
</body>
</html>
