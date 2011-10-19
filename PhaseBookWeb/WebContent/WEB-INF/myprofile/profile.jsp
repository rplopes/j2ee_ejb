<%@page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>

<% 
	PhasebookUserRemote userBean = Utils.getUserBean();
	PhasebookUser user = userBean.getUserById(session.getAttribute("id"));
%>

<p><font color="#800000" size="5">
Name:</font></p> <%= user.getName() %>

<p><font color="#800000" size="5">
E-mail:</font></p> <%= user.getEmail() %>
