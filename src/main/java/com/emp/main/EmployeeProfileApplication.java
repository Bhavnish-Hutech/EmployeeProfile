package com.emp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EmployeeProfileApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeProfileApplication.class, args);
	}

}


//model - arguments
//repository - interface
//service - handle exception also
//controller - create api
//security -  allow api to use  
//JWT token - generate token
//filter - filter the token 