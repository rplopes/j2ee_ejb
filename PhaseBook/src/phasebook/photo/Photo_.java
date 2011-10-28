package phasebook.photo;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-10-28T00:40:17.755+0100")
@StaticMetamodel(Photo.class)
public class Photo_ {
	public static volatile SingularAttribute<Photo, Integer> id;
	public static volatile SingularAttribute<Photo, String> name;
	public static volatile SingularAttribute<Photo, Timestamp> createdAt;
	public static volatile SingularAttribute<Photo, Date> deletedAt;
	public static volatile SingularAttribute<Photo, String> label;
}
