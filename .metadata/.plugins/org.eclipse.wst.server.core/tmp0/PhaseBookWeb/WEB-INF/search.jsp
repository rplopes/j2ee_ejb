<%@ page import="phasebook.controller.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="java.util.List" %>
<%@ page import="phasebook.user.*" %>

<% 
	PhasebookUserRemote userBean = Utils.getUserBean();
	List users = userBean.getUsersFromSearch(request.getParameter("search"));
%>

<h1>Search users for "<%= Utils.text(request.getParameter("search")) %>"</h1>

<%
	if (users.size() == 0)
	{
%>
		<p>No users were found.</p>
<%
	}
	else for (int i=0; i<users.size(); i++)
	{
		PhasebookUser user = (PhasebookUser) users.get(i);
%>
		<p>
			<b><%= Utils.a("user&id="+user.getId(), Utils.text(user.getName())) %></b><br />
			<i><%= Utils.text(user.getEmail()) %></i>
		</p>
<%
	}
%>