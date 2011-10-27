package phasebook.user;
import java.util.List;

import javax.ejb.Remote;

import phasebook.photo.Photo;
import phasebook.post.Post;

@Remote
public interface PhasebookUserRemote {
	
	//public String showName(String name, String email, String password);
	public int create(String name, String email, String password);
	public int login(String email, String password);
	public PhasebookUser getUserById(Object id);
	public List getUsersFromSearch(Object search);
	public List<Post> getUserReceivedPosts(Object userId);
	public void addPost(PhasebookUser from, PhasebookUser to, String text, String privacy);
	public void invite(PhasebookUser hostUser, PhasebookUser invitedUser);
	public void addPost(PhasebookUser from, PhasebookUser to, String text, String photoLink, String privacy);
	public List getUserPublicPosts(Object userId);
	public Photo addPhoto(String photoLink);
	public void setProfilePicture(PhasebookUser user, Photo photo);
	public void deposit(Object id, Float money);
	public int getNUnreadUserPosts(PhasebookUser user);
}
