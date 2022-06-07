package com.emp.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "profile")
public class EmployeeProfile {

	@Id
	private Long id;

	@NotEmpty(message = "first name Could not be empty")
	private String firstName;

	@NotEmpty(message = "last name could not be empty")
	private String lastName;

	@NotEmpty(message = "email could not be empty")
	@Email(message = "The formate of the email is Incorect")
	private String email;

	@NotEmpty(message = "username could not be empty")
	private String username;

	@NotEmpty(message = "password could not be empty")
	private String password;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private SubjectProfile subject;

	public SubjectProfile getSubject() {
		return subject;
	}

	public void setSubject(SubjectProfile subject) {
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
