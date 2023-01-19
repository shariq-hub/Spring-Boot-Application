package com.example.demo.DemoEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
public class DepartmentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "Description")
	private String Description;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentEntity")
	@OneToMany(mappedBy = "departmentEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CourseEntity> courseEntity;
	

	
	

}
