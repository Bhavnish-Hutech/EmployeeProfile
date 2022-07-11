package com.emp.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emp.main.CustomExceptions.ExistException;
import com.emp.main.CustomExceptions.ResourceNotFoundException;
import com.emp.main.GeneralResponse.generalresponse;
import com.emp.main.model.EmployeeProfile;
import com.emp.main.repository.EmployeeRepository;

@Service
public class EmployeeService implements UserDetailsService {

	@Autowired
	private EmployeeRepository empRepo;
	
	public generalresponse saveEmployeeProfile(EmployeeProfile save) throws ExistException {
		generalresponse show = new generalresponse();
		String tempEmail = save.getEmail();
		EmployeeProfile profile = this.fetchEmployeeProfileByEmail(tempEmail);
		boolean exist = empRepo.existsById(save.getId());
		if (exist) {
			throw new ExistException("this Id is already exist:");

		}

		if (profile != null) {
			throw new ExistException("this emailId is already exist:");
		}
		
		empRepo.save(save);
		return show;
	}

	public EmployeeProfile fetchEmployeeProfileByEmail(String email) {
		return empRepo.findByEmail(email);
	}

	public EmployeeProfile getEmployeeProfileByUsername(String username) {
		return empRepo.findByUsername(username);
	}

	public List<EmployeeProfile> getEmployeeProfiles() {
		return empRepo.findAll();

	}

	public EmployeeProfile getEmploeeProfileById(Long id) throws ResourceNotFoundException {
		return empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found :" + id));
	}

	public EmployeeProfile getEmployeeProfileByFirstName(String firstName){

		return empRepo.findByFirstName(firstName);

	}

	public String deleteEmployeeProfile(Long id) {
		if (empRepo.existsById(id)) {
			empRepo.deleteById(id);
			return "Employee Deleted!!" + id;
		} else {
			throw new ResourceNotFoundException("user not found:" + id);
		}
	}

	public EmployeeProfile updateEmployeeProfile(EmployeeProfile update) {
		EmployeeProfile existingEmployeeProfile = empRepo.findById(update.getId())
				.orElseThrow(() -> new ResourceNotFoundException("user not found" + update));
		existingEmployeeProfile.setFirstName(update.getFirstName());
		existingEmployeeProfile.setLastName(update.getLastName());
		existingEmployeeProfile.setEmail(update.getEmail());
		existingEmployeeProfile.setPassword(update.getPassword());
		return empRepo.save(existingEmployeeProfile);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeProfile profile = empRepo.findByUsername(username);
		if (profile == null) {
			throw new ExistException("user not found");
		}
		return new org.springframework.security.core.userdetails.User(profile.getUsername(), profile.getPassword(),
				new ArrayList<>());
	}

}
