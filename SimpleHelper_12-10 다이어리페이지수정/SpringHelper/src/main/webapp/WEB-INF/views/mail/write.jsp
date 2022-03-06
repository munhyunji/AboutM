<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<h1>Java 메일 발송 연습</h1>

	<form method="post" action="${pageContext.request.contextPath }/mail/send.do">
	<div>
		<label for="to">수신주소: </label>
		<input type="email" name="to" id="to"/>
	</div>
	<div>
		<label for="subject">메일제목: </label>
		<input type="text" name="subject" id="subject"/>
	</div>
	<hr />
	<textarea name ="content" class="ckeditor"></textarea>
	<hr />
	<input type="submit" value="보내기"/>
	</form>


	<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
</body>
</html>


