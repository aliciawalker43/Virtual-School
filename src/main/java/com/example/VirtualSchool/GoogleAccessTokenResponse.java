package com.example.VirtualSchool;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleAccessTokenResponse {

	
	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("refresh_token")
	private String refreshToken;
	
	@JsonProperty("expires_in")
	private String expires;
	
	
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		return "GoogleAccessTokenResponse [accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", expires="
				+ expires + "]";
	}



	

	

	
	
	
	
}