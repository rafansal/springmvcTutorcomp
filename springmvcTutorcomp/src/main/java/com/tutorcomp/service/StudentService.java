package com.tutorcomp.service;

import java.util.List;

import com.tutorcomp.entity.Seminar;

public interface StudentService {
	List<Seminar> getStudentSeminar(int userId);
}
