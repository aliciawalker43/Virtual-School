package com.example.VirtualSchool;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.services.youtube.model.Channel;



@Controller
public class VSController {
	
	@Autowired
	private YoutubeApiService ys;
	
	
	@RequestMapping ("/")
	public String showLanding() {
		return "homepage";
	}
	
	

	@RequestMapping ("//")
	public String showStudentHome() {
		return "studenthome";
	}
	
	@RequestMapping ("/search")
	public String searchVideos( Model model, String searchphrase) throws IOException {
		
		 List<SearchResponse> results= ys.searchResults(searchphrase);
		
		 model.addAttribute("results", results);
		 //System.out.println(results.toString());
		return "videoresults";
	}
	
	@RequestMapping ("/myvideos")
	public String myVideos( Model model) throws IOException {
		
		//List<Channel> results= ys.myChannel();
		
		// model.addAttribute("results", results);
		 //System.out.println(results.toString());
		return "videoresults";
	}
	
}
