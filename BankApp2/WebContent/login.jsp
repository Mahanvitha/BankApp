<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="header.jsp" %>
</head>
<body style="text-align:left">
<br>
	<h1>Login</h1>
	<form action="login.do" method="post">
		<br><br>
		<label>Email Id: </label>
		<input type="text" name="customerEmail" placeholder="enter your emailId" required/>
		<br> <br>
		<label>Password: </label>
		<input type="password" name="customerPassword" placeholder="enter your password"required/>
		<br> <br>
		<input type="submit" value="Login"/>
	</form>
</body>
</html>