package com.example.demo.demoController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.demoService.SignUpService;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.UserIncorrectException;
import com.example.demo.request.UserRequest;
import com.example.demo.util.JwtUtil;

@RestController
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	 	
	 
	@Autowired
	private SignUpService signUpService;
	 
	 private static final Logger LOG = LoggerFactory.getLogger(AuthenticateController.class);

	 
	
	@PostMapping("/authenticate")
	public String authenticate(@RequestBody UserRequest userRequest) throws Exception {
		try {
			 LOG.info("Authenticating the User");
		     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword()));
		    }catch (Exception e) {
		    	LOG.info("Error While Authenticating User!");
		    	//throw new Exception("User not Found");
		   throw new UserIncorrectException(userRequest.getUserName(), userRequest.getPassword());
		}
		 LOG.info("Authenticating User Done");
		 return jwtUtil.generateToken(userRequest.getUserName());
		  
		}
	
	@PostMapping("/signUp")
	public ResponseEntity<Object> signUp(@RequestBody UserRequest userRequest) {
		LOG.info("Inside Singup Method!");
	    return signUpService.signUp(userRequest);
		//return new ResponseEntity<Object>(response);
	}
	
	
	
	

}
