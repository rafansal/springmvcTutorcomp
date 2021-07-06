package com.tutorcomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorcomp.dao.SeminarDao;
import com.tutorcomp.entity.Seminar;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
    private SeminarDao seminarDao;
	
	@Override
	@Transactional
	public List<Seminar> getStudentSeminar(int userId) {
		List<Seminar> seminars = seminarDao.getSeminarsForId(userId,"student");
		return seminars;
	}
	
	

}
