package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import com.example.demo.Entity.*;

@Controller
public class controller {
	
	@GetMapping("/")
	public String homePage(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");
	    model.addAttribute("user", user);
	    return "home";
	}

	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
    public String ShowErrorRegisterationMessage(HttpSession session, Model model) {
    	
    	String errorMessage = (String) session.getAttribute("errorMessage");
        String googleEmail = (String) session.getAttribute("googleEmail");
        String googleName = (String) session.getAttribute("googleName");

        if (errorMessage != null) {
            model.addAttribute("error", errorMessage);
            session.removeAttribute("errorMessage");
        }

        if (googleEmail != null) {
            model.addAttribute("email", googleEmail);
            session.removeAttribute("googleEmail");
        }

        if (googleName != null) {
            model.addAttribute("name", googleName);
            session.removeAttribute("googleName");
        }
    	
    	return "register";
    }
	

}

