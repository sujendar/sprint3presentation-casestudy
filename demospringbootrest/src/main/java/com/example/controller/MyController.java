package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserRepo;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.service.CourseService;
import com.example.service.StudentService;

@RestController
@CrossOrigin("http://localhost:4200")
public class MyController {
    @Autowired
	private CourseService courseService;
    @Autowired
    private StudentService ss;

    @CrossOrigin("http://localhost:4200")
	@GetMapping("/course")
	public List<Course> getAll(){
		return courseService.getCourse();
		
	}
	@PostMapping("/course")
	public Course addCourse(@RequestBody Course course) {
		return courseService.addCourse(course);
	}
	@PutMapping("/course/{courseId}")
	public Course updateCourse(@RequestBody Course course) {
		return courseService.updateCourse(course);
		
		
	}
	@DeleteMapping("/course/{courseId}")
	public ResponseEntity<HttpStatus> deleteCoure( @PathVariable String courseId){
		try {
			courseService.deleteCourse(Long.valueOf(courseId));
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	//student
	
	@GetMapping("/student")
	public List<Student> getAllStudents(){
		return ss.getStudent();
		
	}
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student student) {
		return ss.addStudent(student);
	}
	
	@GetMapping("/student/{studentid}")
	public Student getStudent(@PathVariable Long studentid) {
		return ss.getStudent(studentid);
	}
	@PutMapping("/student/{studentid}")
	public Student updateStudent(@RequestBody Student student) {
		return ss.updateStudent(student);
		
		
	}
	@DeleteMapping("/student/{studentid}")
	public ResponseEntity<HttpStatus> deleteStudent( @PathVariable String studentid){
		try {
			ss.deleteStudent(Long.valueOf(studentid));
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	@Autowired
	public UserRepo repo;
    @RequestMapping("/User/login")
	public ResponseEntity<?> loginUser(@RequestBody User user){
    	User userda=repo.findByUserId(user.getUserId());
    	if(userda.getPassword().equals(user.getPassword())) {
    		return ResponseEntity.ok(userda);
    	}else {
    		return (ResponseEntity<?>) ResponseEntity.internalServerError(); 
    	}
	}
    @PostMapping("/User/save")
   	public ResponseEntity<?> saveUser(@RequestBody User user){
    	try {
       	User userda=repo.save(user);
       	return ResponseEntity.ok(userda);
    	}catch (Exception e) {
    		return (ResponseEntity<?>) ResponseEntity.internalServerError(); 
		}
       
   	}
	}

