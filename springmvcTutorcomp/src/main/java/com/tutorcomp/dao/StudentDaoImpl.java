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

import com.tutorcomp.entity.Seminar;
import com.tutorcomp.entity.Student;
import com.tutorcomp.entity.User;

@Repository
@SuppressWarnings("rawtypes")
public class StudentDaoImpl extends ParentDAO implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SeminarDao seminarDao;

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
			
			//delete all seminar related with this student
			seminarDao.deleteStudentSeminars(id, session);
			
			String query = "update Student model set model.status = :status where model.id = :id";
			Query createQuery = session.createQuery(query);
			createQuery.setParameter("id", id);
			createQuery.setParameter("status", serverConstants.deleted);
			createQuery.executeUpdate();
			session.flush();
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
			if(theStudent.getPassword() != "")
				user.setPassword(theStudent.getPassword());
			user.setRole(1);
			user.setStatus(serverConstants.active);
			user.setId(theStudent.getUserId());
			user.setEmail(theStudent.getEmail());
			theStudent.setUser(user);
			theStudent.setStatus(serverConstants.active);
			currentSession.saveOrUpdate(theStudent);
		} catch (Exception e) {
			System.out.println("StudentDaoImpl :: saveStudent :: ERROR :: " + e);
		}
		System.out.println("StudentDaoImpl :: saveStudent :: end");
	}

	@Override
	public Student getStudent(int theId) {
		System.out.println("StudentDaoImpl :: getStudent :: start");
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Student theStudent = currentSession.get(Student.class, theId);
			Student studentdto = theStudent.getDTO();
			studentdto.setUserId(studentdto.getUser().getId());
			studentdto.setPassword(studentdto.getUser().getPassword());
			return studentdto;
		} catch (Exception e) {
			System.out.println("StudentDaoImpl :: getStudent :: ERROR :: " + e);
		}
		System.out.println("StudentDaoImpl :: getStudent :: end");
		return null;
	}
	
	@Override
	public Student getStudentWithUserId(int userId) {
		System.out.println("StudentDaoImpl :: getStudentWithUserId :: start");
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Student student;
			CriteriaBuilder cb = currentSession.getCriteriaBuilder();
			CriteriaQuery<Student> cq = cb.createQuery(Student.class);
			Root<Student> root = cq.from(Student.class);
			cq.select(root);
			cq.where(cb.equal(root.get("userId"), userId));
			Query query = currentSession.createQuery(cq);
			student = (Student) query.getSingleResult();
			return student;
		} catch (Exception e) {
			System.out.println("StudentDaoImpl :: getStudentWithUserId :: ERROR :: " + e);
		}
		System.out.println("StudentDaoImpl :: getStudentWithUserId :: end");
		return null;
	}

}
