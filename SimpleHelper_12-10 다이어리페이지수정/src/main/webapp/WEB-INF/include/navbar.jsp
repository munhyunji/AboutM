<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="navbar navbar-fixed-top" role="navigation">
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
                <ul class="nav navbar-nav" align="right">
                     <li><a href="#">Home</a></li>
                     <li><a href="#page_2">자신소개</a></li>
                     <li><a href="${pageContext.request.contextPath }/diary/list.do">Diary</a></li>
                     <li><a href="${pageContext.request.contextPath}/movie/list.do">영화기록장</a></li>
                     <li><a href="#page_5">마치며..</a></li>
                </ul>
            </div>
        </div>
    </div>