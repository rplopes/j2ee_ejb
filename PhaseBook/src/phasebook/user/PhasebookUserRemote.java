package phasebook.user;
import javax.ejb.Remote;

@Remote
public interface PhasebookUserRemote {
	
	public String showName(String name, String email, String password);

}
