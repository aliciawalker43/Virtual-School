package com.example.VirtualSchool;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;



@Controller
public class UserController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	GoogleService googleService;
	
	@Autowired
	private YoutubeApiService ys;
	

	@Autowired
	HttpSession session;

	// SIGNUP METHODS
	
	@RequestMapping("/signup")
	public String signUpHome() {
		
		return("newmember");
	}
	
	@PostMapping("/signup")
	public String submitSignup(User user,@RequestParam("confirmpassword") String passwordConfirm, 
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("username") String username, Model model) {

		User existingUser = userDao.findByUsername(username);
		if (existingUser != null) {
			model.addAttribute("message", "That username already exists. Please choose a different username.");

			return "newmember";

		}
		
		
		if (!passwordConfirm.contentEquals(password)) {
			model.addAttribute("message", "The passwords you entered do not match. Please try again.");
			return "newmember";
		}
		if (passwordConfirm.contentEquals(password)) {

		String encodePass = Base64.getEncoder().encodeToString(password.getBytes());
		user.setPassword(encodePass);
	
	
		userDao.save(user);
		
		session.setAttribute("user", user);
		model.addAttribute("message", "Thank You for Signing Up.");
		
		}
		return "homepage";
		
	}

	// LOGIN METHODS
	

	
	
	@PostMapping("/login")
	public String submitLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, RedirectAttributes redirect) {
		//find user
		User user = userDao.findByUsername(username);

		if (user == null) {
			model.addAttribute("message", "Incorrect username. Please try again.");
			return "homelogin";
		}
		byte[] decodeByte = Base64.getDecoder().decode(user.getPassword());
		String decodePass = new String(decodeByte);
       // check is password accurate
		if (!password.contentEquals(decodePass)) {
			model.addAttribute("message", "Incorrect password. Please try again.");
			return "homelogin";
		}
		session.setAttribute("user", user);
		
		redirect.addFlashAttribute("message", "Logged in.");
		
	    return "redirect:/";
	}
	
	// LOGOUT METHOD
	@RequestMapping("/logout")
	public String logout(RedirectAttributes redirect) {
		session.invalidate();
		redirect.addFlashAttribute("message", "You are now logged out.");
		
		return "redirect:/index";
	}

	/*@RequestMapping("/updateinfo")
	public String updateUserForm() {
		
		return "userprofileupdate";
	}
	
	
	@RequestMapping("/updateProfile")
	public String updateProfile(Model model, @RequestParam ("username") String username,
			@RequestParam ("email") String email,
			@RequestParam ("password") String password,
			@RequestParam ("password2") String password2,
			RedirectAttributes redirect) {
		
		User currentUser= (User)session.getAttribute("user");
		Long id =  currentUser.getId();
		User user= userDao.findUserById(id);
		
		System.out.println(user);
		if (!password2.contentEquals(password)) {
			model.addAttribute("message", "The passwords you entered do not match. Please try again.");
			return "userprofileupdate";
		}
		if (password2.contentEquals(password)) {

		String encodePass = Base64.getEncoder().encodeToString(password.getBytes());
		user.setPasscode(encodePass);
		
		}
		user.setEmail(email);
		user.setUsername(username);
		
		userDao.save(user);
		session.setAttribute("user", user);
		model.addAttribute("user", user);
		return "userprofile";

}*/
	
	// This is the second step in OAuth. After the user approves the login on the google site, it redirects back
		// to our site with a temporary code.
		@RequestMapping("/oauth-google-callback")
		public String handleGoogleCallback(@RequestParam("code") String code, HttpSession session) throws IOException {
			//Get user from session
			//User currentUser= (User)session.getAttribute("user");
		   //	Long id =  currentUser.getId();
			
			
			// exchange code for response object.
			GoogleAccessTokenResponse response = googleService.getGoogleAccessToken(code);
			System.out.println("response"+ response);
			
			
			//store response as credential of current user
			//currentUser.setCredential(response);
			
			//use credentials to call youtube api mychannel 
			//System.out.println(ys.myChannel());
			
			
			
            /*
			// Set the user on the session, just like the other type of login.
			session.setAttribute("user", user);
			
			// token session- not using.
			session.setAttribute("googleAccessToken", accessToken);
            */
			return "videoresults";
		}
}
