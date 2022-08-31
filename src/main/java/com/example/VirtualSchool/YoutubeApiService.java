package com.example.VirtualSchool;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.http.auth.AUTH;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;





@Configuration 
@Service
public class YoutubeApiService {
	
	@Value("${api-key}")
	String apiKey;

	
	 private static final String PROPERTIES_FILENAME = "youtube.properties";

     private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

	  
     
     private RestTemplate rt;// parse JSon data and store in object

		// initialization block that runs when a new instance of the class is created
		// loaded before the constructor
		{
			ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
				request.getHeaders().add(HttpHeaders.USER_AGENT, "anything");
				return execution.execute(request, body);
			};
			rt = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
		}
     
     
     
	// private static YouTube youtube;

	
	
	/* Properties properties = new Properties();
	 
     try {
         InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
         properties.load(in);

     } catch (IOException e) {
         System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                 + " : " + e.getMessage());
         System.exit(1);
     }

     */


	 public List<SearchResponse> searchResults ( String QueryTerm) throws IOException {
    
		 // This object is used to make YouTube Data API requests.
    YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
         public void initialize(HttpRequest request) throws IOException {
         }
     }).setApplicationName("VirtualSchool").build();
     

     // Define the API request for retrieving search results.
     YouTube.Search.List search = youtube.search().list("id,snippet");

     // Set your developer key
     //String apiKey = properties.getProperty("youtube.apikey");
     search.setKey(apiKey);
     search.setQ(QueryTerm);
     
     // Restrict the search results to only include videos. See:
     // https://developers.google.com/youtube/v3/docs/search/list#type
     search.setType("video");
     
     // To increase efficiency, only retrieve the fields that the
     // application uses.
     search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
     search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
     
     
     // Call the API and print results.
      
    SearchListResponse searchResponse = search.execute();
    List<SearchResult> searchResultList = searchResponse.getItems();
    
    System.out.println(searchResultList);
    List<SearchResponse> response= new ArrayList<SearchResponse>();
    
    //Parse each item in list and store in object
     for (SearchResult result:searchResultList) {
    	String sr= result.toString();
    	final ObjectMapper objectMapper = new ObjectMapper();
    	SearchResponse s = objectMapper.readValue(sr, SearchResponse.class);
    	response.add(s);
    	
    	
     }
    
    //Parsing Json to objects attempts
     //SearchResponse searchResults =rt.getForObject(uri,SearchResponse.class);
    
    /* final ObjectMapper objectMapper = new ObjectMapper();
    SearchResponse[] sr = objectMapper.readValues(searchResultList, Class<SearchResponse[]>);
     */
    
    //SearchResponse sr = .parseAs(SearchResponse.class);
    
     //return (searchResultList.iterator());
     return response;
	 }
     
     /*
     if (searchResultList != null) {
         prettyPrint(searchResultList.iterator(), queryTerm);
     }
 } catch (GoogleJsonResponseException e) {
     System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
             + e.getDetails().getMessage());
 } catch (IOException e) {
     System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
 } catch (Throwable t) {
     t.printStackTrace();
 }
     */
     
}

