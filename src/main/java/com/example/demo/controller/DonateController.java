package com.example.demo.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DAO.DonorRepository;
import com.example.demo.DAO.UserRepository;
import com.example.demo.Entity.Donor;
import com.example.demo.Entity.User;

import jakarta.servlet.http.HttpSession;


@Controller
public class DonateController {
	
	@Autowired
    private DonorRepository donorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean iseligible(String aadhaar) {
    	if(donorRepository.existsById(aadhaar)) {
    	    return false;
    	}
    	return true;
    }
	
	@PostMapping("/donate")
	public String handleDonation(@RequestParam String fullname,
			@RequestParam String aadhaar,
			@RequestParam Integer age, 
			@RequestParam Integer unitsDonated,
			@RequestParam String bloodGroup,
			@RequestParam LocalDate donationDate,
			@RequestParam String hospital,
							Model model) {
	
		Donor newdonor = new Donor(aadhaar, fullname, age, bloodGroup, unitsDonated, donationDate, hospital);
		 donorRepository.save(newdonor);
		 model.addAttribute("message", "Thank you for your donation!");
		return "redirect:/";
	}

	@GetMapping("/donate")
	public String donor(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");

	    if (user != null) {
	        // Fetch latest user from database to get updated aadhaar
	        Optional<User> optionalUser = userRepository.findByaadhaar(user.getAadhar());
	        
	        if (optionalUser.isPresent()) {
	            String aadhaar = optionalUser.get().getAadhar();
	            model.addAttribute("aadhaar", aadhaar);
	        }
	        boolean eligible = iseligible(user.getAadhar());
	        model.addAttribute("eligible", eligible);

	        if (!eligible) {
	            // Calculate next eligible date (last donation + 90 days)
	            Donor list = donorRepository.findTopByAadhaarOrderByDonationDateDesc(user.getAadhar());
	            LocalDate lastDonation = list.getDonationDate();
	            LocalDate nextEligible = lastDonation.plusDays(90);
	            model.addAttribute("nextDate", nextEligible.toString());
	        }
	       
	    }
	    
	    if(user == null) {
	    	return "login";
	    }
	    
	    
	    return "donate";
	}
	
	

}
