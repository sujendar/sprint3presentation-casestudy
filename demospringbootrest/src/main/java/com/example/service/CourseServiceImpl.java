package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CourseDao;
import com.example.entity.Course;
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
	private CourseDao courseDao;
	@Override
	public List<Course> getCourse() {
		// TODO Auto-generated method stub
		return courseDao.findAll();
	}

	@Override
	public Course addCourse(Course course) {
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.save(course);
		return course;
	}

	@Override
	public void deleteCourse(long parseLong) {
		// TODO Auto-generated method stub
		Course entity= courseDao.getOne(parseLong);
		courseDao.delete(entity);
	}

}
