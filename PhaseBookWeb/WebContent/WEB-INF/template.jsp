<%@ page import="phasebook.controller.*" %>

<%
	String title = session.getAttribute("title").toString();
	session.removeAttribute("title");
	String url = session.getAttribute("url").toString();
	session.removeAttribute("url");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="public/css/style.css" rel="stylesheet" type="text/css">
	<title>Phasebook - <%= title %></title>
</head>
<body>
	<div id="header">
		<div id="headercontainer">
			<%= Utils.a("", "Phasebook") %>
			<form action="<%= Utils.url("") %>" method="get" style="display:inline">
				<input type="text" name="search">
				<input type="submit" value="Search">
			</form>
		</div>
	</div>
	<div id="container">
		<% pageContext.include("/WEB-INF/" + url); %>
	</div>
</body>
</html>