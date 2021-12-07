<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>여기맞니?</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/nanumfont.css" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- 로고영역-->
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">메뉴열기</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!--// 반응형메뉴버튼 -->
                <!-- 로고-->
                <a class="navbar-brand" href="#">About M.</a>
                <!--//로고-->
            </div>
            <!--// 로고영역 -->
            <!-- 메뉴 영역-->
            <div class="navbar-collapse collapse">
                <!-- 메뉴항목 -->
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#page_2">현지소개</a></li>
                    <li><a href="#page_3">취미특기</a></li>
                    <li><a href="#page_4">포폴포폴</a></li>
                     <li><a href="#page_5">나의노력</a></li>
                     <li><a href="#">마치며..</a></li>

                </ul>
            </div>
        </div>
    </div>


</body>
</html>
