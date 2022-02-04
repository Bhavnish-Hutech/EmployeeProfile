package com.emp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.main.model.EmployeeProfile;
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeProfile, Long>{


	EmployeeProfile findByFirstName(String firstName);
	
	public EmployeeProfile findByEmail(String email);

	EmployeeProfile findByUsername(String username);


}
