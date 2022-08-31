package com.example.VirtualSchool;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Id {

	@JsonProperty
	private String kind ;
	private String videoId;
	
	
	//Getters & Setters
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
	
	
}
