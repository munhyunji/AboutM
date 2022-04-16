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
	<div class="diary_container">
		<h1>다이어리 보기</h1>
		<hr />
		<foreach var="item" items="${output }" varStatus="status">
			<div class="form-group">
				<label for="title">제목</label>
				
				<input type="text" class="form-control" id="title"
					placeholder="제목을 입력하세요" name="title" maxlength="100" value="$"
					required="required" >
			</div>
			<div class="form-group">
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
		
		</foreach>
	</div>
</body>

</html>