package com.example.demo.DemoEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class CourseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String course_name;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private TeacherEntity teacherEntity;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity departmentEntity;
	
	
	
//	@JsonIgnore
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "DEPARTMENT_ID")
//	private DepartmentEntity departmentEntity;

	
	

}
