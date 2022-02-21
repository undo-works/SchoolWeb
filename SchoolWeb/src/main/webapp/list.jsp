<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
	<table>
		<tr>
			<th>顧客ID</th>
			<th>顧客名</th>
			<th>カナ</th>
			<th>電話番号</th>
			<th>住所</th>
		</tr>
	</table>
	
	<c:forEach items="${list}" var="cust">
		<tr>
			<td><c:out value="${cust.custId}"></c:out></td>
			<td><c:out value="${cust.custName}"></c:out></td>
			<td><c:out value="${cust.kana}"></c:out></td>
			<td><c:out value="${cust.tel}"></c:out></td>
			<td><c:out value="${cust.address}"></c:out></td>
	</c:forEach>
</body>
</html>