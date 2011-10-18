package phasebook.user;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-10-18T14:42:23.499+0100")
@StaticMetamodel(PhasebookUser.class)
public class PhasebookUser_ {
	public static volatile SingularAttribute<PhasebookUser, Integer> id;
	public static volatile SingularAttribute<PhasebookUser, String> name;
	public static volatile SingularAttribute<PhasebookUser, String> email;
	public static volatile SingularAttribute<PhasebookUser, String> password;
	public static volatile SingularAttribute<PhasebookUser, Float> money;
	public static volatile SingularAttribute<PhasebookUser, Date> createdAt;
	public static volatile SingularAttribute<PhasebookUser, Date> deletedAt;
}
