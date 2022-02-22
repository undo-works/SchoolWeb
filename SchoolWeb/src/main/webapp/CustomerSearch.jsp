<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報検索</title>
</head>
<body>
	<form action="/SchoolWeb/MainServlet" method="get">
		<p>電話番号(ハイフンなし)<input type="text" name="tel" /><br/>例：09012345678</p>
		<p>氏名(全角カタカナ)<input type="text" name="name" /><br/>例：佐藤 太郎</p>
		<input type="submit" value="検索"/>
		<input type="reset" value="リセット">
	</form>	
	
	<table border="1">
		<tr>
			<th>ID</th>
			<th>氏名</th>
			<th>カナ</th>
			<th>住所</th>
		</tr>
		
		<a onclick='<c:redirect url="/SchoolWeb/MainServlet"></c:redirect>'>
		<tr>
		<c:forEach items="${ list }" var="obj" varStatus="status">
			
			
				<td><c:out value="${ obj.custId }"></c:out></td>
				<td><c:out value="${ obj.custName }"></c:out></td>
				<td><c:out value="${ obj.kana }"></c:out></td>
				<td><c:out value="${ obj.address }"></c:out></td>
				
			
			
		</c:forEach>
		</tr>
		</a>
	</table>
	
</body>
</html>