package phasebook.lotterybet;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import phasebook.lottery.Lottery;
import phasebook.user.PhasebookUser;


@Entity
public class LotteryBet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BET_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="PHASEBOOK_USER_ID", referencedColumnName="PHASEBOOK_USER_ID")
	private PhasebookUser user;
	
	@Column(name="BET_VALUE")
	private float betValue;
	
	@Column(name="BET_NUMBER")
	private int betNumber;
	
	@Column(name="CREATED_AT")
	private Date createdAT;
	
	@ManyToOne
	@JoinColumn(name="LOTTERY_ID", referencedColumnName="LOTTERY_ID")
	private Lottery lottery;
	
	public LotteryBet()
	{
		super();
	}
	
	public LotteryBet(int id)
	{
		super();
		this.id=id;
	}

	protected int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	protected PhasebookUser getUser() {
		return user;
	}

	protected void setUser(PhasebookUser user) {
		this.user = user;
	}

	protected float getBetValue() {
		return betValue;
	}

	protected void setBetValue(float betValue) {
		this.betValue = betValue;
	}

	public int getBetNumber() {
		return betNumber;
	}

	protected void setBetNumber(int betNumber) {
		this.betNumber = betNumber;
	}

	protected Date getCreatedAT() {
		return createdAT;
	}

	protected void setCreatedAT(Date createdAT) {
		this.createdAT = createdAT;
	}

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}
	
	
}
