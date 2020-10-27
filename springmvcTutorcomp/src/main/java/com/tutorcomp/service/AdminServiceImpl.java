package com.tutorcomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.tutorcomp.dao.StudentDao;
import com.tutorcomp.dao.TutorDao;
import com.tutorcomp.entity.Student;
import com.tutorcomp.entity.Tutor;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	StudentDao studentDAO;
	
	@Autowired
	TutorDao tutorDAO;
	
	@Override
    @Transactional
    public List < Student > getStudents() {
        return studentDAO.getStudents();
    }

    @Override
    @Transactional
    public void saveStudent(Student theStudent) {
        studentDAO.saveStudent(theStudent);
    }

    @Override
    @Transactional
    public Student getStudent(int theId) {
        return studentDAO.getStudent(theId);
    }

    @Override
    @Transactional
    public void deleteStudent(int theId) {
        studentDAO.deleteStudent(theId);
    }
    
//----------------------------------------------------------------------------------------/
    
    @Override
    @Transactional
    public List < Tutor > getTutors() {
        return tutorDAO.getTutors();
    }

    @Override
    @Transactional
    public void saveTutor(Tutor theTutor) {
        tutorDAO.saveTutor(theTutor);
    }

    @Override
    @Transactional
    public Tutor getTutor(int theId) {
        return tutorDAO.getTutor(theId);
    }

    @Override
    @Transactional
    public void deleteTutor(int theId) {
        tutorDAO.deleteTutor(theId);
    }

}
