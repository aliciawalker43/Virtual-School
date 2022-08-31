package com.example.VirtualSchool;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thumbnails {

	@JsonProperty ("default")
	private Dfault dfault;
	
	
	

	public Dfault getDfault() {
		return dfault;
	}

	public void setDfault(Dfault dfault) {
		this.dfault = dfault;
	}
	
	
	
}
