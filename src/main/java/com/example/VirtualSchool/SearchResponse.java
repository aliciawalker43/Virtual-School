package com.example.VirtualSchool;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponse {

	
	@JsonProperty 
	private Id id;
	private Snippet snippet ;
	
	//Getters & Setters
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public Snippet getSnippet() {
		return snippet;
	}
	public void setSnippit(Snippet snippet) {
		this.snippet = snippet;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
