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

<h1><%= user.getName() %></h1>

<p class="tip"><%= user.getEmail() %></p>