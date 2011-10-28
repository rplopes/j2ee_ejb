package phasebook.friendship;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.user.PhasebookUser;

@Generated(value="Dali", date="2011-10-28T07:06:31.582+0100")
@StaticMetamodel(Friendship.class)
public class Friendship_ {
	public static volatile SingularAttribute<Friendship, Integer> id;
	public static volatile SingularAttribute<Friendship, PhasebookUser> hostUser;
	public static volatile SingularAttribute<Friendship, PhasebookUser> invitedUser;
	public static volatile SingularAttribute<Friendship, Boolean> accepted_;
	public static volatile SingularAttribute<Friendship, Timestamp> createdAt;
	public static volatile SingularAttribute<Friendship, Date> deletedAt;
	public static volatile SingularAttribute<Friendship, Boolean> read_;
}
