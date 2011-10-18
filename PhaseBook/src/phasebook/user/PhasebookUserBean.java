package phasebook.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


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
}
