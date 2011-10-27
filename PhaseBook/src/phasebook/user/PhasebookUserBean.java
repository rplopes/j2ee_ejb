package phasebook.user;

import java.util.ArrayList;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import phasebook.friendship.Friendship;
import phasebook.photo.Photo;
import phasebook.post.Post;
import phasebook.email.*;

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
	
	public int create(String name, String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
    	PhasebookUser user = new PhasebookUser(name, email, password);
		em.persist(user);
		em.refresh(user);
		tx.commit();
		int id = user.getId();
		em.close();
		emf.close();
		return id;
	}
	
	/* (non-Javadoc)
	 * @see phasebook.user.PhasebookUserRemote#login(java.lang.String, java.lang.String)
	 */
	public int login(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		try {
			int returnValue = -1;
			Query q = em.createQuery("SELECT u FROM PhasebookUser u " +
						"WHERE u.email LIKE :email AND " +
						"u.password LIKE :password");
			q.setParameter("email",email);
			q.setParameter("password",password);
			
			PhasebookUser user = ((PhasebookUser)q.getSingleResult());
			
			em.merge(user);
			em.refresh(user);
			
			em.close();
			emf.close();
			return user.getId();
		} catch(NoResultException ex){
			return -1;
		} catch(NonUniqueResultException ex){
			return -1;
		}
	}
	
	public List<Post> getUserReceivedPosts(Object userId){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		PhasebookUser user = em.find(PhasebookUser.class, Integer.parseInt(userId.toString()));
		em.persist(user);
		em.refresh(user);
		List<Post> userReceivedPosts = ((PhasebookUser)user).getReceivedPosts();
		List<Post> returnList = new ArrayList<Post>();
		for(int i = 0; i< userReceivedPosts.size(); i++){
			returnList.add(userReceivedPosts.get(i));
		}
		em.close();
		emf.close();
		return returnList;
	}
	
	public List getUserPublicPosts(Object userId){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		PhasebookUser user = em.find(PhasebookUser.class, Integer.parseInt(userId.toString()));
		
		try{
			Query q = em.createQuery("SELECT u FROM Post u " +
					"WHERE u.toUser LIKE :user AND " +
					"u.private_ = :private_");
			q.setParameter("user",user);
			q.setParameter("private_",false);
			
			em.clear();
			emf.close();
			
			return q.getResultList();
		} catch(NoResultException e){
			em.close();
			emf.close();
			List<Post> empty = new ArrayList<Post>();
			return empty;
		}
	}
	
	public PhasebookUser getUserById(Object id){
		int userId = Integer.parseInt(id.toString());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		try {
			PhasebookUser user = em.find(PhasebookUser.class, userId);
			em.persist(user);
			em.refresh(user);
			//em.close();
			//emf.close();
			return user;
		} catch(NoResultException ex){
			em.close();
			emf.close();
			return null;
		} catch(NonUniqueResultException ex){
			em.close();
			emf.close();
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
			em.close();
			emf.close();
			return results;
		} catch(NoResultException ex){
			em.close();
			emf.close();
			return users;
		} 
	}
	
	public void addPost(PhasebookUser from, PhasebookUser to, String text, String privacy){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
    	Post post = new Post(from, to, text, privacy);
		em.persist(post);
		em.refresh(post);
		tx.commit();
		em.close();
		emf.close();
		EmailUtils.notifyUser(to, "PHASEBOOK: You have a new post", from.getName()+
				" posted a message on your wall:<br><br>\""+text+"\"<br><br>You have also "+(getNUnreadUserPosts(to)-1)+
				" posts to read.<br><br>"+
				EmailUtils.a("Go to your wall"));
	}
	
	public void addPost(PhasebookUser from, PhasebookUser to, String text, String photoLink, String privacy){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Photo photo = new Photo(photoLink); 
		em.persist(photo);
		em.refresh(photo);
		
    	Post post = new Post(from, to, text, photo, privacy);
		em.persist(post);
		em.refresh(post);
		
		tx.commit();
		em.close();
		emf.close();
		if(!from.equals(to))
			EmailUtils.notifyUser(to, "PHASEBOOK: You have a new post", 
				from.getName()+" posted a message on your wall:<br><br>"+EmailUtils.img(photo.getName(), from.getId())+
				"<br><br>\""+text+"\"<br><br>You have also "+(getNUnreadUserPosts(to)-1)+
				" posts to read.<br><br>"+EmailUtils.a("Go to your wall"));
	}
	
	public Photo addPhoto(String photoLink){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Photo photo = new Photo(photoLink); 
		em.persist(photo);
		em.refresh(photo);

		tx.commit();
		em.close();
		emf.close();
		return photo;
	}
	
	public void invite(PhasebookUser hostUser, PhasebookUser invitedUser)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
    	Friendship fship = new Friendship(hostUser, invitedUser);
		em.persist(fship);
		em.refresh(fship);
		tx.commit();
		EmailUtils.notifyUser(invitedUser, "PHASEBOOK: "+hostUser.getName()+" invited you", hostUser.getName()+" invited you to be his friend<br><br>"
				+EmailUtils.a("Go to your notifications"));
	}
	
	public void setProfilePicture(PhasebookUser user, Photo photo)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		em.merge(user);
		user.setPhoto(photo);
		em.merge(user);
		tx.commit();
		em.close();
		emf.close();
		
	}

	public void deposit(Object id, Float money) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		PhasebookUser user = getUserById(id);
		user.setMoney(user.getMoney() + money);
		em.merge(user);
		tx.commit();
		em.close();
		emf.close();
	}
	
	public int getNUnreadUserPosts(PhasebookUser user)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhaseBook");
		EntityManager em = emf.createEntityManager();
		
		List<?> posts = null;
		
		Query q = em.createQuery("SELECT u FROM Post u WHERE u.toUser = :user AND u.read_ = :status");
		q.setParameter("user",user);
		q.setParameter("status",false);
		
		return q.getResultList().size();
	}
	
}
