package phasebook.lotterybet;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.lottery.Lottery;
import phasebook.user.PhasebookUser;

@Generated(value="Dali", date="2011-10-28T07:18:21.857+0100")
@StaticMetamodel(LotteryBet.class)
public class LotteryBet_ {
	public static volatile SingularAttribute<LotteryBet, Integer> id;
	public static volatile SingularAttribute<LotteryBet, PhasebookUser> user;
	public static volatile SingularAttribute<LotteryBet, Float> betValue;
	public static volatile SingularAttribute<LotteryBet, Integer> betNumber;
	public static volatile SingularAttribute<LotteryBet, Float> valueWon;
	public static volatile SingularAttribute<LotteryBet, Date> createdAT;
	public static volatile SingularAttribute<LotteryBet, Boolean> read_;
	public static volatile SingularAttribute<LotteryBet, Lottery> lottery;
}
