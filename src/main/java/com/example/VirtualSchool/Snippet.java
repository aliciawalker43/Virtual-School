package com.example.VirtualSchool;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snippet {

	@JsonProperty
	private Thumbnails thumbnails;
	private String title;
	
	//Getters & Setters
	public Thumbnails getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(Thumbnails thumbnails) {
		this.thumbnails = thumbnails;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
