package phasebook.lottery;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import phasebook.external.lottery.ExternalLotteryRemote;

@Stateless
public class LotteryBean implements LotteryRemote {
	
	public static int number = -1;
	public static String nextDraw = "";
	public static int timerInterval = 1000 * 60;
	private @Resource SessionContext ctx;
	  
	public void scheduleTimer(long milliseconds) {  
		ctx.getTimerService().createTimer(new Date(new Date().getTime() + milliseconds), "Hello World");  
	}
	
	public String nextDrawDate() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
		Calendar nextDraw = Calendar.getInstance();
		nextDraw.add(Calendar.MINUTE, 1);
		return dateFormat.format(nextDraw.getTime());
	}
	
	@Timeout
	public void timeout(Timer timer){
		try {
			InitialContext ctx = new InitialContext();
			ExternalLotteryRemote lottery = (ExternalLotteryRemote) ctx.lookup("ExternalLotteryBean/remote");
			number = lottery.getNumber();
			nextDraw = lottery.getNextDraw();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		System.out.println("Phasebook - " + number + ". Next at " + nextDraw);
		this.scheduleTimer(timerInterval);
	}

}
