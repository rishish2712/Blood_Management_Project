package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.DAO.DonorRepository;
import com.example.demo.dto.BloodGroupUnitProjection;

@Controller
public class InventoryController {
	
	@Autowired
	private DonorRepository donorRepository;
	
	@GetMapping("/inventory")
	public String inventory(Model model) {
	    List<BloodGroupUnitProjection> data = donorRepository.findTotalunitsDonatedBybloodGroup();

	    // Convert list to map for fast lookup
	    Map<String, Integer> unitMap = new HashMap<>();
	    for (BloodGroupUnitProjection entry : data) {
	        unitMap.put(entry.getBloodGroup(), entry.getTotalunitsDonated());
	    }

	    // Predefined blood types
	    List<String> bloodTypes = Arrays.asList("A+", "A−", "B+", "B−", "AB+", "AB−", "O+", "O−");

	    // Create list of map entries to send to Thymeleaf
	    List<Map<String, Object>> inventoryList = new ArrayList<>();
	    for (String type : bloodTypes) {
	        Map<String, Object> entry = new HashMap<>();
	        entry.put("bloodGroup", type);
	        entry.put("units", unitMap.getOrDefault(type, 0));
	        inventoryList.add(entry);
	    }
	    
	    model.addAttribute("inventory", inventoryList);
	    return "inventory";
	}
}
