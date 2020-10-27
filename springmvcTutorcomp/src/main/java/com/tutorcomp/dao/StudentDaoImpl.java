package com.tutorcomp.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tutorcomp.entity.Student;
import com.tutorcomp.entity.User;

@Repository
@SuppressWarnings("rawtypes")
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Student> getStudents() {
		System.out.println("StudentDaoImpl :: getStudents :: start");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Student> StudentList;
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Student> cq = cb.createQuery(Student.class);
			Root<Student> root = cq.from(Student.class);
			cq.select(root);
			Query query = session.createQuery(cq);
			StudentList = query.getResultList();
			for (Student stu : StudentList) {
				stu.setUserId(stu.getUser().getId());
				stu.setUserName(stu.getUser().getUserName());
				stu.setPassword(stu.getUser().getPassword());
			}

			return StudentList;
		} catch (Exception e) {
			System.out.println("StudentDaoImpl :: getStudents :: ERROR :: " + e);
		}
		System.out.println("StudentDaoImpl :: getStudents :: end");
		return null;
	}

	@Override
	public void deleteStudent(int id) {
		System.out.println("StudentDaoImpl :: deleteStudent :: start");
		try {
			Session session = sessionFactory.getCurrentSession();
			Student book = session.byId(Student.class).load(id);
			session.delete(book);
		} catch (Exception e) {
			System.out.println("StudentDaoImpl :: deleteStudent :: ERROR :: " + e);
		}
		System.out.println("StudentDaoImpl :: deleteStudent :: end");
	}

	@Override
	public void saveStudent(Student theStudent) {
		System.out.println("StudentDaoImpl :: saveStudent :: start");
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			User user = new User();
			user.setPassword(theStudent.getPassword());
			user.setRole(1);
			user.setUserName(theStudent.getUserName());
			theStudent.setUser(user);
			currentSession.save(theStudent);
		} catch (Exception e) {
			System.out.println("StudentDaoImpl :: saveStudent :: ERROR :: " + e);
		}
		System.out.println("StudentDaoImpl :: saveStudent :: end");
	}

	@Override
	public Student getStudent(int theId) {
		System.out.println("StudentDaoImpl :: saveStudent :: start");
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Student theStudent = currentSession.get(Student.class, theId);
			theStudent.setUserId(theStudent.getUser().getId());
			theStudent.setUserName(theStudent.getUser().getUserName());
			theStudent.setPassword(theStudent.getUser().getPassword());
			return theStudent;
		} catch (Exception e) {
			System.out.println("StudentDaoImpl :: saveStudent :: ERROR :: " + e);
		}
		System.out.println("StudentDaoImpl :: saveStudent :: end");
		return null;
	}

}
