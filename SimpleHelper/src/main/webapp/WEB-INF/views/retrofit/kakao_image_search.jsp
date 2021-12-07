<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	<!-- Twitter Bootstrap3 & jQuery -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>카카오 이미지 검색</h1>

	<!-- 검색폼 -->
	<form name="form1" method="get"
		action="${pageContext.request.contextPath}/retrofit/kakao_image_search.do">
		<label for="query">검색어: </label> 
		<input type="search" id="query"name="query" value="${query}" /> 
		<input type="submit" value="검색" />
	</form>

	<hr />
	
	<!-- 검색 결과처리 -->
	<c:choose>
		<c:when test="${image != null && image.documents.size() > 0 }">
			<table border="1">
			<c:forEach var="item" items="${image.documents }" varStatus="status">
				<c:if test="${status.index % 6 == 0 }">
					<tr>
					</c:if>
					
					<td>
						<a href="${item.docUrl }">
							<img src="${item.thumbnail_url }" />
							
						</a>
					</td>
					
					<c:if test="${status.index + 1 % 6 == 0 }">
					</tr>
				</c:if>
			</c:forEach>
			
			</table>
		</c:when>
		<c:otherwise>
			<h3>검색 결과가 없습니다.</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>


