package com.example.demo.DAO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Donor;
import com.example.demo.dto.BloodGroupUnitProjection;

@Repository
public interface DonorRepository extends JpaRepository<Donor, String> {
		
	Donor findTopByAadhaarOrderByDonationDateDesc(String aadhaar);
	
	@Query("SELECT d.bloodGroup AS bloodGroup, SUM(d.unitsDonated) AS totalunitsDonated FROM Donor d GROUP BY d.bloodGroup")
	List<BloodGroupUnitProjection> findTotalunitsDonatedBybloodGroup();

}
