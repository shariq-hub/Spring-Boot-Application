package com.example.demo.demoRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DemoEntity.TeacherEntity;
@Repository
public interface TeacherEntityRepo extends JpaRepository<TeacherEntity, Integer> {
	


}
