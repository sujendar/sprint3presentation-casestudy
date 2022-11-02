package com.example.service;

import java.util.List;

import com.example.entity.Course;

public interface CourseService {

	public List<Course> getCourse();
	
	public Course addCourse(Course course);
	
	public Course updateCourse(Course course);
	
	public void deleteCourse(long parseLong);
}
