package com.tutorcomp.dao;

import java.util.List;

import com.tutorcomp.entity.Tutor;

public interface TutorDao {
	List<Tutor> getTutors();

	void deleteTutor(int id);

	void saveTutor(Tutor theTutor);

	Tutor getTutor(int theId);

	Tutor getStudentWithUserId(int userId);
}
