package phasebook.external.lottery;

import javax.ejb.Remote;

@Remote
public interface ExternalLotteryRemote {
	
	public void scheduleTimer(long milliseconds);
	public int getNumber();
	public String getNextDraw();
	public int getTimerInterval();

}