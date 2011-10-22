package phasebook.post;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import phasebook.user.PhasebookUser;


@Entity
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="POST_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="FROM_USER", referencedColumnName = "PHASEBOOK_USER_ID")
	private PhasebookUser fromUser;
	
	@ManyToOne
	@JoinColumn(name="TO_USER", referencedColumnName = "PHASEBOOK_USER_ID")
	private PhasebookUser toUser;
	
	@Column(name="PRIVATE_")
	private boolean private_;
	
	@Column(name="READ_")
	private boolean read_;
	
	@Column(name="CREATED_AT")
	private Date createdAt;
	
	@Column(name="DELETED_AT")
	private Date deletedAt;
	
	@Column(name="PHOTO_LINK")
	private String photoLink;
	
	@Column(name="TEXT")
	private String text;
	
	public Post()
	{
		super();
	}
	
	public Post(PhasebookUser from, PhasebookUser to, String text)
	{
		super();
		this.fromUser = from;
		this.toUser = to;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public PhasebookUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(PhasebookUser fromUser) {
		this.fromUser = fromUser;
	}

	public PhasebookUser getToUser() {
		return toUser;
	}

	public void setToUser(PhasebookUser toUser) {
		this.toUser = toUser;
	}

	public boolean isPrivate_() {
		return private_;
	}

	public void setPrivate_(boolean private_) {
		this.private_ = private_;
	}

	public boolean isRead_() {
		return read_;
	}

	public void setRead_(boolean read_) {
		this.read_ = read_;
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

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
