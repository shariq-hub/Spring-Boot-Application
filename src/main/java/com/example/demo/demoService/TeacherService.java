package com.example.demo.demoService;

import com.example.demo.request.TeacherRequest;

public interface TeacherService {
	
	public void AddTeacher(TeacherRequest teacherRequest);
	public TeacherRequest getTeacher(int id);
	public void updateTeacher(TeacherRequest teacherRequest,int id);
	public void deleteTeacher(int id);

}
