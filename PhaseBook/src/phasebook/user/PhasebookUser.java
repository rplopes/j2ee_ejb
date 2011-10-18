package phasebook.user;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

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
}