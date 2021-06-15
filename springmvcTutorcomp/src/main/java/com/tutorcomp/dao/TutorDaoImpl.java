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
import com.tutorcomp.entity.Tutor;
import com.tutorcomp.entity.User;

@Repository
@SuppressWarnings("rawtypes")
public class TutorDaoImpl extends ParentDAO implements TutorDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SeminarDao seminarDao;

	@Override
	public List<Tutor> getTutors() {
		System.out.println("TutorDaoImpl :: getTutors :: start");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Tutor> TutorList;
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Tutor> cq = cb.createQuery(Tutor.class);
			Root<Tutor> root = cq.from(Tutor.class);
			cq.select(root);
			Query query = session.createQuery(cq);
			TutorList = query.getResultList();
			for (Tutor stu : TutorList) {
				stu.setUserId(stu.getUser().getId());
				stu.setPassword(stu.getUser().getPassword());
			}

			return TutorList;
		} catch (Exception e) {
			System.out.println("TutorDaoImpl :: getTutors :: ERROR :: " + e);
		}
		System.out.println("TutorDaoImpl :: getTutors :: end");
		return null;
	}

	@Override
	public void deleteTutor(int id) {
		System.out.println("TutorDaoImpl :: deleteTutor :: start");
		try {
			Session session = sessionFactory.getCurrentSession();
			
			//to delete all the seminar related with this tutor
			seminarDao.deleteTutorSeminars(id, session);
			
			String query = "update Tutor model set model.status = :status where model.id = :id";
			Query createQuery = session.createQuery(query);
			createQuery.setParameter("id", id);
			createQuery.setParameter("status", serverConstants.deleted);
			createQuery.executeUpdate();
		} catch (Exception e) {
			System.out.println("TutorDaoImpl :: deleteTutor :: ERROR :: " + e);
		}
		System.out.println("TutorDaoImpl :: deleteTutor :: end");
	}

	@Override
	public void saveTutor(Tutor theTutor) {
		System.out.println("TutorDaoImpl :: saveTutor :: start");
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			User user = new User();
			if(theTutor.getPassword() != "")
				user.setPassword(theTutor.getPassword());			
			user.setRole(2);
			user.setStatus(serverConstants.active);
			user.setId(theTutor.getUserId());
			user.setEmail(theTutor.getEmail());
			theTutor.setUser(user);
			currentSession.saveOrUpdate(theTutor);
		} catch (Exception e) {
			System.out.println("TutorDaoImpl :: saveTutor :: ERROR :: " + e);
		}
		System.out.println("TutorDaoImpl :: saveTutor :: end");
	}

	@Override
	public Tutor getTutor(int theId) {
		System.out.println("TutorDaoImpl :: saveTutor :: start");
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Tutor theTutor = currentSession.get(Tutor.class, theId);
			Tutor tutordto = theTutor.getDTO();
			tutordto.setUserId(tutordto.getUser().getId());
			tutordto.setPassword(tutordto.getUser().getPassword());
			return tutordto;
		} catch (Exception e) {
			System.out.println("TutorDaoImpl :: saveTutor :: ERROR :: " + e);
		}
		System.out.println("TutorDaoImpl :: saveTutor :: end");
		return null;
	}

	@Override
	public Tutor getStudentWithUserId(int userId) {
		System.out.println("StudentDaoImpl :: getStudentWithUserId :: start");
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Tutor tutor;
			CriteriaBuilder cb = currentSession.getCriteriaBuilder();
			CriteriaQuery<Tutor> cq = cb.createQuery(Tutor.class);
			Root<Tutor> root = cq.from(Tutor.class);
			cq.select(root);
			cq.where(cb.equal(root.get("userId"), userId));
			Query query = currentSession.createQuery(cq);
			tutor = (Tutor) query.getSingleResult();
			return tutor;
		} catch (Exception e) {
			System.out.println("StudentDaoImpl :: getStudentWithUserId :: ERROR :: " + e);
		}
		System.out.println("StudentDaoImpl :: getStudentWithUserId :: end");
		return null;
	}

}
