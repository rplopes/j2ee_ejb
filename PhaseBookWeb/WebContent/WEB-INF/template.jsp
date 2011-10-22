<%@ page import="phasebook.controller.*" %>
<%@ page import="phasebook.user.*" %>

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
	<script src="public/js/action.js"></script>
	<title>Phasebook - <%= title %></title>
</head>
<body>
	<div id="header">
		<table id="headercontainer">
			<tr>
				<td width="150">
					<%= Utils.a("", "<h1>Phasebook</h1>") %>
				</td>
				<% if (session.getAttribute("id") != null) { %>
				<td>
					<form action="<%= Utils.url("") %>" method="get" style="display:inline">
						<input type="text" name="search" value="<%= Utils.text(request.getParameter("search")) %>">
						<input type="submit" value="Search">
					</form>
				</td>
				<td id="menupopupheader" width="150" style="text-align:right; color:white" onmouseover="showPopup()" onmouseout="hidePopup()">
					<%= Utils.text(Utils.getUserBean().getUserById(session.getAttribute("id")).getName()) %>
					<div id="menupopup">
						<ul>
							<li>
								<%= Utils.a("", "My profile") %>
							<li>
								<%= Utils.a("logout", "Logout") %>
							</li>
						</ul>
					</div>
					<script>
						document.getElementById("menupopup").style.display = 'none';
					</script>
				</td>
				<% } %>
			</tr>
		</table>
	</div>
	<div id="container">
		<% pageContext.include("/WEB-INF/" + url); %>
	</div>
</body>
</html>