package com.example.demo.demoRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.DemoEntity.CourseEntity;

public interface CourseEntityRepo extends JpaRepository<CourseEntity, Integer> {
	

}
