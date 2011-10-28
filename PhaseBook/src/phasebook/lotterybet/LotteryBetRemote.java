package phasebook.lotterybet;

import java.util.List;

import javax.ejb.Remote;

import phasebook.user.PhasebookUser;

@Remote
public interface LotteryBetRemote {
	
	public boolean createBet(Object id, int number);
	public List<LotteryBet> userCurrentBets(Object id);
	public List<LotteryBet> userOldBets(Object id);
	public Object checkUnreadBetResults(PhasebookUser entry);
	public void readUnreadBets(PhasebookUser entry);
}
