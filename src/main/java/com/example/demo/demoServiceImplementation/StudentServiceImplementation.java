package com.example.demo.demoServiceImplementation;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DemoEntity.DepartmentEntity;
import com.example.demo.DemoEntity.StudentEntity;
import com.example.demo.demoRepo.DepartmentEntityRepo;
import com.example.demo.demoRepo.StudentEntityRepo;
import com.example.demo.demoService.StudentService;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.request.StudentRequest;

@Service
public class StudentServiceImplementation implements StudentService {
	@Autowired
	private StudentEntityRepo studentEntityRepo;
	@Autowired
	private DepartmentEntityRepo departmentEntityRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void AddStudent(StudentRequest studentRequest,int id) {
		DepartmentEntity departmentEntity=departmentEntityRepo.findById(id).get();
		StudentEntity studentEntity=modelMapper.map(studentRequest, StudentEntity.class);
		studentEntity.setDepartment_entity(departmentEntity);
		studentEntityRepo.save(studentEntity);
			}
	
	public StudentRequest getStudent(int id){
		ModelMapper modelMapper=new ModelMapper();
		StudentEntity studentEntity=new StudentEntity();
		studentEntity=studentEntityRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student","Student_id",id));
		StudentRequest studentRequest=modelMapper.map(studentEntity, StudentRequest.class);
		studentRequest.setDepartment_id(studentEntity.getDepartment_entity().getId());
		return studentRequest;		
	}
	
	public void updateStudent(int id,StudentRequest studentRequest) {
		//ModelMapper modelMapper=new ModelMapper();
		StudentEntity studentEntity=new StudentEntity();
		//StudentRequest studentRequest2=new StudentRequest();
		studentEntity=studentEntityRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student","Student_id",id));
		studentEntity.setName(studentRequest.getName());
		studentEntity.setRoll_no(studentRequest.getRoll_no());
		studentEntity.setEmail(studentRequest.getEmail());
		//DepartmentEntity departmentEntity=departmentEntityRepo.findById(studentRequest.getDepartment_id()).get(); // Get the Department
		//studentRequest.setDepartment_entity(departmentEntity);
		studentEntityRepo.save(studentEntity);	
		//studentRequest2=modelMapper.map(studentEntity,StudentRequest.class);
		//studentEntity.setDepartment_entity(departmentEntity);
		
		
	}
	
	public void deleteStudent(int id) {
		StudentEntity studentEntity=studentEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","Student_id",id));
		studentEntityRepo.delete(studentEntity);
	}
	
	
	
		}

	

