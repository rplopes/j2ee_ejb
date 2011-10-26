package phasebook.lottery;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.lotterybet.LotteryBet;

@Generated(value="Dali", date="2011-10-26T16:36:48.035+0100")
@StaticMetamodel(Lottery.class)
public class Lottery_ {
	public static volatile SingularAttribute<Lottery, Integer> id;
	public static volatile SingularAttribute<Lottery, Integer> lotteryNumber;
	public static volatile SingularAttribute<Lottery, Timestamp> lotteryDate;
	public static volatile ListAttribute<Lottery, LotteryBet> lotteryBets;
}
