package com.example.VirtualSchool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String username;
	private String password;
	private Long googleId;
	private GoogleAccessTokenResponse crediential;
	
	
	
	
	
	
	public User() {
		super();
	}






	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
	}






	public String getUsername() {
		return username;
	}






	public void setUsername(String username) {
		this.username = username;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	public Long getGoogleId() {
		return googleId;
	}






	public void setGoogleId(Long googleId) {
		this.googleId = googleId;
	}




	


	
	
	
	
}
