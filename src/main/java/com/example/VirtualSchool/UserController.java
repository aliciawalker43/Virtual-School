package com.example.VirtualSchool;

import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	GoogleService googleService;
	

	@Autowired
	HttpSession session;

	// SIGNUP METHODS
	
	@RequestMapping("/signup")
	public String signUpHome() {
		
		return("newmember");
	}
	
	@PostMapping("/signup")
	public String submitSignup(User user,@RequestParam("passwordConfirm") String passwordConfirm, 
			@RequestParam("passcode") String password,@RequestParam("firstname") String name1,
			@RequestParam("lastname") String name2,
			@RequestParam("email") String email,
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
		user.setPasscode(encodePass);
	
	
		userDao.save(user);
		
		session.setAttribute("user", user);
		model.addAttribute("message", "Thank You for Signing Up.");
		
		}
		return "redirect:/index";
		
	}

	// LOGIN METHODS
	

	@PostMapping("/loging")
	public String loginGoogle(RedirectAttributes redirect) {
		session.invalidate();
		redirect.addFlashAttribute("message", "Log in with Google.");
		
		return "redirect:/https://accounts.google.com/o/oauth2/v2/auth?\n" + 
				" scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&\n" + 
				" access_type=offline&\n" + 
				" include_granted_scopes=true&\n" + 
				" response_type=code&\n" + 
				" state=state_parameter_passthrough_value&\n" + 
				" redirect_uri=https%3A//oauth2.example.com/code&\n" + 
				" client_id=client_id";
	}
	
	@PostMapping("/login")
	public String submitLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, RedirectAttributes redirect) {
		//find user
		User user = userDao.findByUsername(username);

		if (user == null) {
			model.addAttribute("message", "Incorrect username. Please try again.");
			return "homelogin";
		}
		byte[] decodeByte = Base64.getDecoder().decode(user.getPasscode());
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
	
	// This is the second step in OAuth. After the user approves the login on the github site, it redirects back
		// to our site with a temporary code.
		@RequestMapping("/oauth-google-callback")
		public String handleGithubCallback(@RequestParam("code") String code, HttpSession session) {
			// First we must exchange that code for an access token.
			String accessToken = googleService.getGoogleAccessToken(code);
			// Then we can use that access token to get information about the user and other things.
			User githubUser = googleService.getUserFromGoogleApi(accessToken);

			// Check to see if the user is already in our database.
			User user = userDao.findByGithubId(googleUser.getGoogleId());
			if (user == null) {
				// if not, add them.
				user = githubUser;
				userDao.save(user);
			}

			// Set the user on the session, just like the other type of login.
			session.setAttribute("user", user);
			// In some apps, you need the access token later, so throw that on the session, too.
			session.setAttribute("googleAccessToken", accessToken);

			return "redirect:/";
		}
}
