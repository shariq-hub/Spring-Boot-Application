package com.example.demo.request;

import lombok.Data;

@Data
public class CourseRequest {
	
	private int id;
	
	private String course_name;

	private TeacherRequest teacherEntity;
	
}
