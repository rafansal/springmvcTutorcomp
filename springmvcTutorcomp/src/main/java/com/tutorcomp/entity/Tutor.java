package com.tutorcomp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tutor")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int id;

	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id")
    private User user;

	@Transient
	private String password;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Tutor getDTO() {
		Tutor tutorDTO = new Tutor();
		tutorDTO.setId(this.getId());
		tutorDTO.setFirstName(this.firstName);
		tutorDTO.setLastName(this.lastName);
		tutorDTO.setEmail(this.getEmail());
		tutorDTO.setMobile(this.getMobile());
		tutorDTO.setUserId(this.getUserId());
		tutorDTO.setUser(this.getUser());
		tutorDTO.setPassword(this.getPassword());
		return tutorDTO;
	}
}
