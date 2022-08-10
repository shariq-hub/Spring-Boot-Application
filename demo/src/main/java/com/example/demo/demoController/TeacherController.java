package com.example.demo.demoController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.example.demo.DemoEntity.TeacherEntity;
import com.example.demo.demoRepo.TeacherEntityRepo;
import com.example.demo.demoService.TeacherService;
import com.example.demo.request.TeacherRequest;

@RestController
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	TeacherEntityRepo teacherEntityRepo;
	
	@PostMapping("/AddTeacher")
    public ResponseEntity<Object> AddTeacher(
       @RequestBody TeacherRequest teacherRequest)
    {
		if(Teaexist(teacherRequest.getId())==false) { 
         teacherService.AddTeacher(teacherRequest);
         return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Teacher Already Exist With this id",HttpStatus.NOT_FOUND);
    }
	
	
	
	@GetMapping("/getTeacher/{id}")
	public ResponseEntity<Object> getTeacher(@PathVariable(name="id") int id) {
		TeacherRequest teacherRequest=new TeacherRequest();
		if(Teaexist(id)==true) {
		 teacherRequest=teacherService.getTeacher(id);
		 return new ResponseEntity<Object>(teacherRequest,HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Teacher Not Found With this id",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/updateTeacher/{id}")
	public ResponseEntity<Object> updateTeacher(@PathVariable int id,@RequestBody TeacherRequest teacherRequest){
		if(Teaexist(id)==true) {
			teacherService.updateTeacher(teacherRequest, id);
			return new ResponseEntity<Object>("Teacher Details with this id Updated Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Teacher Not Found With this Id",HttpStatus.NOT_FOUND); 
		
	}
	
	
	private boolean Teaexist(int id) {
		return teacherEntityRepo.existsById(id);
	}
}