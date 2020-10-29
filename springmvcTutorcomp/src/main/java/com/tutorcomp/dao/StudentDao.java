package com.tutorcomp.dao;

import java.util.List;

import com.tutorcomp.entity.Student;

public interface StudentDao {

	List<Student> getStudents();

	void deleteStudent(int id);

	void saveStudent(Student theStudent);

	Student getStudent(int theId);

	Student getStudentWithUserId(int userId);

}
