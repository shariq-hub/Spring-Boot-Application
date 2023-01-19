package com.example.demo.demoController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DemoEntity.StudentEntity;
import com.example.demo.demoService.CourseService;
import com.example.demo.request.ApiResponse;
import com.example.demo.request.CourseRequest;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	

	@PostMapping("/{dep_id}/{tea_id}")
	public ResponseEntity<Object> addCourse(@RequestBody CourseRequest courseRequest,@PathVariable(name="dep_id")int department_id,@PathVariable(name="tea_id")int teacher_id){
		courseService.AddCourse(courseRequest, department_id, teacher_id);
		return new ResponseEntity<>("Course Added Successfully",HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCourse(@PathVariable int id) {
		CourseRequest courseRequest=courseService.getCourse(id);
		return new ResponseEntity<Object>(courseRequest,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}/{tea_id}")
	public ResponseEntity<Object> updateCourse(@RequestBody CourseRequest courseRequest,@PathVariable int id,@PathVariable(name="tea_id") int tea_id){	
		courseService.updateCourse(courseRequest, id,tea_id);
		return new ResponseEntity<Object>(new ApiResponse("Course updated Successfully",true),HttpStatus.OK);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCourse(@PathVariable int id){
		courseService.deleteCourse(id);
		return new ResponseEntity<Object>(new ApiResponse("Course Deleted Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/getAllCourse")
	public ResponseEntity<Object> getAllCourses(@RequestParam(value = "pageNumber",defaultValue = "0" , required = false) Integer pageNumber
	,@RequestParam(value = "pageSize", defaultValue = "10" ,required = false) Integer pageSize)  throws Exception{
	List<CourseRequest> listofCourse=	courseService.getAllCourse(pageNumber, pageSize);
	return new ResponseEntity<Object>(listofCourse,HttpStatus.OK);
	
	}
	
	

}
