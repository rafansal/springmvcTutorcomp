package com.tutorcomp.service;

import java.util.List;

import com.tutorcomp.entity.Student;

public interface AdminService {

	public List<Student> getStudents();

	public void saveStudent(Student theStudent);

	public Student getStudent(int theId);

	public void deleteStudent(int theId);

}
