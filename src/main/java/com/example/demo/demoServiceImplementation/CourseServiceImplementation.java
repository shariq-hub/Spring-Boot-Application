package com.example.demo.demoServiceImplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.el.LambdaExpression;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DemoEntity.CourseEntity;
import com.example.demo.DemoEntity.DepartmentEntity;
import com.example.demo.DemoEntity.TeacherEntity;
import com.example.demo.demoRepo.CourseEntityRepo;
import com.example.demo.demoRepo.DepartmentEntityRepo;
import com.example.demo.demoRepo.TeacherEntityRepo;
import com.example.demo.demoService.CourseService;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.request.CourseRequest;


@Service
public class CourseServiceImplementation implements CourseService {
	@Autowired
	CourseEntityRepo courseEntityRepo;
	@Autowired
	TeacherEntityRepo teacherEntityRepo;
	
	@Autowired
	DepartmentEntityRepo departmentEntityRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
    private static final Logger LOG = LoggerFactory.getLogger(CourseServiceImplementation.class);


	
	public void AddCourse(CourseRequest courseRequest,int dep_id,int tea_id) {
		LOG.info("Inside AddCourse Method");
		CourseEntity courseEntity=new CourseEntity();
		DepartmentEntity departmentEntity=new DepartmentEntity();
		TeacherEntity teacherEntity=new TeacherEntity();
		LOG.info("Finding Teacher against Course");
		teacherEntity=teacherEntityRepo.findById(tea_id).orElseThrow(()->new ResourceNotFoundException("Teacher", "Teacher id:", tea_id));
		departmentEntity=departmentEntityRepo.findById(dep_id).orElseThrow(()->new ResourceNotFoundException("Department", "Department id:", tea_id));
		courseEntity=modelMapper.map(courseRequest, CourseEntity.class);
		courseEntity.setTeacherEntity(teacherEntity);
		courseEntity.setDepartmentEntity(departmentEntity);
		LOG.info("Saving User Information");
		courseEntityRepo.save(courseEntity);
	}
	
	
	
    public CourseRequest getCourse(int id) {
    	//page numner, Page Size
    	//PageRequest p=PageRequest.of(2, 10);
    	CourseEntity courseEntity=courseEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course","Course_id",id));
    	//Page<CourseEntity> a=courseEntityRepo.findAll(p);
    	//LOG.info("Message is "+a.toString());
    	ModelMapper modelMapper=new ModelMapper();
    	CourseRequest courseRequest=new CourseRequest();
    	courseRequest=modelMapper.map(courseEntity, CourseRequest.class);
    	return courseRequest;
    }

    public void updateCourse(CourseRequest courseRequest,int id,int tea_id) {
    	CourseEntity courseEntity=new CourseEntity();
    	TeacherEntity teacherEntity=new TeacherEntity();
    	courseEntity=courseEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course","Course_id",id));
    	teacherEntity=teacherEntityRepo.findById(tea_id).orElseThrow(()-> new ResourceNotFoundException("Teacher","Teacher_id",tea_id));
    	courseEntity.setCourse_name(courseRequest.getCourse_name());
    	courseEntity.setTeacherEntity(teacherEntity);
    	
    	//courseEntity.setTeacherEntity(modelMapper.map(courseRequest.getTeacherEntity(),TeacherEntity.class));
    	//courseEntity.getTeacherEntity().setId(courseRequest.getTeacher_id());
    	//courseEntity.setTeacherEntity(courseRequest.getTeacher_id());
    	courseEntityRepo.save(courseEntity);
    	
    }
    
    public void deleteCourse(int id) {
    	CourseEntity courseEntity=courseEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course","Course_id",id));
    	courseEntityRepo.delete(courseEntity);
    }



	@Override
	public List<CourseRequest> getAllCourse(Integer pageNumber, Integer pageSize) throws Exception {
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<CourseEntity> pageList= courseEntityRepo.findAll(pageable);
		if(pageList.getContent()!=null) {
			throw new Exception("Courses not Found!");
		}
		List<CourseEntity> courseList=new ArrayList<>();
		courseList=pageList.getContent();
		List<CourseRequest> courseRequestList=courseList.stream().map((course)->this.modelMapper.map(course, CourseRequest.class))
				.collect(Collectors.toList());
		return courseRequestList;
	}

	
	

}
