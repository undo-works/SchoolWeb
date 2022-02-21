<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>id:<c:out value="${ id }"/></p>
	<form action="/SchoolWeb/MainServlet" method="get">
		Postで送信
		<br /> 
		<input type="text" name="aaa" />
		<br />
		<input type="text" name="bbb" />
		<br />
		<input type="submit" value="実行" />
	</form>
</body>
</html>