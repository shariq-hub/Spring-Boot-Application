package com.example.demo.demoService;

import com.example.demo.request.CourseRequest;

public interface CourseService {
	
	public void AddCourse(CourseRequest courseRequest);
	public CourseRequest getCourse(int id);
	public void updateCourse(CourseRequest courseRequest,int id);

}