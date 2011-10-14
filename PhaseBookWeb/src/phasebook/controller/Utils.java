package phasebook.controller;

public class Utils {
	
	// Para facilitar, caso mais tarde se queira mudar a estrutura dos urls
	public static String a(String url, String text)
	{
		return "<a href='?p=" + url + "'>" + text + "</a>";
	}

}
