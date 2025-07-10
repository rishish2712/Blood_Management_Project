package com.example.demo.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, String> {
	

}

