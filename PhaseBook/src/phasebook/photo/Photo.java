package phasebook.photo;

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
public class Photo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PHOTO_ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CREATED_AT")
	private Date createdAt;
	
	@Column(name="DELETED_AT")
	private Date deletedAt;
	
	@Column(name="LABEL")
	private String label;
	
	public Photo()
	{
		super();
	}
	
	public Photo(String name)
	{
		super();
		this.name = name;
	}
	
	public Photo(String name, String label)
	{
		super();
		this.name = name;
		this.label = label;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}