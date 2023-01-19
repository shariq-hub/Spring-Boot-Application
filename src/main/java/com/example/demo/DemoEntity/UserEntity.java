package com.example.demo.DemoEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user1")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "my_seq", sequenceName = "my_seq", allocationSize = 1)
	private int id;
	@Column(unique = true)
	private String username;
	@Column
	private String password;
	
	
	
}
