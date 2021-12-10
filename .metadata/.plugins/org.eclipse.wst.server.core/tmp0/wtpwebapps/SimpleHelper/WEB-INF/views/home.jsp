<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/nanumfont.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/main.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/navbar.css" />
</head>
<body>
	<%@ include file="../include/navbar.jsp"%>
    <div class="container">
        <div class="page_1" id="page_1">
        <div class="main_introduce">
             <img src="${pageContext.request.contextPath}/assets/img/beans.png" class="main_beans">
            <img src="${pageContext.request.contextPath}/assets/img/jack.png" class="main_img">

            

            <h2>All about her.</h2>
            <p class="main_sub">
                기존의 웹 표준 기술 위에 새로워진 HTML5와 CSS3 요소를 더하여 인터렉티브 한 반응형 웹페이지 기술을 제작할 수 있는 웹 퍼블리싱 기법들을 소개하고, CSS3의 코드를 편리하게 작성할 수 있도록 도와주는 LESS를 기반으로 한
            </p>
			<img src="${pageContext.request.contextPath}/assets/img/beans2.png" class="main_beans2">
        </div>

    </div>
    <div class="page_2" id="page_2">
          <div class="row">
            <div class="col-md-1">
            </div>
            <div class="col-md-4">
                <h2>나는 누구인가?</h2>
                <p>
                    이름: 문현지 <br>
                    주소: 서울 강남구 역삼동 (본가 : 전라남도 광주)<br>
                    메일: mhg0904@naver.com<br>
                    연락처 : 010-8519-9617<br>
                    TMI : 현재 역삼동에서 자취, 곱창전골을 좋아함, 영화오타쿠
                </p>

            </div>
            <div class="col-md-1">
            </div>
            <div class="col-md-5">
                <h2>하고싶은 말</h2>
                <p>
					저는 만들고, 그리는 것을 좋아했던 아이였습니다.
고등학생때는 그림부에 들어가서 방과후에 그림을 그렸습니다.   내가 원하는 걸 만들 때의 성취감과 자극이 너무나도 행복했었고 그때의 경험을 살려서 코딩하며 원하는 프로그램을 만들고 개발하고 배포하는 기쁨에 빠지고싶습니다.
                </p>

            </div>
            <div class="col-md-1">
            </div>
        </div>
    </div>
    <div class="page_3" id="page_3">
       <div class="page3_introduce">
            <img src="assets/img/mind.jpg" class="main_img img-rounded">
            <h4 style="margin-left: 150px;">혹시 "---" 아세요?</h4>
            <p class="main_sub">
              
            </p>

        </div>
    </div>
    <div class="page_4" id="page_4">
      <h4 align="center">무슨내용을 넣을까.</h4>
        <p>
          
        </p>
    </div>
    <div class="page_5" id="page_5">
      <h4 style="margin-left: 150px;">이제는 개발자가 되고싶습니다.</h4>
       <p>
            5페이지입니다
        </p>
    </div>
    </div>


	<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/handlebars-v4.0.11.js"></script>
	
	

</body>
</html>
