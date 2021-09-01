<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>결제 화면</title>
    <link href="/dist/tailwind.css" rel="stylesheet">
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
</head>
<body>

<script>
    function iamport(){
            //가맹점 식별코드
        IMP.init('imp78976986');
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : '${order.id}',
            name : '주문명:결제테스트',
            amount : '${order.amount}',
            buyer_email : 'iamport@siot.do',
            buyer_name : '구매자이름',
            buyer_tel : '010-1234-5678',
            buyer_addr : '서울특별시 강남구 삼성동',
            buyer_postcode : '123-456'
        }, function(rsp) { //callback
            if ( rsp.success ) {
                console.log(rsp)
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "/verifyIamport/" + rsp.imp_uid, //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid: rsp.imp_uid,
                        merchant_uid: ${order.id}
                    }
                }).done((data) => {
                    console.log(data);
                    window.location.href = "/checkouts/${order.id}/payments/complete";
                }).fail(() => {
                    window.location.href = "/checkouts/${order.id}/payments/fail";
                })

            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                alert(msg);
            }
        });
    }

</script>
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
            <span class="ml-3 text-xl">mygoodsupporter</span>
        </div>
        <!-- Menu -->
        <div class="hidden w-full order-last lg:flex lg:w-auto lg:order-none text-base"
             id="navbar-collapse-menu" >
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900">진행중인 프로젝트</a>
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900">오픈예정 프로젝트</a>
            <a class="block mt-4 lg:inline-block lg:mt-0 lg:mr-5 hover:text-gray-900" href="${pageContext.request.contextPath}/projects">모든 프로젝트</a>
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
                <button class="inline-flex items-center bg-amber-200 border-0 py-1 px-3 focus:outline-none hover:bg-yellow-300 rounded text-base"><a href="profile">Mypage</a>
                    <svg fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="w-4 h-4 ml-1" viewBox="0 0 24 24">
                        <path d="M5 12h14M12 5l7 7-7 7"></path>
                    </svg>
                </button>
            </sec:authorize>
        </div>
    </nav>
</header>

<section class="text-gray-600 body-font overflow-hidden">
    <div class="container px-5 py-24 mx-auto">
        <div class="lg:w-4/5 mx-auto flex flex-wrap">
            <div class="lg:w-1/2 w-full lg:pr-10 lg:py-6 mb-6 lg:mb-0">
                <h2 class="text-sm title-font text-gray-500 tracking-widest">${projectDetails.makerProfile.username}</h2>
                <h1 class="text-gray-900 text-3xl title-font font-medium mb-4">${projectDetails.title}</h1>
                <div class="hidden flex mb-4">
                    <a class="flex-grow text-yellow-200 border-b-2 border-yellow-200 py-2 text-lg px-1"></a>
                    <a class="flex-grow border-b-2 border-gray-300 py-2 text-lg px-1">Reviews</a>
                    <a class="flex-grow border-b-2 border-gray-300 py-2 text-lg px-1">Details</a>
                </div>
                <p class="leading-relaxed mb-4">${projectDetails.subtitle}</p>
                <div class="flex border-t border-gray-200 py-2">
                    <span class="text-gray-500">${reward.title}</span>
                    <span class="ml-auto text-gray-900">${reward.id}</span>
                </div>
                <div class="flex border-t border-gray-200 py-2">
                    <span class="text-gray-500">아이템 목록</span>
                    <span class="ml-auto text-gray-900">${reward.items}</span>
                </div>
                <div class="flex border-t border-b mb-6 border-gray-200 py-2">
                    <span class="text-gray-500">수량</span>
                    <span class="ml-auto text-gray-900">1</span>
                </div>
                <div class="flex flex-wrap">
                    <span class="w-full title-font font-medium text-2xl text-gray-900">${order.amount}</span>
                    <button class="w-1/3 flex ml-auto text-white bg-yellow-300 border-0 py-2 focus:outline-none hover:bg-yellow-400 rounded">카드 결제</button>
                    <button class="w-1/3 flex ml-auto text-white bg-yellow-300 border-0 py-2 focus:outline-none hover:bg-yellow-400 rounded" onclick="iamport()">카카오페이 결제</button>
                </div>
            </div>

            <div class="flex flex-wrap w-full lg:w-1/2 lg:py-6 mb-6 lg:mb-0 lg:bg-gray-100">
                <form action="" method="post" class="flex flex-wrap lg:px-4">
                    <h2 class="block w-full text-gray-900 text-lg font-medium title-font mb-5">결제 정보 입력</h2>
                    <div class="w-full flex flex-wrap mb-4">
                        <label for="cardNumber" class="leading-7 text-sm text-gray-600">카드 번호(12자리)</label>
                        <input type="text" id="cardNumber" name="cardNumber" value="" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-200 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
                    </div>
                    <div class="w-full flex flex-wrap mb-4">
                        <label for="expiredDate" class="leading-7 text-sm text-gray-600">카드 유효기간(MM/YY)</label>
                        <input type="text" id="expiredDate" name="expiredDate" value="" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-200 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
                    </div>
                    <div class="w-full flex flex-wrap mb-4">
                        <label for="cardPassword" class="leading-7 text-sm text-gray-600">카드 비밀번호 앞 2자리</label>
                        <input type="password" id="cardPassword" name="cardPassword" value="" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-200 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
                    </div>
                    <div class="w-full flex flex-wrap mb-4">
                        <label for="dateOfBirth" class="leading-7 text-sm text-gray-600">소유주 생년월일</label>
                        <input type="text" id="dateOfBirth" name="dateOfBirth" value="" class="w-full bg-white rounded border border-gray-300 focus:border-yellow-200 focus:ring-2 focus:ring-yellow-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out">
                    </div>
                    <div class="w-full flex flex-wrap mb-4">
                        <p class="w-full text-xs text-gray-500 mb-4"><input type="checkbox" class="mr-2">결제사 정보제공 동의</p>
                        <button class="w-full ml-auto text-white bg-yellow-300 border-0 py-2 px-6 focus:outline-none hover:bg-yellow-400 rounded">카드 결제 하기</button>

                    </div>
                </form>
            </div>

        </div>
    </div>
</section>


</body>
</html>
