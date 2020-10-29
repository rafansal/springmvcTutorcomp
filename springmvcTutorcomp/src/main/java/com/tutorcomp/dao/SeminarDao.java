package com.tutorcomp.dao;

import java.util.List;

import com.tutorcomp.entity.Seminar;

public interface SeminarDao {

	List<Seminar> getSeminars();

	void deleteSeminar(int id);

	void saveSeminar(Seminar theSeminar);

	Seminar getSeminar(int theId);

	List<Seminar> getSeminarsForId(int studentId,String type);

}
