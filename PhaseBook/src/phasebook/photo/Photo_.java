package phasebook.photo;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.user.PhasebookUser;

@Generated(value="Dali", date="2011-10-26T00:05:56.557+0100")
@StaticMetamodel(Photo.class)
public class Photo_ {
	public static volatile SingularAttribute<Photo, Integer> id;
	public static volatile SingularAttribute<Photo, PhasebookUser> user;
	public static volatile SingularAttribute<Photo, String> name;
	public static volatile SingularAttribute<Photo, Boolean> private_;
	public static volatile SingularAttribute<Photo, Date> createdAt;
	public static volatile SingularAttribute<Photo, Date> deletedAt;
	public static volatile SingularAttribute<Photo, String> label;
}
