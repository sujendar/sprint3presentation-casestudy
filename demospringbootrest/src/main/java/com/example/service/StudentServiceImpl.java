package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentDao;
import com.example.entity.Student;
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
	private StudentDao studentDao;
	@Override
	public List<Student> getStudent() {
		// TODO Auto-generated method stub
		return studentDao.findAll();
	}

	@Override
	public Student addStudent(Student student) {
		studentDao.save(student);
		return student;
	}

	@Override
	public Student updateStudent(Student student) {
		studentDao.save(student);
		return student;
	}

	@Override
	public void deleteStudent(long parseLong) {
		Student student=studentDao.getById(parseLong);
		studentDao.delete(student);
		
	}

	@Override
	public Student getStudent(long studentId) {
		Student student=studentDao.findById(studentId).get();
		return student;
	}

}
