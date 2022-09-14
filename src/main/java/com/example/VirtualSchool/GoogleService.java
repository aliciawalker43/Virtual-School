package com.example.VirtualSchool;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.google.api.client.util.Value;

public class GoogleService {
	
	@Value("${client_id}")
	private String clientId;
	
	@Value("${client_secret}")
	private String clientSecret;
	
	
   // Exchange authorization code for token.
	public String getGoogleAccessToken(String code) {
		// We'll talk more about rest template in the coming days.
		Map<String, String> params = new HashMap<>();
		params.put("redirect_uri", "https://localhost:8080//");
		params.put("client_id", clientId);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		params.put("clent_secret", clientSecret);
		params.put("access_type", "offline");
		RestTemplate rest = new RestTemplate();
		GoogleAccessTokenResponse response = rest.postForObject("https://oauth2.googleapis.com/token ", params,
				GoogleAccessTokenResponse.class);
		return response.getAccessToken();
	}
	
}
