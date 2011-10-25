package phasebook.post;

import javax.ejb.Stateless;

@Stateless
public class PostBean implements PostRemote {
	
//	public int create(PhasebookUser toUserId, PhasebookUser fromUserId, String text) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		
//		tx.begin();
//		
// 
//		em.persist(post);
//		em.refresh(post);
//		tx.commit();
//		return post.getId();
//	}
}
