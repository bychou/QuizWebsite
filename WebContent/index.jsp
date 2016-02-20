<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
<h1>Welcome to Quiz Website</h1>
<p>Please log in. </p>
<form action="LoginServlet" method="post">
<p>User Name:<input type="text" name="username"></p>
<p>Password:<input type="text" name="password"><input type="submit"></p>
<a href="CreateAccount.html">Create New Account</a>
</form>
</body>
</html>
