package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImplimentation implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		this.studentRepository.save(student);
		
	}

	@Override
	public Student getStudentById(long id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = null;
		if (optional.isPresent()) {
			student = optional.get();
		} else {
			throw new RuntimeException(" student not found for id :: " + id);
		}
		return student;
	}

	@Override
	public List<Student> getoneStudents(String userid) {
		return studentRepository.findByUserid(userid);
	
	}

	@Override
	public List<Student> getoneStudents1(long id) {
		
		return studentRepository.findByid(id);
	}	
}
