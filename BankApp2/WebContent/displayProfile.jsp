<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="header.jsp" %>  
</head>
<body bgcolor="CornFlowerBlue" style="text-align:left">
	<br>
	<br>
	
	<h1>Customer Profile Information</h1>
	<br>
	<h4>Customer Id: ${customer.customerId} </h4>
    <h4>Customer Name:  ${customer.customerName} </h4>
	<h4>Customer Password:  ${customer.customerPassword}</h4>
	<h4>Customer Address: ${customer.customerAddress}</h4>
	<h4>Customer Date of Birth: ${customer.customerDateOfBirth}</h4>
	<h4>Customer Account Id: ${customer.customerAccount.accountId}</h4>
	<a href="editProfile.jsp"> Edit Profile</a>
</body>
</html>