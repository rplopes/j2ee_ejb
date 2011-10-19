<%@page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>

<h1>Login</h1>

<form method="POST" action="LoginUserForm">

	<table>
		<tr>
			<td>Email</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Login" name="B1"></td>
		</tr>
	</table>
	
</form>

<p class="tip">
	Don't have an account yet? <%= Utils.a("register", "Register") %> for free
</p>