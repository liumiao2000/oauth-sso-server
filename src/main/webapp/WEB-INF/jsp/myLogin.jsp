<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>登陆</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	</head>
	<body>
		<div>
			<h2>登陆</h2>
			<form action="/auth/form" method="post">
				<input type="text" name="username" placeholder="请输入用户名"/>
				<input type="password" name="password" placeholder="请输入密码"/>
				<input type="text" name="captcha" placeholder="请输入验证码"/>
				<img alt="验证码" src="/captcha.jpg" width="150px" height="50px"/>
				<input type="submit" value="登陆"/>
			</form>
		</div>
	</body>
</html>