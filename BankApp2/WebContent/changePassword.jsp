<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="header.jsp" %>

</head>
<body>
<br>
	<h1>Change Password</h1>
	<form action="changePassword.do" method="post">
		<br><br>
		<label>Old Password: </label>
		<input type="text" name="customerOldPassword" placeholder="enter your Old Password" required/>
		<br> <br>
		<label>New Password: </label>
		<input type="password" name="customerNewPassword" placeholder="enter your New Password"required/>
		<br> <br>
		<input type="submit" value="update"/>
	</form>
</body>
</html>