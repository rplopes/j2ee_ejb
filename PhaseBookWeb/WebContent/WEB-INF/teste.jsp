<%@page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>

<%
	InitialContext ctx = new InitialContext();
	PhasebookUserRemote user = (PhasebookUserRemote) ctx.lookup("PhasebookUserBean/remote");
	String name = user.showName("User", "123@mail.com", "pass");
%>
	
<b>Name: </b><%= name %>
<%= Utils.a("ola", "Olá") %>