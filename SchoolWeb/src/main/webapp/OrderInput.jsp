<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.script.ScriptEngine" %>
<%@ page import="javax.script.ScriptEngineManager" %>
<%@ page import="javax.script.ScriptException" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>【注文/配達確認/顧客情報変更】</title>
<script>
/*function register(){
	var result = window.confirm('注文情報を登録します。よろしいですか？');
	if( result ) {
		//window.location.href='/SchoolWeb/MainServlet';
		xhr.open('POST', '/SchoolWeb/MainServlet', true);
		xhr.send();
	}else {
		console.log('キャンセルがクリックされました');
	}
}*/
</script>
</head>
<body>
	<p>ID &nbsp; <c:out value="${customer.custId}"></c:out></p>
	<p>氏名<input type="text" value="${customer.custName}"/></p>
	<p>カナ<input type="text" value="${customer.kana}"/></p>
	<p>電話番号<input type="text" value="${customer.tel}"/></p>
	<p>住所<input type="text" value="${customer.address}" size="50px"/></p>	
	<hr/>
	<form>
		<input type="hidden" name="custId" value="${customer.custId}"/>
		<input type="hidden" name="item" value="item"/>
		<button name="flag" value="06">注文手続</button>
		<button name="flag" value="08">配達確認</button>
		<button name="flag" value="09">顧客情報変更</button>
	</form>
	<hr/>
	
<% 
	/*
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine se = sem.getEngineByName("JavaScript");
    String script ="var result = window.confirm('注文情報を登録します。よろしいですか？');";
    String script2 = "if( result ) {window.location.href='/SchoolWeb/MainServlet';}";
	String script3 = "else {console.log('キャンセルがクリックされました');}";
    try {
        se.eval(script+script2+script3);
    } catch (ScriptException e) {
        e.printStackTrace();
    }
    */
%>	
	<form name="myform" action="/SchoolWeb/MainServlet" method="post">
		<input type="hidden" name="custId" value="">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>商品名</th>
				<th>サイズ</th>
				<th>数量</th>
				<th>単価(円：税込)</th>
			</tr>
	
			<c:forEach items="${ itemList }" var="obj" varStatus="status">
				<tr>
					<td><c:out value="${ obj.itemId }"></c:out></td>
					<td><c:out value="${ obj.itemName }"></c:out></td>
					<td><c:out value="${ obj.size }"></c:out></td>
					<td><input type="text" name="quantity${ status.index }">
					<fmt:formatNumber var="rate" value="${obj.price*(1+tax.rate)}" maxFractionDigits="0"></fmt:formatNumber></td> 
					<td><c:out value="${ rate }"></c:out></td>
				</tr>	
			</c:forEach>	
		</table>
		<hr/>
		<input type="text" name="year" size="10px"/>年
		<input type="text" name="month" size="10px"/>月
		<input type="text" name="date" size="10px"/>日
		<input type="text" name="hour" size="10px"/>時
		<input type="text" name="minute" size="10px"/>分に配達
		<hr/>
		<button name="flag" value="10" id="btn">注文登録</button>
		<a href="/DeliveryConfirm.jsp"><button name="flag" value="01">戻る</button></a>
	</form>
	
</body>
</html>