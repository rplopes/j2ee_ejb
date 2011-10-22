package phasebook.user;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import phasebook.friendship.Friendship;
import phasebook.lotterybet.LotteryBet;
import phasebook.oldlotterybet.OldLotteryBet;
import phasebook.post.Post;

@Entity
public class PhasebookUser implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PHASEBOOK_USER_ID")
	private int id;
	
	private String name, email, password;
	private float money;
	
	@Column(name="CREATED_AT")
	private Date createdAt;
	
	@Column(name="DELETED_AT")
	private Date deletedAt;
	
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "hostUser") 
	private List<Friendship> sentInvites = new ArrayList<Friendship>();
	
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "invitedUser")
	private List<Friendship> receivedInvites = new ArrayList<Friendship>();
	
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
	private List<LotteryBet> lotteryBets = new ArrayList<LotteryBet>();
	
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
	private List<OldLotteryBet> oldLotteryBets = new ArrayList<OldLotteryBet>();
	
	@ElementCollection  
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "toUser")
	private List<Post> sentPosts = new ArrayList<Post>();
	
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "fromUser")
	private List<Post> receivedPosts = new ArrayList<Post>();
	
	public PhasebookUser()
	{
		super();
	}
	
	public PhasebookUser(String name, String email, String password, float money)
	{
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.money = money;
	}
	
	public PhasebookUser(String name, String email, String password)
	{
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		return "[" + this.id + "] " + this.name + ": " + this.email + ", " + this.password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Friendship> getSentInvites() {
		return sentInvites;
	}

	public void setSentInvites(List<Friendship> sentInvites) {
		this.sentInvites = sentInvites;
	}

	public List<Friendship> getReceivedInvites() {
		return receivedInvites;
	}

	public void setReceivedInvites(List<Friendship> receivedInvites) {
		this.receivedInvites = receivedInvites;
	}

	public List<OldLotteryBet> getOldLotteryBets() {
		return oldLotteryBets;
	}

	public void setOldLotteryBets(List<OldLotteryBet> oldLotteryBets) {
		this.oldLotteryBets = oldLotteryBets;
	}

	public List<LotteryBet> getLotteryBets() {
		return lotteryBets;
	}

	public void setLotteryBets(List<LotteryBet> lotteryBets) {
		this.lotteryBets = lotteryBets;
	}

	public List<Post> getReceivedPosts() {
		return receivedPosts;
	}

	public void setReceivedPosts(List<Post> receivedPosts) {
		this.receivedPosts = receivedPosts;
	}

	public List<Post> getSentPosts() {
		return sentPosts;
	}

	public void setSentPosts(List<Post> sentPosts) {
		this.sentPosts = sentPosts;
	}
}