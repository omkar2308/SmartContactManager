package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping( "/home" )
	public String home(Model m) {
		
		m.addAttribute("title","Home-Smart Contact");
		return "home";
	}
	
	@RequestMapping( "/about" )
	public String about(Model m) {
		
		m.addAttribute("title","About-Smart Contact");
		return "about";
	}
}





