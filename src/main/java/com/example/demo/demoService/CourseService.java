package com.example.demo.demoService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.request.CourseRequest;

public interface CourseService {
	
	public void AddCourse(CourseRequest courseRequest,int department_id,int teacher_id);
	public CourseRequest getCourse(int id);
	public void updateCourse(CourseRequest courseRequest,int id,int tea_id);
	public void deleteCourse(int id);
	public List<CourseRequest> getAllCourse(Integer pageNumber,Integer pageSize) throws Exception;
}
