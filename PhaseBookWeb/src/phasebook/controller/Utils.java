package phasebook.controller;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import phasebook.user.PhasebookUserRemote;

public class Utils {
	
	// Creates a link to a URL in HTML
	public static String a(String url, String text)
	{
		if (url.length() == 0)
			return "<a href='/PhaseBookWeb'>" + text + "</a>";
		return "<a href='?p=" + url + "'>" + text + "</a>";
	}
	
	// Prints the correct URL
	public static String url(String url)
	{
		if (url.length() == 0)
			return "";
		return "?p=" + url;
	}
	
	//Get user bean
	public static PhasebookUserRemote getUserBean(){
		InitialContext ctx = null;
		try {
			ctx = new InitialContext();
			PhasebookUserRemote user;
			user = (PhasebookUserRemote) ctx.lookup("PhasebookUserBean/remote");
			return user;
		} catch (NamingException e) {
			return null;
		}
	}

}
