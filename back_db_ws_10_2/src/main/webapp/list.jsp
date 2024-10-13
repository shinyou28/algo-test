<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ssafy.ws.step2.dto.Movie" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화정보사이트</title>
<style>
	label {
		display: inline-block;
		width: 80px;
	}
	
	table{
		border-collapse: collapse;
  		width: 100%;
  		margin-bottom : 10px;
	}
	
	th, td{
		border : 1px solid black;
		padding: 6px 15px;
	}
	th {
	  	background: #42444e;
	  	color: #fff;
	 	text-align: left;
	}
	
</style>
</head>
<body>
	<header>
      <h1>영화 목록</h1>
    </header>
    <main>
      <h2>등록된 영화 정보</h2>
      <h2>지금까지 등록한 영화 수 : ${MovieCount} </h2>
      <table>
      	<thead>
	      <tr>
	      	<th>번호</th>
	        <th>제목</th>
	        <th>감독</th>
	        <th>장르</th>
	        <th>상영시간</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach items="${movies}" var="movie" varStatus="status">
	     	<tr>
	     		<td>${status.count}</td>
	     		<td>${movie.title}</td>
	     		<td>${movie.director}</td>
	     		<td>${movie.genre}</td>
	     		<td>${movie.runningTime}분</td>
	     	</tr> 
	      </c:forEach>
	    </tbody>
      </table> 
      <a href = "regist.jsp">추가등록</a>
      <br>
    </main>
</body>
</html>