package com.example.demo.demoService;

import com.example.demo.request.StudentRequest;

public interface StudentService {
	
	public void AddStudent(StudentRequest studentRequest,int id);
	public StudentRequest getStudent(int id);
	public void updateStudent(int id,StudentRequest studentEntity);
	public void deleteStudent(int id);
}
