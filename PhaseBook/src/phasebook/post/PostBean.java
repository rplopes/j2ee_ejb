package phasebook.post;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import phasebook.user.PhasebookUser;

@Stateless
public class PostBean implements PostRemote {
	
	public void readUnreadPosts(PhasebookUser entry)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		List<?> result = null;
		
		Query q = em.createQuery("SELECT u FROM Post u WHERE u.toUser = :user AND u.read_ = :status");
		q.setParameter("user",entry);
		q.setParameter("status",false);
		
		try
		{
			result=q.getResultList();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Post post;
			for(Object object : result)
			{
				post = (Post)object;
				em.merge(post);
				post.setRead_(true);
				em.merge(post);
			}
			tx.commit();
		}
		catch(NoResultException e)
		{
			System.out.println("<Não foram encontrados posts por ler>");
		}
	}
	
}
