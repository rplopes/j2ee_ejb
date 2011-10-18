package phasebook.student;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Student implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name, phone;
	
	public Student()
	{
		super();
	}
	
	public Student(String name, String phone)
	{
		super();
		this.name = name;
		this.phone = phone;
	}
	
	public Student(String name)
	{
		super();
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	@Override
	public String toString()
	{
		return "[" + this.id + "] " + this.name + ": " + this.phone;
	}
}
