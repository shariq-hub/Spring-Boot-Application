package com.example.demo.demoServiceImplementation;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DemoEntity.UserEntity;
import com.example.demo.demoRepo.UserRepo;
import com.example.demo.demoService.SignUpService;
import com.example.demo.request.ApiResponse;
import com.example.demo.request.UserRequest;

@Service
public class SignUpServiceImpl implements SignUpService{
	
	@Autowired
	UserRepo userRepo;

//	@Autowired
//	ModelMapper modelMapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(SignUpServiceImpl.class);

	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public ResponseEntity<Object> signUp(UserRequest userRequest) {
		LOG.info("Creating User With Request "+userRequest.toString());
		UserEntity userEntity=new UserEntity();
		userEntity.setUsername(userRequest.getUserName());
		userEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
	//	UserEntity saveUser=null;
		UserEntity findByUsername = userRepo.findByUsername(userRequest.getUserName());
		if(findByUsername!=null&&findByUsername.getUsername().equals(userRequest.getUserName())) {
			LOG.info("User Already Exist With This Following UserName "+userRequest.toString());
			//return new ResponseEntity<Object>(findByUsername,HttpStatus.FOUND);
			ApiResponse apiResponse=new ApiResponse("User Already Exist",false);
//			apiResponse.setMessage("User Already Exist!");
//			apiResponse.setSuccess(false);
		return new ResponseEntity<Object>(apiResponse,HttpStatus.FOUND);
		}
		else {	
		ApiResponse apiResponse=new ApiResponse("User Created Successfully",true);
		LOG.info("User Created Successfully! ");
		userRepo.save(userEntity);
		return new ResponseEntity<Object>(apiResponse,HttpStatus.CREATED);
		}
	}

}
