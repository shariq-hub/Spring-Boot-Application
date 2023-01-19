package com.example.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user;
	private String fieldname;
	private int fieldvalue;
	public ResourceNotFoundException(String user, String fieldname, int fieldvalue) {
		super(String.format("%s not found with %s : %s", user,fieldname,fieldvalue));
		this.user = user;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}
	
	
	
	
	

}
