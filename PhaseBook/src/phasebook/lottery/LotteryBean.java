package phasebook.lottery;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import phasebook.external.lottery.ExternalLotteryRemote;

@Stateless
public class LotteryBean implements LotteryRemote {
	
	public static int number = -1;
	public static String nextDraw = "";
	public static int timerInterval = 1000 * 60;
	private @Resource SessionContext ctx;
	  
	public void scheduleTimer(long milliseconds) {  
		ctx.getTimerService().createTimer(new Date(new Date().getTime() + milliseconds), "Hello World");  
	}
	
	public String nextDrawDate() {
		return nextDraw;
	}
	
	@Timeout
	public void timeout(Timer timer){
		try {
			InitialContext ctx = new InitialContext();
			ExternalLotteryRemote lottery = (ExternalLotteryRemote) ctx.lookup("ExternalLotteryBean/remote");
			number = lottery.getNumber();
			nextDraw = lottery.getNextDraw();
			if (number > 0)
				createDraw(number, new Date());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		System.out.println("Phasebook - " + number + ". Next at " + nextDraw);
		this.scheduleTimer(timerInterval);
	}
	
	private void createDraw(int number, Date date) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
    	Lottery lottery = new Lottery();
    	lottery.setLotteryNumber(number);
    	lottery.setLotteryDate(new java.sql.Date(date.getTime()));
		em.persist(lottery);
		tx.commit();
	}

}
