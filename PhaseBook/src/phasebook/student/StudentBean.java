package phasebook.student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 * Session Bean implementation class StudentBean
 */
@Stateless
public class StudentBean implements StudentRemote {

    /**
     * Default constructor. 
     */
    public StudentBean() {
    }

	public String showName(String name, String phone) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
    	tx.begin();
    	Student student = new Student(name, phone);
		em.persist(student);
		tx.commit();
		System.out.println(name);
		return name;
	}

}
