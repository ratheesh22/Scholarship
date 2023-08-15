package com.main;

import java.util.List;

import com.main.*;

public interface StudentService {
	List<Student> getAllStudents();
	void saveStudent(Student student);
	Student getStudentById(long id);
	List<Student> getoneStudents(String userid);
	List<Student> getoneStudents1(long id);

}
