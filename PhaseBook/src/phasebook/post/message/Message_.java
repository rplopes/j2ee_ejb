package phasebook.post.message;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import phasebook.post.Post_;

@Generated(value="Dali", date="2011-10-18T00:43:34.435+0100")
@StaticMetamodel(Message.class)
public class Message_ extends Post_ {
	public static volatile SingularAttribute<Message, Integer> id;
	public static volatile SingularAttribute<Message, String> text;
}
