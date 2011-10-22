<%@page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>

<% 
	PhasebookUserRemote userBean = Utils.getUserBean();
	PhasebookUser user;
	if (request.getParameter("id") == null)
		user = userBean.getUserById(session.getAttribute("id"));
	else
	{
		try {
			Utils.getUserBean().getUserById(request.getParameter("id")).getName();
			user = userBean.getUserById(request.getParameter("id"));
		} catch (Exception e) {
			user = userBean.getUserById(session.getAttribute("id"));
		}
	}
%>

<h1><%= Utils.text(user.getName()) %></h1>

<p class="tip"><%= Utils.text(user.getEmail()) %></p>

<form method="POST" action="CreatePostForm">
	<table>
		<tr>
			<td><textarea name="text" COLS=80 ROWS=6></TEXTAREA></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Send" name="B1"></td>
		</tr>
	</table>
</form>