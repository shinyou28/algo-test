<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/bootstrap.jsp" %>
</head>
<body>
	<div class="container">
		<hr>
		<h2>게시판 등록</h2>
		<hr>
		<form action="write" method="POST">
			<div class="mb-3">
				<label for="title" class="form-label">글제목</label>
				<input type="text" class="form-control" id="title" name="title">
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">글쓰니</label>
				<input type="text" class="form-control" id="writer" name="writer">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">글내용</label>
				<textarea class="form-control"  rows="10" cols="10" id="content" name="content"></textarea>
			</div>
			
			<button class="btn btn-outline-primary">등록</button>
		</form>
	</div>
</body>
</html>