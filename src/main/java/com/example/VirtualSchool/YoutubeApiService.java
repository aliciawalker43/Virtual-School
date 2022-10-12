package com.example.VirtualSchool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.common.collect.Lists;



@Configuration 
@Service
public class YoutubeApiService {
	
	@Value("${api-key}")
	String apiKey;

	
	 private static final String PROPERTIES_FILENAME = "youtube.properties";

     private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

	  
     
     // Pull general search results from YouTube API
     
	 public List<SearchResponse> searchResults ( String QueryTerm) throws IOException {
    
		 // This object is used to make YouTube Data API requests.
    YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
         @Override
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
    
    //System.out.println(searchResultList);
    List<SearchResponse> response= new ArrayList<SearchResponse>();
    
    //Parse each item in list and store in object
     for (SearchResult result:searchResultList) {
    	String sr= result.toString();
    	final ObjectMapper objectMapper = new ObjectMapper();
    	SearchResponse s = objectMapper.readValue(sr, SearchResponse.class);
    	response.add(s);
    	
     }
    return response;
	 }
     
	 
	 
	 //Call YouTube Api for search results from specific user channel using credentials.
	 
	    
	  public List<Channel> myChannel() throws IOException  {
	 // This OAuth 2.0 access scope allows for read-only access to the
     // authenticated user's account, but not other types of account access.
         List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.readonly");
        
        // List<Channel> channelsList= null;
         
     //try {
         // Authorize the request.
        Credential credential = Auth.authorize(scopes, "myUploads");
         
         // This object is used to make YouTube Data API requests.
        YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName("VirtualSchool").build();

         // Call the API's channels.list method to retrieve the
         // resource that represents the authenticated user's channel.
         // In the API response, only include channel information needed for
         // this use case. The channel's contentDetails part contains
         // playlist IDs relevant to the channel, including the ID for the
         // list that contains videos uploaded to the channel.
         YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
        channelRequest.setMine(true);
         channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
         ChannelListResponse channelResult = channelRequest.execute();

          List <Channel> channelsList = channelResult.getItems();
         System.out.println(channelsList);
         return channelsList;
      
    	  
       }
     
    	// return channelsList;
     
    	 
     
         
         
         /*if (channelsList != null) {
             // The user's default channel is the first item in the list.
             // Extract the playlist ID for the channel's videos from the
             // API response.
             String uploadPlaylistId =
                     channelsList.get(0).getContentDetails().getRelatedPlaylists().getUploads();

             // Define a list to store items in the list of uploaded videos.
             List<PlaylistItem> playlistItemList = new ArrayList<PlaylistItem>();

             // Retrieve the playlist of the channel's uploaded videos.
             YouTube.PlaylistItems.List playlistItemRequest =
                     youtube.playlistItems().list("id,contentDetails,snippet");
             playlistItemRequest.setPlaylistId(uploadPlaylistId);

             // Only retrieve data used in this application, thereby making
             // the application more efficient. See:
             // https://developers.google.com/youtube/v3/getting-started#partial
             playlistItemRequest.setFields(
                     "items(contentDetails/videoId,snippet/title,snippet/publishedAt),nextPageToken,pageInfo");

             String nextToken = "";

             // Call the API one or more times to retrieve all items in the
             // list. As long as the API response returns a nextPageToken,
             // there are still more items to retrieve.
             do {
                 playlistItemRequest.setPageToken(nextToken);
                 PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();

                 playlistItemList.addAll(playlistItemResult.getItems());

                 nextToken = playlistItemResult.getNextPageToken();
                 
             } while (nextToken != null); 
            
             
             // Prints information about the results.
            // prettyPrint(playlistItemList.size(), playlistItemList.iterator());
         }
        
          }  catch (GoogleJsonResponseException e) {
         e.printStackTrace();
         System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                 + e.getDetails().getMessage());

    } catch (Throwable t) {
         t.printStackTrace();
     }*/
     
       
     }
     



