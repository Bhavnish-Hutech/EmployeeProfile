package com.emp.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.main.CustomExceptions.ExistException;
import com.emp.main.GeneralResponse.generalresponse;
import com.emp.main.JWT.jwthelper;
import com.emp.main.JWT.response;
import com.emp.main.model.EmployeeProfile;
import com.emp.main.repository.EmployeeRepository;
import com.emp.main.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private jwthelper helper;

	@PostMapping("/addEmployee")
	public generalresponse addEmployee(@Valid @RequestBody EmployeeProfile save) {
		String password = save.getPassword();
		String CrytedPassword = bCryptPasswordEncoder.encode(password);
		save.setPassword(CrytedPassword);
		return service.saveEmployeeProfile(save);
	}

	@GetMapping("/list/employees")
	public List<EmployeeProfile> findallEmployeeProfiles() {
		return service.getEmployeeProfiles();
	}

	@GetMapping("/employee/{id}")
	public EmployeeProfile findEmployeeProfileById(@PathVariable Long id) {
		return service.getEmploeeProfileById(id);
	}

	@GetMapping("/employee/{firstName}")
	public EmployeeProfile findEmployeeProfileByFirstName(@PathVariable String firstName) {

		return service.getEmployeeProfileByFirstName(firstName);
	}

	@PutMapping("/update/{id}")
	public EmployeeProfile UpdateEmployeeProfile(@Valid @RequestBody EmployeeProfile update) {

		String password = update.getPassword();
		String CrytedPassword = bCryptPasswordEncoder.encode(password);
		update.setPassword(CrytedPassword);

		return service.updateEmployeeProfile(update);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmployeeProfile(@PathVariable Long id) {

		return service.deleteEmployeeProfile(id);
	}

	@PostMapping("/token")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody response profile) throws ExistException {
		
		try {
		EmployeeProfile user = employeeRepository.findByUsername(profile.getUsername());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (user.getUsername().equals(profile.getUsername())
				&& passwordEncoder.matches(profile.getPassword(), user.getPassword())) {
		
			final UserDetails userdetails  = service.loadUserByUsername(profile.getUsername());
			final String jwt = helper.generateToken(userdetails);
			profile.setUsername(user.getUsername());
			profile.setFirstName(user.getFirstName());
			return ResponseEntity.ok(new response(profile.getFirstName(), jwt, profile.getUsername(), profile.getPassword()));
			}

		else {
			throw new ExistException("Profile is not found");
		}
		}
		catch (BadCredentialsException e) {
			throw new ExistException("username does not exist");
		}
		
	}
	
	
	
}
		
		

	
	
//	try {
//		new UsernamePasswordAuthenticationToken(profile.getUsername(), profile.getPassword());
//	} catch (BadCredentialsException e) {
//		throw new ExistException("username does not exist");
//	}
//
//	final UserDetails userDetails = service.loadUserByUsername(profile.getUsername());
//	final String jwt = helper.generateToken(userDetails);
//	return ResponseEntity.ok(new response(jwt));
//}
