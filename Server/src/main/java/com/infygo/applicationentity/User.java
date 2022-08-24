package com.infygo.applicationentity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NamedQuery;
import org.springframework.lang.NonNull;

@Entity
@Table(name = User.USER_TABLENAME)
//@NamedQuery(name = "User.insertRecordIntoUser",query = "insert into User_Details values(?1)")
public class User {

	static final String USER_TABLENAME = "User_Details";

	@Id
	@NotEmpty
	@Column(name="user_id", length=25)
	private String userId;

	@NotEmpty
	@Column(name="city", length=25)
	private String city;

	@NotEmpty
	@Column(name="email", length=25)
	private String email;

	@NotEmpty
	@Column(name="name", length=25)
	private String name;

	@NotEmpty
	@Column(name="password", length=25)
	private String password;

	@NotEmpty
	@Column(name="phone", length=25)
	private String phone;

	//@NotEmpty since a user can be registered but may not have any tickets booked initially
	@Column(name="ticket")
	@OneToMany(mappedBy = "userId" , cascade = CascadeType.ALL)
	private List<Ticket> ticket;
	//A given user can be associated a number of tickets at a given time



	//Getters and Setters

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	public User(String userId, String city, String email, String name, String password, String phone,
			List<Ticket> ticket) {
		super();
		this.userId = userId;
		this.city = city;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", city=" + city + ", email=" + email + ", name=" + name + ", password="
				+ password + ", phone=" + phone + ", ticket=" + ticket + "]";
	}

	public User assignToUser(User destination) {

		destination.city = this.city;
		destination.email = this.email;
		destination.name = this.name;
		destination.password = this.password;
		destination.phone = this.phone;
		destination.userId = this.userId;


		destination.ticket = new ArrayList<Ticket>();

		for(Ticket obj : this.ticket) {
			destination.ticket.add(obj);
		}

		return destination;

	}

	public User() {
		super();
	}

	public boolean checkEquality(User obj) {
		
		if(!(this.city.equals(obj.city)) || !(this.email.equals(obj.email)) || !(this.name.equals(obj.name)) || !(this.password.equals(obj.password)) || !(this.phone.equals(obj.phone)) || !(this.userId.equals(obj.userId)))
			return false;
		
		return true;
	}

}
