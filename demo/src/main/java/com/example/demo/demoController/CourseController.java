package com.example.demo.demoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.demoRepo.CourseEntityRepo;
import com.example.demo.demoService.CourseService;
import com.example.demo.request.CourseRequest;

@RestController
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseEntityRepo courseEntityRepo;
	
	@PostMapping("/AddCourse")
	 public ResponseEntity<Object> AddCourse(
		       @RequestBody CourseRequest courseRequest)
		    {
		         courseService.AddCourse(courseRequest);
		         return new ResponseEntity<Object>("Course Added Successfully",HttpStatus.OK);
		    }
	
	@GetMapping("/getCourse/{id}")
	public ResponseEntity<Object> getCourse(@PathVariable int id) {
		CourseRequest courseRequest=courseService.getCourse(id);
		return new ResponseEntity<Object>(courseRequest,HttpStatus.OK);
	}
	
	
	@PostMapping("/updateCourse/{id}")
	public ResponseEntity<Object> updateCourse(@RequestBody CourseRequest courseRequest,@PathVariable int id){
		if(courseExist(id)) {
		courseService.updateCourse(courseRequest, id);
		return new ResponseEntity<Object>("Course Updated Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Course not Found with this id",HttpStatus.NOT_FOUND);
		
	}
	
	private boolean courseExist(int id) {
		return courseEntityRepo.existsById(id);
	}

}
