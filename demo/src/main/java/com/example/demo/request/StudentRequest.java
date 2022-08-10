package com.example.demo.request;

import com.example.demo.DemoEntity.DepartmentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
public class StudentRequest {
	
	private int id;
	private int roll_no;
	private String name;
	private String email;
	private Integer department_id;
	@JsonIgnore
	private DepartmentEntity department_entity;
	

}
