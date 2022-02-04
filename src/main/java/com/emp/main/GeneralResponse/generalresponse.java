package com.emp.main.GeneralResponse;

public class generalresponse {
	
	private Integer status;
	private String message;
	private String response;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public generalresponse() {
		super();
		this.status = 200;
		this.message = "save sucess";
		this.response = "save";
	}
	
	
	

}
