package com.example.service;

import java.util.List;

import com.example.entity.Course;
import com.example.entity.Student;

public interface StudentService {
public Student getStudent(long l);
	
	public Student addStudent(Student student);
	
	public Student updateStudent(Student student);
	
	public void deleteStudent(long parseLong);

	List<Student> getStudent();
}
