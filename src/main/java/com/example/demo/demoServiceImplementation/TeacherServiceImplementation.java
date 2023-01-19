package com.example.demo.demoServiceImplementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DemoEntity.TeacherEntity;
import com.example.demo.demoRepo.TeacherEntityRepo;
import com.example.demo.demoService.TeacherService;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.request.TeacherRequest;

@Service
public class TeacherServiceImplementation implements TeacherService {
	
	@Autowired
	TeacherEntityRepo teacherEntityRepo;
	
	public void AddTeacher(TeacherRequest teacherRequest) {
			ModelMapper modelMapper=new ModelMapper();
			TeacherEntity teacherEntity=new TeacherEntity();
			teacherEntity=modelMapper.map(teacherRequest,TeacherEntity.class);
			teacherEntityRepo.save(teacherEntity);	
	}
	
	public TeacherRequest getTeacher(int id) {
		TeacherRequest teacherRequest=new TeacherRequest();
		ModelMapper modelMapper=new ModelMapper();
		TeacherEntity teacherEntity=new TeacherEntity();
		teacherEntity=teacherEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teacher","Teacher_id",id));
		teacherRequest=modelMapper.map(teacherEntity, TeacherRequest.class);
		return teacherRequest;
	}
	
	public void updateTeacher(TeacherRequest teacherRequest,int id) {
		//ModelMapper modelMapper=new ModelMapper();
		TeacherEntity teacherEntity=new TeacherEntity();
		//TeacherRequest teacherRequest2=new TeacherRequest();
		teacherEntity=teacherEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teacher","Teacher_id",id));
		teacherEntity.setName(teacherRequest.getName());
		teacherEntity.setCell(teacherRequest.getCell());
		teacherEntity.setEmail(teacherRequest.getEmail());
		teacherEntityRepo.save(teacherEntity);
		//teacherRequest2=modelMapper.map(teacherEntity, TeacherRequest.class);
		//return teacherRequest2;
	}
	
	public void deleteTeacher(int id) {
		TeacherEntity teacherEntity=teacherEntityRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teacher","Teacher_id",id));
		teacherEntityRepo.delete(teacherEntity);
	}
}
