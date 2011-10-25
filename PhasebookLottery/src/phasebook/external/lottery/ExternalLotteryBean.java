package phasebook.external.lottery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;

@Stateless
public class ExternalLotteryBean implements ExternalLotteryRemote {

	private static Calendar nextDraw;
	private static int number;
	private static int timerInterval = 1000 * 60;
	
	private @Resource SessionContext ctx;
	  
	public void scheduleTimer(long milliseconds) {  
		ctx.getTimerService().createTimer(new Date(new Date().getTime() + milliseconds), "Hello World");  
	}
	
	@Timeout
	public void timeout(Timer timer){
		/*DateFormat dateFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
		Calendar draw = Calendar.getInstance();
		draw.add(Calendar.MINUTE, 1);
		nextDraw = dateFormat.format(draw.getTime());*/
		nextDraw = Calendar.getInstance();
		Random r = new Random();
		number = r.nextInt(100) + 1;
		System.out.println("External Lottery: number " + number);
		this.scheduleTimer(timerInterval);
	}
	
	public int getNumber() {
		return number;
	}
	
	public Calendar getNextDraw() {
		return nextDraw;
	}
	
	public int getTimerInterval() {
		return this.timerInterval;
	}

}
