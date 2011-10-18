package phasebook.controller;

public class Utils {
	
	// Creates a link to a URL in HTML
	public static String a(String url, String text)
	{
		return "<a href='?p=" + url + "'>" + text + "</a>";
	}
	
	// Prints the correct URL
	public static String url(String url)
	{
		return "?p=" + url;
	}

}
