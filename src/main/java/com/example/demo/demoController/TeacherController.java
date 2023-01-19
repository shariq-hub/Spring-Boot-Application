package com.example.demo.demoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.demoRepo.TeacherEntityRepo;
import com.example.demo.demoService.TeacherService;
import com.example.demo.request.ApiResponse;
import com.example.demo.request.TeacherRequest;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	TeacherEntityRepo teacherEntityRepo;
	
	
	@PostMapping("/")
    public ResponseEntity<Object> AddTeacher(
       @RequestBody TeacherRequest teacherRequest)
    {
		 teacherService.AddTeacher(teacherRequest);
         return new ResponseEntity<Object>(new ApiResponse("Teacher Created Successfully",true),HttpStatus.CREATED);
    }
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getTeacher(@PathVariable(name="id") int id) {
		TeacherRequest teacherRequest=new TeacherRequest();
	     teacherRequest=teacherService.getTeacher(id);
		 return new ResponseEntity<Object>(teacherRequest,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTeacher(@PathVariable int id,@RequestBody TeacherRequest teacherRequest){
			teacherService.updateTeacher(teacherRequest, id);
			return new ResponseEntity<Object>(new ApiResponse("Teacher Updated Successfully",true),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTeacher(@PathVariable int id){
		teacherService.deleteTeacher(id);
		return new ResponseEntity<Object>(new ApiResponse("Teacher Deleted Successfully",true),HttpStatus.OK);
	}
	
}
