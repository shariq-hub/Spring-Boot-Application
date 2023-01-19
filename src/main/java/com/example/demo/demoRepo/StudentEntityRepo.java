package com.example.demo.demoRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DemoEntity.StudentEntity;

@Repository
public interface StudentEntityRepo extends JpaRepository<StudentEntity, Integer> {

}
