package phasebook.lotterybet;

import javax.ejb.Remote;

@Remote
public interface LotteryBetRemote {
	
	public void createBet(Object id, int number);

}
