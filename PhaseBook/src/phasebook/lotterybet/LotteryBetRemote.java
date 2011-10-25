package phasebook.lotterybet;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LotteryBetRemote {
	
	public void createBet(Object id, int number);
	public List<LotteryBet> userBets(Object id);

}
