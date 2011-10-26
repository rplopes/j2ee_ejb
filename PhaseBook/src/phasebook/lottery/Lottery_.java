package phasebook.lottery;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.lotterybet.LotteryBet;

@Generated(value="Dali", date="2011-10-26T16:35:52.017+0100")
@StaticMetamodel(Lottery.class)
public class Lottery_ {
	public static volatile SingularAttribute<Lottery, Integer> id;
	public static volatile SingularAttribute<Lottery, Integer> lotteryNumber;
	public static volatile SingularAttribute<Lottery, Date> lotteryDate;
	public static volatile ListAttribute<Lottery, LotteryBet> lotteryBets;
}
