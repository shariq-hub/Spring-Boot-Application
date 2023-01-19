package com.example.demo.demoServiceImplementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.DemoEntity.DepartmentEntity;
import com.example.demo.DemoEntity.TeacherEntity;
import com.example.demo.demoRepo.CourseEntityRepo;
import com.example.demo.demoRepo.DepartmentEntityRepo;
import com.example.demo.demoRepo.TeacherEntityRepo;
import com.example.demo.demoService.DepartmentService;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.request.DepartmentRequest;
import com.example.demo.request.TeacherRequest;





@Service
public class DepartmentServiceImplementation implements DepartmentService {
	
	
	@Autowired
	DepartmentEntityRepo departmentEntityRepo;
	@Autowired
	CourseEntityRepo courseEntityRepo;
	@Autowired
	TeacherEntityRepo teacherEntityRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentServiceImplementation.class);

	
	
	public void AddDepartment(DepartmentRequest departmentRequest) {
		
		       LOG.info("Inside Add Department Method");
			 //  ModelMapper modelMapper=new ModelMapper();
//			   modelMapper.getConfiguration()
//			   .setMatchingStrategy(MatchingStrategies.STRICT);
//			   departmentRequest.getCourseEntity().forEach(x->{   // here I first move into the first course 
//				   TeacherEntity teacherEntity = teacherEntityRepo.findById(x.getTeacher_id()).orElseThrow(()->new ResourceNotFoundException("Teacher","Teacher_id",x.getTeacher_id()));//Get First Teacher Details
//				   x.setTeacherEntity(modelMapper.map(teacherEntity, TeacherRequest.class));  //mapped the teacher to entity, Convert Entity to Request
//			   });
//				 department=new DepartmentEntity();
			   DepartmentEntity	department=modelMapper.map(departmentRequest,DepartmentEntity.class);
			   
			   departmentEntityRepo.save(department);	
		

	
	}
	public DepartmentRequest getDepartment(int id) {
		LOG.info("Inside GetDepartment Method()");
		DepartmentRequest departmentRequest=new DepartmentRequest();
		DepartmentEntity departmentEntity=new DepartmentEntity();
		//TeacherEntity teacherEntity=new TeacherEntity();
		departmentEntity=departmentEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department","Department_id",id));
		departmentRequest=modelMapper.map(departmentEntity, DepartmentRequest.class);
		
		return departmentRequest;
	}
	
	public void deleteDepartment(int id) {
		DepartmentEntity departmentEntity=departmentEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department","Department_id",id));
		departmentEntityRepo.delete(departmentEntity);
	}
	
	public List<DepartmentRequest> getAllDepartments(Integer pageNumber,Integer pageSize) throws Exception {
		List<DepartmentEntity> alldepartments=new ArrayList<>();
//		int pageNumber=1;
//		int pageSize=5;
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<DepartmentEntity> p= departmentEntityRepo.findAll(pageable);
		if(p.getContent()==null) {
			throw new Exception("Department Not Found!");
		}
		alldepartments=p.getContent();
		List<DepartmentRequest> listOfDepartments=alldepartments.stream().map((department)->this.modelMapper.map(department, DepartmentRequest.class))
	    .collect(Collectors.toList());
		return listOfDepartments;
		
	}
	
	

	







	
	
	
	
	
//	public ResponseEntity<DepartmentRequest> getDepartment(int id){
//		if(departmentEntityRepo.findById(id)!=null) {
//			return new ResponseEntity<DepartmentRequest>(HttpStatus.OK);
//		}
//		return new ResponseEntity<DepartmentRequest>("Department Not found With this id",HttpStatus.NOT_FOUND);
//	}
	
//	private DepartmentRequest convertEntityToDto(DepartmentEntity departmentEntity){
//        DepartmentRequest departmentRequestdto = new DepartmentRequest();
//        departmentRequestdto.setName(departmentEntity.getName());
//        departmentRequestdto.setDescription(departmentEntity.getDescription());
//        departmentRequestdto.setCourseRequests(departmentEntity.getCourseEntity());
//        return userLocationDTO;
//    }
	
	
	
	}
