package phasebook.lotterybet;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import phasebook.lottery.*;
import phasebook.user.*;

@Stateless
public class LotteryBetBean implements LotteryBetRemote {
	
	public void createBet(Object id, int number) {
		PhasebookUserBean userEJB = new PhasebookUserBean();
		PhasebookUser user = userEJB.getUserById(id);
		LotteryBean lotteryEJB = new LotteryBean();
		Lottery lottery = lotteryEJB.getCurrentDraw();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		LotteryBet bet = new LotteryBet();
		bet.setBetNumber(number);
		bet.setBetValue(1);
		bet.setUser(user);
		bet.setLottery(lottery);
		em.persist(bet);
		em.refresh(bet);
		tx.commit();
	}
	
	public List<LotteryBet> userBets(Object id) {
		PhasebookUserBean userEJB = new PhasebookUserBean();
		PhasebookUser user = userEJB.getUserById(id);
		return user.getLotteryBets();
	}

}
