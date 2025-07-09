package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.DAO.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                @RequestParam String aadhar,
                                Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";
        }

        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email already exists.");
            return "register";
        }
        if (userRepository.findByaadhar(aadhar).isPresent()) {
            model.addAttribute("error", "Aadhar already exists.");
            return "register";
        }
        // Save user to DB
        User newUser = new User(username, email, password,aadhar); // In production: hash password!
        userRepository.save(newUser);

        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    @PostMapping("/login")
    public String Login(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        if (userRepository.findByUsername(username).isPresent() && userRepository.findByUsername(username).get().getPassword().equals(password)) {
            session.setAttribute("user", userRepository.findByUsername(username).get());
            return "redirect:/"; // Redirect to home page
        }
        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "home";
    }
}

