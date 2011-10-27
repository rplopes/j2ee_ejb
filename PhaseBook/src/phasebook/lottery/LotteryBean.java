package phasebook.lottery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import phasebook.external.lottery.ExternalLotteryRemote;
import phasebook.lotterybet.*;
import phasebook.user.*;

@Stateless
public class LotteryBean implements LotteryRemote {
	
	private static float MONEYWIN = 50;
	
	public static int number = -1;
	public static Calendar nextDraw = null;
	public static int timerInterval = 1000 * 60;
	private @Resource SessionContext ctx;
	  
	public void scheduleTimer(long milliseconds) {  
		ctx.getTimerService().createTimer(new Date(new Date().getTime() + milliseconds), "Hello World");  
	}
	
	public String nextDrawDate() {
		if (nextDraw == null)
			return null;
		DateFormat dateFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
		return dateFormat.format(nextDraw.getTime());
	}
	
	@Timeout
	public void timeout(Timer timer){
		try {
			InitialContext ctx = new InitialContext();
			ExternalLotteryRemote lottery = (ExternalLotteryRemote) ctx.lookup("ExternalLotteryBean/remote");
			number = lottery.getNumber();
			nextDraw = lottery.getNextDraw();
			if (number > 0)
				updateCurrentDraw(number);
			createDraw(nextDraw);
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		System.out.println("Phasebook - " + number + ". Next at " + nextDraw);
		this.scheduleTimer(timerInterval);
	}
	
	@SuppressWarnings("finally")
	public Lottery getCurrentDraw() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM Lottery u " +
				"WHERE u.lotteryNumber = -1");
		Lottery lottery = null;
		try {
			lottery = (Lottery)q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			lottery = null;
		} finally{
			em.close();
			emf.close();
			return lottery;
		}
	}
	
	private void updateCurrentDraw(int number) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Lottery lottery = getCurrentDraw();
		if (lottery != null) {
			lottery.setLotteryNumber(number);
			em.merge(lottery);
		}
		tx.commit();
		
		// Give money to winers
		LotteryBetBean betEJB = new LotteryBetBean();
		
		List<?> bets = betEJB.getAllBets();
		for (int i=0; i<bets.size(); i++) {
			LotteryBet bet = (LotteryBet)bets.get(i);
			if (bet.getBetNumber() == number){
				giveMoney(bet.getUser().getId());
				betEJB.updateBet(bet, MONEYWIN);
			}
			else {
				betEJB.updateBet(bet, 0);
			}
		}
		em.close();
		emf.close();
	}
	
	private void giveMoney(int id) {
		PhasebookUserBean userEJB = new PhasebookUserBean();
		userEJB.deposit((Object)id, MONEYWIN);
		System.out.println("User " + id + "won " + MONEYWIN);
	}
	
	private void createDraw(Calendar date) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
    	Lottery lottery = new Lottery();
    	lottery.setLotteryNumber(-1);
    	//lottery.setLotteryDate(new java.sql.Date(date.getTimeInMillis()));
		em.persist(lottery);
		tx.commit();
		
		em.close();
		emf.close();
	}

}
