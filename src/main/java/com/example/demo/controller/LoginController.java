package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.DAO.UserRepository;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                @RequestParam String aadhaar,
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
        if (userRepository.findByaadhaar(aadhaar).isPresent()) {
            model.addAttribute("error", "Aadhaar already exists.");
            return "register";
        }
        // Save user to DB
        User newUser = new User(username, email, password,aadhaar); // In production: hash password!
        userRepository.save(newUser);

        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    @PostMapping("/dologin")
    public String Login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model)  {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            User user = optionalUser.get();

            // ✅ Spring Security authentication
            Authentication auth = new UsernamePasswordAuthenticationToken(
            		user.getUsername(), null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
            

            // Save user in session (optional, if needed elsewhere)
            session.setAttribute("user", user);

            return "redirect:/";
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

