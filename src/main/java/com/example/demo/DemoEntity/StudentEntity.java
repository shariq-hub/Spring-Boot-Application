package com.example.demo.DemoEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.request.StudentRequest;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;



import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int roll_no;
	@Column
	private String name;
	@Column
	private String email;
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity department_entity;
	
	
	
}
