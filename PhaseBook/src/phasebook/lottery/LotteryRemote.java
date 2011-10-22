package phasebook.lottery;

import javax.ejb.Remote;

@Remote
public interface LotteryRemote {
	
	public String draw();
	public String nextDrawDate();

}
