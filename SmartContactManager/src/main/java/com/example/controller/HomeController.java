package com.example.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.UserRepository;
import com.example.entities.Contact;
import com.example.entities.User;
import com.example.helper.Message;

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

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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

//	Handler for registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m,
			HttpSession session) {
		try {
//			if(!agreement) {
//				System.out.println("you have not aggred terms and conditions");
//				throw new Exception("you have not aggred terms and conditions");
//			}

			if (bindingResult.hasErrors()) {
				System.out.println("errors " + bindingResult.toString());
				m.addAttribute("user", user);
				return "signup";
			}

			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			System.out.println("agreement" + agreement);
			System.out.println("user" + user);

			User result = this.userRepository.save(user);
			m.addAttribute("user", new User());
			session.setAttribute("message", new Message("succesfully registered", "alert-success"));
			return "signup";

		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("user", user);
			session.setAttribute("message", new Message("somthing went wong...!" + e.getMessage(), "alert-danger"));
			return "signup";
		}

	}
//	handler for loging 
	
	@GetMapping("/signin")
	public String Login(Model m) {
		
		return "signin";
	}
	

}
