<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
</style>
</head>
<body>
	<header>
      <h1>SSAFY 영화관리</h1>
    </header>
    <main>
      <c:choose>
      	<c:when test="${empty sessionScope.movieCount}">
      		<h2>지금까지 등록한 영화 수 : 0 </h2>
	  	</c:when>
	  	<c:otherwise>
	  		<h2>지금까지 등록한 영화 수 : ${sessionScope.movieCount} </h2>
	  	</c:otherwise>
	  </c:choose>
      <form action="main" accept-charset="UTF-8" method="POST">
        <fieldset>
          <legend>영화 정보 입력</legend>
          <input type="hidden" name ="action" value = "regist">
          제목 : <input type="text" name="title"><br>
          감독 : <input type="text" name="director"><br>
          장르 : <input type="text" name="genre"><br>
          상영 시간 : <input type="text" name="runningTime"><br>
          <input type="submit" value="등록">
          <input type="reset" value="초기화">
        </fieldset>
      </form>
    </main>
</body>
</html>