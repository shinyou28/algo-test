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
  		border-spacing: 0;
  		width: 80%;
  		margin-bottom : 10px;
	}
	
	th, td{
		border : 1px solid black;
		padding: 6px 15px;
		width: 500px;
	}
	th {
	  	background: #42444e;
	  	color: #fff;
	 	text-align: left;
	}
	th.item, td.item{
		width:200px;
	} 
	
</style>
</head>
<body>
	<%-- <% Movie movie = (Movie)request.getAttribute("movie"); %> --%>
	<%-- servlet을 통해 request에 movie 객체가 존재 --%>
	<header>
      <h1>영화 등록 결과</h1>
    </header>
    <main>
      <h2>등록된 영화 정보</h2>
      <h2>지금까지 등록한 영화 수 : ${MovieCount} </h2>

      <table>
      	<thead>
	      <tr>
	        <th class="item">항목</th>
	        <th>내용</th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td class="item">제목</td>
	        <td>${movie.title}</td>
	      </tr>
	      <tr>
	        <td class="item">감독</td>
	        <td>${movie.director}</td>
	      </tr>
	      <tr>
	        <td class="item">장르</td>
	        <td>${movie.genre}</td>
	      </tr>
	      <tr>
	        <td class="item">상영시간</td>
	        <td>${movie.runningTime}</td>
	      </tr>
	    </tbody>
      </table> 
      <a href = "regist.jsp">추가등록</a>
      <%-- 파라미터로 action 전송 --%>
      <a href = "./main?action=list">영화 목록</a>
      <br>
    </main>
</body>
</html>