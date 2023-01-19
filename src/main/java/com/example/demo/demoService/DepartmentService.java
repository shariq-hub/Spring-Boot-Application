package com.example.demo.demoService;


import java.util.List;

import com.example.demo.DemoEntity.DepartmentEntity;
import com.example.demo.request.DepartmentRequest;

public interface DepartmentService {

	public void AddDepartment(DepartmentRequest departmentRequest);
	public DepartmentRequest getDepartment(int id);
	//public DepartmentEntity returnDeparmentObj();
	public void deleteDepartment(int id);
	public List<DepartmentRequest> getAllDepartments(Integer pageNumber,Integer pageSize)throws Exception;

}
