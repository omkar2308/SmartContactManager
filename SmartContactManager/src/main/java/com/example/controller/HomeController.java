package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.UserRepository;
import com.example.entities.Contact;
import com.example.entities.User;

@Controller
public class HomeController {

//	@Autowired
//	private UserRepository userRepository;
//	
//	@GetMapping("/test")
//	@ResponseBody
//	public String test() {
//		
//		User user = new User();
//		user.setName("omkar hajare");
//		user.setEmail("omkar@gmail.com");
//		
//		Contact contact = new Contact();
//		user.getContacts().add(contact);
//		
//		userRepository.save(user);
//		return "working";
//	}

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Model m) {

		m.addAttribute("title", "Home-Smart Contact");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model m) {

		m.addAttribute("title", "About-Smart Contact");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model m) {

		m.addAttribute("title", "Register-Smart Contact");
		m.addAttribute("user", new User());
		return "signup";
	}

//	handler for registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m) {
		if(!agreement) {
			System.out.println("you have not aggred terms and conditions");
		}
		
		user.setRole("ROAL_USER");
		user.setEnabled(true);
		user.setImageUrl("default.png");
		
		User result = this.userRepository.save(user);
		m.addAttribute("user",result);
		
		System.out.println("agreement " + agreement);
		System.out.println("user" + user);
		return "signup";
	}
}
