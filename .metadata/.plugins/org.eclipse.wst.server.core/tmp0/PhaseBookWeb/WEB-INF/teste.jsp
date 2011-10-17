<%@page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.student.*" %>

<%
	InitialContext ctx = new InitialContext();
	StudentRemote student = (StudentRemote) ctx.lookup("StudentBean/remote");
	String name = student.showName("Estudante", "123");
%>
	
<b>Name: </b><%= name %>
<%= Utils.a("ola", "Olá") %>