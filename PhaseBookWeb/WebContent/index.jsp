<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="phasebook.controller.*"%>

<%
	
	/*
	*	Controller do web tier
	*	Todas as páginas passam por aqui primeiro. O controller analiza o URL
	*	e carrega os elementos da página de acordo.
	*/
	
	String title = "Login";
	String url   = "login.jsp";
	String p     = request.getParameter("p");
	if (p == null)
		p = "";
	
	if (p.compareTo("register") == 0 && session.getAttribute("id") == null)
	{
		title = "Register";
		url   = "register.jsp";
	}
	
	else if (p.compareTo("login") == 0 && session.getAttribute("id") == null)
	{
		title = "Login";
		url   = "login.jsp";
	}

	// Default
	else
	{
		if(session.getAttribute("id") != null){
			title = Utils.getUserBean().getUserById(session.getAttribute("id")).getName();
			url   = "myprofile/profile.jsp";
		}
		else
		{
			title = "Login";
			url   = "login.jsp";
		}
	}

	session.setAttribute("title", title);
	session.setAttribute("url", url);
	pageContext.include("/WEB-INF/template.jsp");

%>
