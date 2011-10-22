package phasebook.lottery;

import java.text.*;
import java.util.Calendar;

import javax.ejb.Stateless;

@Stateless
public class LotteryBean implements LotteryRemote {
	
	public String draw() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
        Calendar draw = Calendar.getInstance();
        return "Hello from control: " + dateFormat.format(draw.getTime());
    }
	
	public String nextDrawDate() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
		Calendar nextDraw = Calendar.getInstance();
		nextDraw.add(Calendar.MINUTE, 1);
		return dateFormat.format(nextDraw.getTime());
	}

}
