package phasebook.post;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.photo.Photo;
import phasebook.user.PhasebookUser;

@Generated(value="Dali", date="2011-10-27T14:58:16.341+0100")
@StaticMetamodel(Post.class)
public class Post_ {
	public static volatile SingularAttribute<Post, Integer> id;
	public static volatile SingularAttribute<Post, PhasebookUser> fromUser;
	public static volatile SingularAttribute<Post, PhasebookUser> toUser;
	public static volatile SingularAttribute<Post, Boolean> private_;
	public static volatile SingularAttribute<Post, Boolean> read_;
	public static volatile SingularAttribute<Post, Date> createdAt;
	public static volatile SingularAttribute<Post, Date> deletedAt;
	public static volatile SingularAttribute<Post, Photo> photo;
	public static volatile SingularAttribute<Post, String> text;
}
