package com.emp.main.JWT;

public class response {
	
	private String firstName;
	private String token;
	private String password;
	private String username;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public response() {
		super();
		// TODO Auto-generated constructor stub
	}
	public response(String firstName, String token, String password, String username) {
		super();
		this.firstName = firstName;
		this.token = token;
		this.password = password;
		this.username = username;
	}
	
	
	
	
	


}
