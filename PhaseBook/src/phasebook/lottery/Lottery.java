package phasebook.lottery;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lottery implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="LOTTERY_NUMBER")
	private int lotteryNumber;
	@Column(name="LOTTERY_DATE")
	private Date lotteryDate;
	
	public Lottery()
	{
		super();
	}
	
	public Lottery(int id)
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

	protected int getLotteryNumber() {
		return lotteryNumber;
	}

	protected void setLotteryNumber(int lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
	}

	protected Date getLotteryDate() {
		return lotteryDate;
	}

	protected void setLotteryDate(Date lotteryDate) {
		this.lotteryDate = lotteryDate;
	}
	
	
}
