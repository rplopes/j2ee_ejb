package phasebook.user;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
public class PhasebookUser implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int phasebook_user_id;
	private String name, email, password;
	private float money;
	private Date created_at, deleted_at;
	
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
		return "[" + this.phasebook_user_id + "] " + this.name + ": " + this.email + ", " + this.password;
	}
	
	public int getPhasebook_user_id() {
		return phasebook_user_id;
	}

	public void setPhasebook_user_id(int phasebook_user_id) {
		this.phasebook_user_id = phasebook_user_id;
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}
	
}
