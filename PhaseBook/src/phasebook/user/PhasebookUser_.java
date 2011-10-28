package phasebook.user;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.friendship.Friendship;
import phasebook.lotterybet.LotteryBet;
import phasebook.photo.Photo;
import phasebook.post.Post;

@Generated(value="Dali", date="2011-10-28T00:41:43.274+0100")
@StaticMetamodel(PhasebookUser.class)
public class PhasebookUser_ {
	public static volatile SingularAttribute<PhasebookUser, Integer> id;
	public static volatile SingularAttribute<PhasebookUser, String> name;
	public static volatile SingularAttribute<PhasebookUser, String> email;
	public static volatile SingularAttribute<PhasebookUser, String> password;
	public static volatile SingularAttribute<PhasebookUser, Float> money;
	public static volatile SingularAttribute<PhasebookUser, Timestamp> createdAt;
	public static volatile SingularAttribute<PhasebookUser, Date> deletedAt;
	public static volatile SingularAttribute<PhasebookUser, Photo> photo;
	public static volatile ListAttribute<PhasebookUser, Friendship> sentInvites;
	public static volatile ListAttribute<PhasebookUser, Friendship> receivedInvites;
	public static volatile ListAttribute<PhasebookUser, LotteryBet> lotteryBets;
	public static volatile ListAttribute<PhasebookUser, Post> sentPosts;
	public static volatile ListAttribute<PhasebookUser, Post> receivedPosts;
}
