<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.student.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bom dia</title>
</head>
<body>
	<%
	InitialContext ctx = new InitialContext();
	StudentRemote student = (StudentRemote) ctx.lookup("StudentBean/remote");
	String name = student.showName("Estudante", "123");
	%>
	
	<b>Name: </b><%= name %>
</body>
</html>