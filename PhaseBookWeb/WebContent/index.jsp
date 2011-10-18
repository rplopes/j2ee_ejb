<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	/*
	*	Controller do web tier
	*	Todas as páginas passam por aqui primeiro. O controller analiza o URL
	*	e carrega os elementos da página de acordo.
	*/
	
	String title = "PhaseBook";
	String url   = "teste.jsp";
	String p     = request.getParameter("p");
	if (p == null)
		p = "";
	
	if (p.compareTo("ola") == 0)
	{
		title = "Olá";
		url   = "ola.jsp";
	}
	
	else if (p.compareTo("register") == 0)
	{
		title = "Register page";
		url   = "register.jsp";
	}
	// Default returns the index page
	else
	{
		title = "Teste";
		url   = "teste.jsp";
	}

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= title %></title>
</head>
<body>
	<% pageContext.include("/WEB-INF/" + url); %>
</body>
</html>