package com.example.demo.demoServiceImplementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DemoEntity.CourseEntity;
import com.example.demo.demoRepo.CourseEntityRepo;
import com.example.demo.demoService.CourseService;
import com.example.demo.request.CourseRequest;

@Service
public class CourseServiceImplementation implements CourseService {
	@Autowired
	CourseEntityRepo courseEntityRepo;
	
    public void AddCourse(CourseRequest courseRequest) {
			ModelMapper modelMapper=new ModelMapper();
			CourseEntity courseEntity=new CourseEntity();
			courseEntity=modelMapper.map(courseRequest,CourseEntity.class);
			courseEntityRepo.save(courseEntity);	
}
    
    public CourseRequest getCourse(int id) {
    	CourseEntity courseEntity=courseEntityRepo.findById(id).get();
    	ModelMapper modelMapper=new ModelMapper();
    	CourseRequest courseRequest=new CourseRequest();
    	courseRequest=modelMapper.map(courseEntity, CourseRequest.class);
    	courseRequest.setTeacher_id(courseEntity.getTeacherEntity().getId());
    	return courseRequest;
    }

    public void updateCourse(CourseRequest courseRequest,int id) {
    	CourseEntity courseEntity=new CourseEntity();
    	courseEntity=courseEntityRepo.findById(id).get();
    	courseEntity.setCourse_name(courseRequest.getCourse_name());
    	//courseEntity.getTeacherEntity().setId(courseRequest.getTeacher_id());
    	//courseEntity.setTeacherEntity(courseRequest.getTeacher_id());
    	courseEntityRepo.save(courseEntity);
    	
    }
	

}