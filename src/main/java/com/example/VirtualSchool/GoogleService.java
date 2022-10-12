package com.example.VirtualSchool;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import org.springframework.beans.factory.annotation.Value;

@Component
public class GoogleService {
	
	@Value("${client_id}")
	private String clientId;
	
	@Value("${client_secret}")
	private String clientSecret;
	
	
   // Exchange authorization code for token and refresh.
	public GoogleAccessTokenResponse getGoogleAccessToken(String code) {
		// We'll talk more about rest template in the coming days.
		Map<String, String> params = new HashMap<>();
		params.put("redirect_uri", "http://localhost:8080/oauth-google-callback");
		params.put("client_id", clientId);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		params.put("client_secret", clientSecret);
		//params.put("access_type", "offline");
		RestTemplate rest = new RestTemplate();
		GoogleAccessTokenResponse response = rest.postForObject("https://oauth2.googleapis.com/token", params,
				GoogleAccessTokenResponse.class);
		return response;
	}
	
	

	
	
	//not using this yet
	public User getUserFromGoogleApi(String accessToken) {
		
		RestTemplate rest = new RestTemplate();
		
		// ATTN: replace url with google
		String uri = "https://api.google.com/user";
		
		// add accessToken as header
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		
		// make GET request. Must use exchange when adding headers.
		GoogleUser response = rest.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), GoogleUser.class).getBody();
		User user = new User();
		response.copyToUser(user);
		return user;
}

	
}