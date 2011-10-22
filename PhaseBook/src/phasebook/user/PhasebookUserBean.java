package phasebook.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import phasebook.friendship.Friendship;
import phasebook.post.Post;


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
		em.refresh(user);
		tx.commit();
		return user.getId();
	}
	
	public int login(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
//		try {
			Query q = em.createQuery("SELECT u FROM PhasebookUser u " +
						"WHERE u.email LIKE :email AND " +
						"u.password LIKE :password");
			q.setParameter("email",email);
			q.setParameter("password",password);
			
//	    	Post post = new Post(getUserById(2),getUserById(1),"cenas cenas cenas");
//			em.persist(post);
//			em.refresh(post);
//			tx.commit();
			
			PhasebookUser user = ((PhasebookUser)q.getSingleResult());
			
			em.merge(user);
			em.refresh(user);
			
			tx.begin();
	    	Post post = new Post(user, user, "olaaaaaaaaaaaaaaa");
			em.persist(post);
			em.refresh(post);
			tx.commit();
			
			return user.getId();
//		} catch(Exception ex){
//			return -1;
//		}
	}
	
	public List<Post> getUserReceivedPostMessages(Object userId){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		PhasebookUser user = em.find(PhasebookUser.class, userId);
		em.persist(user);
		em.refresh(user);
		List<Post> userReceivedPosts = ((PhasebookUser)user).getReceivedPosts();
		List<Post> returnList = new ArrayList<Post>();
		for(int i = 0; i< userReceivedPosts.size(); i++){
			returnList.add(userReceivedPosts.get(i));
		}
		return returnList;
	}
	
	public PhasebookUser getUserById(Object id){
		int userId = Integer.parseInt(id.toString());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		try {
			PhasebookUser user = em.find(PhasebookUser.class, userId);
			em.persist(user);
			em.refresh(user);
			return user;
		} catch(Exception ex){
			return null;
		}
	}
	
	public List getUsersFromSearch(Object search) {
		List users = new ArrayList();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		try {
			Query q = em.createQuery("SELECT u FROM PhasebookUser u ");
			users = q.getResultList();
			return users;
		} catch (Exception e) {
			return users;
		}
	}
}
