package phasebook.user;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Session Bean implementation class PhasebookUserBean
 */
@Stateless
public class PhasebookUserBean implements PhasebookUserRemote {

    /**
     * Default constructor. 
     */
    public PhasebookUserBean() {
    }

	/*public String showName(String name, String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
    	tx.begin();
    	PhasebookUser user = new PhasebookUser(name, email, password);
		em.persist(user);
		tx.commit();
		System.out.println(name);
		return name;
	}*/
	
	public int create(String name, String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
    	PhasebookUser user = new PhasebookUser(name, email, password);
		em.persist(user);
		tx.commit();
		return user.getId();
	}
	
	public int login(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		try {
			Query q = em.createQuery("SELECT u FROM PhasebookUser u " +
						"WHERE u.email LIKE :email AND " +
						"u.password LIKE :password");
			q.setParameter("email",email);
			q.setParameter("password",password);
			return ((PhasebookUser)q.getSingleResult()).getId();
		} catch(Exception ex){
			return -1;
		}
	}
	
	public PhasebookUser getUserById(Object id){
		int userId = Integer.parseInt(id.toString());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		try {
			PhasebookUser user = em.find(PhasebookUser.class, userId);
			return user;
		} catch(Exception ex){
			return null;
		}
	}
	
	public List getUsersFromSearch(Object search) {
		List users = new ArrayList();
		List results = new ArrayList();
		String s = search.toString();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		try {
			Query q = em.createQuery("SELECT u FROM PhasebookUser u ");
			users = q.getResultList();
			if (s != null)
			{
				Pattern pattern = Pattern.compile(s);
				for (int i=0; i<users.size(); i++)
				{
					PhasebookUser user = (PhasebookUser)users.get(i);
					boolean found = false;
					Matcher matcher = pattern.matcher(user.getName());
					if (matcher.find())
						found = true;
					matcher = pattern.matcher(user.getEmail());
					if (matcher.find())
						found = true;
					if (found)
						results.add(user);
				}
			}
			return results;
		} catch (Exception e) {
			return users;
		}
	}
}
