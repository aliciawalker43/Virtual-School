package com.example.VirtualSchool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	private String username;
	private String passcode;
	private String email;
	
	
	
	
	
	public User() {
		super();
	}
	
	//Getters &Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
}
