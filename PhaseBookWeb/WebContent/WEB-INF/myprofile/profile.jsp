<%@page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>

<% 
	PhasebookUserRemote userBean = Utils.getUserBean();
	PhasebookUser user = userBean.getUserById(session.getAttribute("id"));
%>

<h1><%= user.getName() %></h1>

<p class="tip"><%= user.getEmail() %></p>