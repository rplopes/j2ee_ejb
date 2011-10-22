package phasebook.user;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PhasebookUserRemote {
	
	//public String showName(String name, String email, String password);
	public int create(String name, String email, String password);
	public int login(String email, String password);
	public PhasebookUser getUserById(Object id);
	public List getUsersFromSearch(Object search);
	public void deposit(Object id, Float money);
}
