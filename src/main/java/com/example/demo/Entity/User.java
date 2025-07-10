package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(length = 12 , nullable = false , unique = true)
    private String aadhaar;


	public User() {}

    public User(String username, String email, String password, String aadhaar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.aadhaar = aadhaar;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getAadhar() {
		return aadhaar;
	}

	public void setAadhar(String aadhar) {
		this.aadhaar = aadhar;
	}

	public String getUsername() {
		return username; 
	}
    public void setUsername(String username) {
    	this.username = username; 
    }

    public String getEmail() { 
    	return email; 
    }
    public void setEmail(String email) {
    	this.email = email; 
    }

    public String getPassword() {
    	return password; 
    }
    public void setPassword(String password) {
    	this.password = password; 
    }
}
