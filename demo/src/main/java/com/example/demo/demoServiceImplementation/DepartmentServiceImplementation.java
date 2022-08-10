package com.example.demo.demoServiceImplementation;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DemoEntity.DepartmentEntity;
import com.example.demo.DemoEntity.StudentEntity;
import com.example.demo.DemoEntity.TeacherEntity;
import com.example.demo.demoRepo.CourseEntityRepo;
import com.example.demo.demoRepo.DepartmentEntityRepo;
import com.example.demo.demoRepo.TeacherEntityRepo;
import com.example.demo.demoService.DepartmentService;
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
	public void AddDepartment(DepartmentRequest departmentRequest) {
		
		
			   ModelMapper modelMapper=new ModelMapper();
			   modelMapper.getConfiguration()
			   .setMatchingStrategy(MatchingStrategies.STRICT);
			   departmentRequest.getCourseEntity().forEach(x->{   // here I first move into the first course 
				   TeacherEntity teacherEntity = teacherEntityRepo.findById(x.getTeacher_id()).get();//Get First Teacher Details
				   x.setTeacherEntity(modelMapper.map(teacherEntity, TeacherRequest.class));  //mapped the teacher to entity, Convert Entity to Request
			   });
//				 department=new DepartmentEntity();
			   DepartmentEntity	department=modelMapper.map(departmentRequest,DepartmentEntity.class);
			   
			   departmentEntityRepo.save(department);	
		
}
	
	
	public DepartmentRequest getDepartment(int id) {
		ModelMapper modelMapper=new ModelMapper();
		DepartmentRequest departmentRequest=new DepartmentRequest();
		 DepartmentEntity departmentEntity=new DepartmentEntity();
		//TeacherEntity teacherEntity=new TeacherEntity();
		departmentEntity=departmentEntityRepo.findById(id).get();
		departmentRequest=modelMapper.map(departmentEntity, DepartmentRequest.class);
		//teacherEntity=teacherEntityRepo.findById(departmentRequest.getCourseEntity().get())
		return departmentRequest;
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