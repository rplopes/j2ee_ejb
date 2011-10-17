package phasebook.lottery;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-10-17T22:46:23.992+0100")
@StaticMetamodel(Lottery.class)
public class Lottery_ {
	public static volatile SingularAttribute<Lottery, Integer> id;
	public static volatile SingularAttribute<Lottery, Integer> lotteryNumber;
	public static volatile SingularAttribute<Lottery, Date> lotteryDate;
}
