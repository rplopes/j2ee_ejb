package phasebook.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import phasebook.user.PhasebookUser;

public class EmailUtils {
	
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 465;
    private static final String SMTP_AUTH_USER = "phasebookmessager@gmail.com";
    private static final String SMTP_AUTH_PWD  = "grrgrrgrr";
	public static String MAIN_PATH   = "http://localhost:8080/photos/";
	public static int IMG_DEFAULT_WIDTH = 300; 
	
	public static void notifyUser(PhasebookUser user, String subject, String body){
		Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");
        // props.put("mail.smtps.quitwait", "false");

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        Transport transport = null;
		try {
			transport = mailSession.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        MimeMessage message = new MimeMessage(mailSession);
        try {
			message.setSubject(subject);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
    		message.setContent(body, "text/html");
			message.addRecipient(Message.RecipientType.TO,
		             new InternetAddress(user.getEmail()));
			transport.connect
	          (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

	        transport.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
	        transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String img(String url, int id)
	{
		return "<img src=\""+MAIN_PATH+"/"+id+"/"+url+"\" width=\""+IMG_DEFAULT_WIDTH+"\" />";
	}
	
	public static String a(String text)
	{
		return "<a href='http://localhost:8080/PhaseBookWeb'>" + text + "</a>";
	}

}
