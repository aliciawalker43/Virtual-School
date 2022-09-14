package com.example.VirtualSchool;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleAccessTokenResponse {

	
	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("refresh_token")
	private String refreshToken;
	
}