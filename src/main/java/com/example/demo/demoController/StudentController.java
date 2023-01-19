package com.example.demo.demoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.demoRepo.DepartmentEntityRepo;
import com.example.demo.demoRepo.StudentEntityRepo;
import com.example.demo.demoService.StudentService;
import com.example.demo.request.ApiResponse;
import com.example.demo.request.StudentRequest;
@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentEntityRepo studentEntityRepo;
	
	@Autowired
	DepartmentEntityRepo departmentEntityRepo;
	
	
	@PostMapping("/{id}")
    public ResponseEntity<Object> addStudent(
       @RequestBody StudentRequest studentRequest,@PathVariable(name="id") Integer department_id)
    {
			 studentService.AddStudent(studentRequest,department_id);	
			 return new ResponseEntity<Object>(new ApiResponse("Student Added Successfully",true),HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getStudent(@PathVariable(name="id") int id) {
		StudentRequest studentRequest=new StudentRequest();
		studentRequest=studentService.getStudent(id);
		return new ResponseEntity<Object>(studentRequest,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable(name="id") int id,@RequestBody StudentRequest studentRequest){
		studentService.updateStudent(id,studentRequest);
		return new ResponseEntity<Object>(new ApiResponse("Student Updated Successfully",true),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable int id){
		studentService.deleteStudent(id);
		return new ResponseEntity<Object>(new ApiResponse("Student Deleted Successfully",true),HttpStatus.OK);
	}
	
	
	}
	