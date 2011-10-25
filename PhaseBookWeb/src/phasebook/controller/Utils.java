package phasebook.controller;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import phasebook.friendship.FriendshipRemote;
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
	public static PhasebookUserRemote getUserBean()
	{
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
	
	//Get friendship bean
	public static FriendshipRemote getFriendshipBean()
	{
		InitialContext ctx = null;
		try {
			ctx = new InitialContext();
			FriendshipRemote friendship;
			friendship = (FriendshipRemote) ctx.lookup("FriendshipBean/remote");
			return friendship;
		} catch (NamingException e) {
			return null;
		}
	}
	
	//Get escaped text
	public static String text(String text)
	{
		if (text == null)
			return "";
		
		StringBuffer sb = new StringBuffer();
		int n = text.length();
		for (int i=0; i<n; i++)
		{
			char c = text.charAt(i);
			switch (c)
			{
				case '<': sb.append("&lt;"); break;
				case '>': sb.append("&gt;"); break;
				case '&': sb.append("&amp;"); break;
				case '"': sb.append("&quot;"); break;
				default: sb.append(c); break;
			}
		}
		
		return sb.toString();
	}

}
