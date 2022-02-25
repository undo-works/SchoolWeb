<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー画面</title>
</head>
<body>
	<h2>メインメニュー</h2>
	<br /> 
	<form action="/SchoolWeb/MainServlet" method="get">
		<button name="flag" value="01">01 注文管理</button>
		<br />
		<button name="flag" value="02">02 注文一覧</button>
		<br />
		<button name="flag" value="03">03 新規顧客登録</button>
		<br />
		<button name="flag" value="04">04 商品情報登録・変更</button>
		<br />
		<button name="flag" value="05">05 CSVファイル出力</button>
	</form>

</body>
</html>