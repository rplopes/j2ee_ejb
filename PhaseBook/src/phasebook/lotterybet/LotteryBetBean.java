package phasebook.lotterybet;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import phasebook.lottery.*;
import phasebook.post.Post;
import phasebook.user.*;

@Stateless
public class LotteryBetBean implements LotteryBetRemote {
	
	public boolean createBet(Object id, int number) {
		PhasebookUserBean userEJB = new PhasebookUserBean();
		PhasebookUser user = userEJB.getUserById(id);
		LotteryBean lotteryEJB = new LotteryBean();
		Lottery lottery = lotteryEJB.getCurrentDraw();
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			tx.begin();
			LotteryBet bet = new LotteryBet();
			bet.setBetNumber(number);
			bet.setBetValue(1);
			bet.setValueWon(-1);
			bet.setUser(user);
			bet.setLottery(lottery);
			em.persist(bet);
			em.refresh(bet);
			tx.commit();em.persist(bet);em.persist(bet);
			em.refresh(bet);
			em.refresh(bet);
			em.close();
			emf.close();
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public List<LotteryBet> userCurrentBets(Object id) {
		return userBets(id, false);
	}
	
	public List<LotteryBet> userOldBets(Object id) {
		return userBets(id, true);
	}
	
	private List<LotteryBet> userBets(Object id, boolean old) {
		PhasebookUserBean userEJB = new PhasebookUserBean();
		PhasebookUser user = userEJB.getUserById(id);
		List<LotteryBet> allBets = user.getLotteryBets();
		List<LotteryBet> theseBets = new ArrayList<LotteryBet>();
		for (int i=0; i<allBets.size(); i++) {
			LotteryBet bet = allBets.get(i);
			if ((old && bet.getValueWon() > -1) ||
					(!old && bet.getValueWon() == -1))
				theseBets.add(bet);
		}
		return theseBets;
	}
	
	public List getAllBets() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM LotteryBet u ");
		List bets = q.getResultList();
		em.close();
		emf.close();
		return bets;
	}
	
	public void updateBet(LotteryBet bet, float won) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		bet.setValueWon((int)won);
		em.merge(bet);
		tx.commit();
		em.close();
		emf.close();
	}
	
	public Object checkUnreadBetResults(PhasebookUser entry)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM LotteryBet u WHERE u.user = :user AND u.read_ = :readStatus AND u.valueWon > -1");
		q.setParameter("user", entry);
		q.setParameter("readStatus", false);
		List<?> bets = q.getResultList();
		em.close();
		emf.close();
		return bets;
	}
	
	public void readUnreadBets(PhasebookUser entry)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		List<?> result = null;
		
		Query q = em.createQuery("SELECT u FROM LotteryBet u WHERE u.user = :user AND u.read_ = :readStatus AND u.valueWon > -1");
		q.setParameter("user", entry);
		q.setParameter("readStatus", false);
		
		try
		{
			result=q.getResultList();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Post post;
			for(Object object : result)
			{
				post = (Post)object;
				em.merge(post);
				post.setRead_(true);
				em.merge(post);
			}
			tx.commit();
			em.close();
			emf.close();
		}
		catch(NoResultException e)
		{
			em.close();
			emf.close();
			System.out.println("<Não foram encontrados posts por ler>");
		}
	}

}
