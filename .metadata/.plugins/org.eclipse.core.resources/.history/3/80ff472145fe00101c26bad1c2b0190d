package phasebook.friendship;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import phasebook.user.PhasebookUser;

@Entity
public class Friendship implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FRIENDSHIP_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="HOST_PHASEBOOK_USER_ID", referencedColumnName="PHASEBOOK_USER_ID")
	private PhasebookUser hostUser;
		
	@ManyToOne
	@JoinColumn(name="invited_PHASEBOOK_USER_ID", referencedColumnName="PHASEBOOK_USER_ID")
	private PhasebookUser invitedUser;
	
	private boolean accepted_;
	
	@Column(name="CREATED_AT")
	private Date createdAt;
	
	@Column(name="DELETED_AT")
	private Date deletedAt;
	
	
	protected Friendship()
	{
		super();
	}
	
	protected Friendship(PhasebookUser inviterUser, PhasebookUser inveteeUser) 
	{
		super();
		this.hostUser = inviterUser;
		this.invitedUser = inveteeUser;
	}
	
	private Date getCurrentTime()
	{		
		Calendar currenttime = Calendar.getInstance();
		return new Date((currenttime.getTime()).getTime());
	}

	protected int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	protected PhasebookUser getHostUser() {
		return hostUser;
	}

	protected void setHostUser(PhasebookUser hostUser) {
		this.hostUser = hostUser;
	}

	protected PhasebookUser getInvitedUser() {
		return invitedUser;
	}

	protected void setInvitedUser(PhasebookUser invitedUser) {
		this.invitedUser = invitedUser;
	}

	protected boolean isAccepted_() {
		return accepted_;
	}

	protected void setAccepted_(boolean accepted_) {
		this.accepted_ = accepted_;
	}

	protected Date getCreatedAt() {
		return createdAt;
	}

	protected void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	protected Date getDeletedAt() {
		return deletedAt;
	}

	protected void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	protected void deleteFriendship()
	{
		this.deletedAt=getCurrentTime();
	}
	
	
}
