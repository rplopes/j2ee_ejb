<%@page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>

<%
	String name = "";
	String email = "";
	if (session.getAttribute("name") != null)
	{
		name = session.getAttribute("name").toString();
		session.removeAttribute("name");
	}
	if (session.getAttribute("email") != null)
	{
		email = session.getAttribute("email").toString();
		session.removeAttribute("email");
	}
	
	if (session.getAttribute("error") != null)
	{
%>
		<p style="color:red"><%= session.getAttribute("error") %></p>
<%
		session.removeAttribute("error");
	}
%>

<form method="POST" action="CreateUserForm">
<p><font color="#800000" size="5">
Name:</font><input type="text" name="name" value="<%= name %>" size="20"></p>

<p><font color="#800000" size="5">
E-mail:</font><input type="text" name="email" value="<%= email %>" size="20"></p>

<p><font color="#800000" size="5">
Password:</font><input type="password" name="password1" size="20"></p>

<p><font color="#800000" size="5">
Repeat password:</font><input type="password" name="password2" size="20"></p>

<p><input type="submit" value="Submit" name="B1"></p>
</form>