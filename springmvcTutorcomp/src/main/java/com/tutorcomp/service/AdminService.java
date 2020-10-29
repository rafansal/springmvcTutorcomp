package com.tutorcomp.service;

import java.util.List;

import com.tutorcomp.entity.Seminar;
import com.tutorcomp.entity.Student;
import com.tutorcomp.entity.Tutor;

public interface AdminService {

	public List<Student> getStudents();

	public void saveStudent(Student theStudent);

	public Student getStudent(int theId);

	public void deleteStudent(int theId);
	
	public List<Tutor> getTutors();

	public void saveTutor(Tutor theTutor);

	public Tutor getTutor(int theId);

	public void deleteTutor(int theId);

	public void saveSeminar(Seminar theSeminar);

	public Seminar getSeminar(int theId);

	public void deleteSeminar(int theId);

	public List<Seminar> getSeminars();

}
