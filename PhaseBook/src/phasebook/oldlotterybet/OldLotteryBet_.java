package phasebook.oldlotterybet;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.user.PhasebookUser;

@Generated(value="Dali", date="2011-10-18T02:05:36.262+0100")
@StaticMetamodel(OldLotteryBet.class)
public class OldLotteryBet_ {
	public static volatile SingularAttribute<OldLotteryBet, Integer> id;
	public static volatile SingularAttribute<OldLotteryBet, PhasebookUser> user;
	public static volatile SingularAttribute<OldLotteryBet, Integer> lotteryId;
	public static volatile SingularAttribute<OldLotteryBet, Float> valueWon;
	public static volatile SingularAttribute<OldLotteryBet, Float> betValue;
	public static volatile SingularAttribute<OldLotteryBet, Integer> betNumber;
	public static volatile SingularAttribute<OldLotteryBet, Date> createdAT;
}
