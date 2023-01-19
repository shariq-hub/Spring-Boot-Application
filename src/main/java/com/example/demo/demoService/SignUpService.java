package com.example.demo.demoService;

import org.springframework.http.ResponseEntity;

import com.example.demo.request.UserRequest;

public interface SignUpService {
	
	public ResponseEntity<Object> signUp(UserRequest userRequest);

}
