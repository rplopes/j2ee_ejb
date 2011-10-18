<%@page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>

<form method="POST" action="LoginUserForm">

<p><font color="#800000" size="5">
E-mail:</font><input type="text" name="email" size="20"></p>

<p><font color="#800000" size="5">
Password:</font><input type="password" name="password" size="20"></p>

<p><input type="submit" value="Submit" name="B1"></p>
</form>