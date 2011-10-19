package phasebook.user;
import javax.ejb.Remote;

@Remote
public interface PhasebookUserRemote {
	
	public String showName(String name, String email, String password);
	public int create(String name, String email, String password);
	public int login(String email, String password);
	public PhasebookUser getUserById(Object id);
}
