package com.example.demo.exceptions;

public class UserIncorrectException extends RuntimeException {
	private String userName;
	private String password;
	public UserIncorrectException(String userName,String password) {
		super(String.format("UserName :%s or Password : %s not found ", userName,password));
		this.userName=userName;
		this.password=password;
		
	}
	

}
