<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
		<h2>게시글 목록</h2>
		<!-- 로그인 X (로그인 페이지) 서울10반 지유림 good -->
		<c:if test="${empty loginUser}">
			<div class="d-flex justify-content-end">
				<form action="/login" method="POST">
					<input type="text" name="id" placeholder="아이디">
					<input type="password" name="pw" placeholder="비밀번호">
					<button class="btn btn-outline-primary">로그인</button>
				</form>
			</div>
		</c:if>
		<!-- 로그인 O (로그아웃) -->
		<c:if test="${not empty loginUser}">
			<span>${loginUser}님 반갑습니다.</span>
			<a href="/logout">로그아웃</a>
		</c:if>
		<hr>
		<%@ include file="../common/searchForm.jsp" %>
		<hr>
		<table class="table text-center">
			<tr class="table-light">
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>날짜</th>
			</tr>
			<c:forEach items="${boards}" var="board">
				<tr>
					<td>${board.id}</td>
					<td><a href="detail?id=${board.id}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.viewCnt}</td>
					<td>${board.regDate}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="d-flex justify-content-end">
			<a class="btn btn-outline-primary" href="/writeform">글 작성</a>
		</div>
	</div>
</body>
</html>