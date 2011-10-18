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

	session.setAttribute("title", title);
	session.setAttribute("url", url);
	pageContext.include("/WEB-INF/template.jsp");

%>