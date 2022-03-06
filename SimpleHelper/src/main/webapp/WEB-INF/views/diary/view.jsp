<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>

<head>
   <%@ include file="../../include/header.jsp" %>
</head>

<body>
    	<h1>다이어리</h1>
	<p>글번호: ${output.id }</p>
	<p>제목: ${output.title }</p>
	<p>작성일자: ${output.date }</p>
	<p>작성자: ${output.writer }</p>
	<p>글내용: ${output.content }</p>
	
	<hr/>
	<a href="${pageContext.request.contextPath }/diary/list.do">목록보기</a>
	<a href="${pageContext.request.contextPath }/diary/add.do">교수추가</a>
	<a href="${pageContext.request.contextPath }/diary/edit.do?id=${output.id}">[교수수정]</a>
	<a href="${pageContext.request.contextPath }/diary/delete_ok.do?id=${output.id}">[교수삭제]</a>
</body>

</html>