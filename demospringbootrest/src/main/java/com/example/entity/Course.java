package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course {
public Course(long id, String title,String description) {
		super();
		this.id = id;
		this.title = title;
		this.description=description;
	}
@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
public Course() {
	super();
	// TODO Auto-generated constructor stub
}
@Id
private long id;
private String title;
private String description;

public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

}
