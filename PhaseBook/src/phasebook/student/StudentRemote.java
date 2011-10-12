package phasebook.student;
import javax.ejb.Remote;

@Remote
public interface StudentRemote {
	
	public String showName(String name, String phone);

}
