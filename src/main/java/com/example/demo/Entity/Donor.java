package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Donor {
    @Id
    private String aadhaar;
    
	private String fullname;
    private String bloodGroup;
    private Integer age;
    private Integer unitsDonated;
    private LocalDate donationDate;
    
    public String getAddhar() {
		return aadhaar;
	}
	public void setAddhar(String addhar) {
		this.aadhaar = addhar;
	}
	public String getName() {
		return fullname;
	}
	public void setfullName(String name) {
		this.fullname = name;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getUnitsDonated() {
		return unitsDonated;
	}
	public void setUnitsDonated(int unitsDonated) {
		this.unitsDonated = unitsDonated;
	}
	public LocalDate getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(LocalDate donationDate) {
		this.donationDate = donationDate;
	}
	 public Donor() {
	    }
	public Donor(String aadhaar, String name, Integer age , String bloodGroup, Integer unitsDonated,
			LocalDate donationDate) {
		super();
		this.aadhaar = aadhaar;
		this.fullname = name;
		this.bloodGroup = bloodGroup;
		this.age = age;
		this.unitsDonated = unitsDonated;
		this.donationDate = donationDate;
	}
		

}

