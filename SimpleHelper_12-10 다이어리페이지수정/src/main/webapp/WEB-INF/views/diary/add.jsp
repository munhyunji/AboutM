<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html>
 <%@ include file="../../include/header.jsp" %>

<body>
 <%@ include file="/WEB-INF/include/navbar.jsp" %>
	<div class="container">
		<div class="diary_page">
		<h1>다이어리 추가</h1>
		<hr />
		<form action="${pageContext.request.contextPath }/diary/add_ok.do" method="post">
			<div class="form-group">
				<label for="title">제목</label>
				<!-- placeholder 속성 입력한 데이터가 없는 경우 배경으로 나타난다.실제적으로 입력을 100자까지로 지정 -->
				<!-- required 속성을 설정하면 필수입력 사항이된다. -->
				<input type="text" class="form-control" id="title"
					placeholder="제목을 입력하세요" name="title" maxlength="100"
					required="required" >
			</div>
			<div class="form-group">
				<jsp:useBean id="now" class="java.util.Date" />
				<fmt:formatDate value="${now }" pattern="yyyy-MM-dd" var="date" />
				<label for="content">작성일자 : ${now }</label>
				<input type="date" name="date" id="date">
				
			</div>
			<div class="form-group">
				
				<label for="content">작성자 : 문현지!</label>
				
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				
				<textarea class="form-control" rows="20" id="content" name="content"
					placeholder="내용을 작성하세요"></textarea>
			</div>
			
			<button type="submit" class="btn btn-default">등록</button>
			<a class="btn btn-info"
				href="${pageContext.request.contextPath }/diary/list.do">취소</a>
		</form>
	

		</div>
	</div>
</body>

</html>