package com.example.VirtualSchool;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.services.youtube.model.SearchResult;

import antlr.collections.List;

@Controller
public class VSController {
	
	@RequestMapping ("/")
	public String showHome() {
		return "homepage";
	}

	@RequestMapping ("//")
	public String showStudentHome() {
		return "studenthome";
	}
	
	@RequestMapping ("/search")
	public String searchVideos( Model model, String searchphrase) throws IOException {
		
		 Iterator<SearchResult> results= YoutubeApiService.searchResults(searchphrase);
		
		 model.addAttribute("results", results);
		return "videoresults";
	}
	
}
