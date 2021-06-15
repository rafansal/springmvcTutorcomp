package com.tutorcomp.dao;

import java.util.List;

import org.hibernate.Session;

import com.tutorcomp.entity.Seminar;

public interface SeminarDao {

	List<Seminar> getSeminars();

	public void deleteSeminar(int id);
	
	public void deleteStudentSeminars(int studentId,Session session);
	
	public void deleteTutorSeminars(int tutorId,Session session);

	public void saveSeminar(Seminar theSeminar);

	Seminar getSeminar(int theId);

	List<Seminar> getSeminarsForId(int studentId,String type);

}
