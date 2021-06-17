package com.example.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.UserRepository;
import com.example.entities.Contact;
import com.example.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

//	method for adding common data to response
	@ModelAttribute
	public void AddComanData(Model m, Principal principal) {

		String userName = principal.getName();
		System.out.println("username " + userName);

//		get user using username (email)

		User user = userRepository.getUserByUserName(userName);
		System.out.println("user " + user);
		m.addAttribute("user", user);

	}

//	home dashboard
	@RequestMapping("/index")
	public String index(Model m, Principal principal) {

		m.addAttribute("title", "user dashboard");
		return "normal/user_index";
	}

//	handler for add contact 
	@GetMapping("/add-contact")
	public String openAddContact(Model m) {

		m.addAttribute("title", "add contact");
		m.addAttribute("user", new User());
		return "normal/add_contacts";
	}

//	handler for process add contact form
	@PostMapping("/process-contact")
	public String processAddcontact(@Valid @ModelAttribute Contact contact, Principal principal,
			BindingResult bindingResult, Model m) {

		try {
			if (bindingResult.hasErrors()) {
				System.out.println("errors " + bindingResult.toString());
				m.addAttribute("contact", contact);
				return "normal/add_contacts";
			}

			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);

			contact.setUser(user);

			user.getContacts().add(contact);
			this.userRepository.save(user);

			System.out.println("data " + contact);

			return "normal/add_contacts";

		} catch (Exception e) {

		}
		return "normal/add_contacts";

	}

}
