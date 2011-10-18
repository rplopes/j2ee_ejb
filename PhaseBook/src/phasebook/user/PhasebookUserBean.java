package phasebook.user;

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

	public String showName(String name, String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
    	tx.begin();
    	PhasebookUser user = new PhasebookUser(name, email, password);
		em.persist(user);
		tx.commit();
		System.out.println(name);
		return name;
	}
	
	public boolean create(String name, String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
    	PhasebookUser user = new PhasebookUser(name, email, password);
		em.persist(user);
		tx.commit();
		return true;
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
}
