package com.tutorcomp.service;

import java.util.List;

import com.tutorcomp.entity.Seminar;

public interface TutorService {
	List<Seminar> getTutorSeminar(int userId);
}
