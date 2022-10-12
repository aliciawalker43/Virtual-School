package com.example.VirtualSchool;


// model for API response

public class GoogleUser {

	
	private String name;
	private Long id;
	
	
	
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public void copyToUser(User user) {
		user.setGoogleId(id);
		user.setName(name);
	}
}
