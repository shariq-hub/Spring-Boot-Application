package com.example.demo.demoService;


import com.example.demo.request.DepartmentRequest;

public interface DepartmentService {

	public void AddDepartment(DepartmentRequest departmentRequest);
	public DepartmentRequest getDepartment(int id);
	//public DepartmentEntity returnDeparmentObj();

}
