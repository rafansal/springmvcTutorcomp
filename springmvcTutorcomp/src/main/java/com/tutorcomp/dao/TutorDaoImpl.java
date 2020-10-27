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

import com.tutorcomp.entity.Tutor;
import com.tutorcomp.entity.User;

@Repository
@SuppressWarnings("rawtypes")
public class TutorDaoImpl implements TutorDao {

	@Autowired
	private SessionFactory sessionFactory;

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
				stu.setUserName(stu.getUser().getUserName());
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
			Tutor book = session.byId(Tutor.class).load(id);
			session.remove(book);
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
			user.setPassword(theTutor.getPassword());
			user.setRole(1);
			user.setId(theTutor.getUserId());
			user.setUserName(theTutor.getUserName());
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
			theTutor.setUserId(theTutor.getUser().getId());
			theTutor.setUserName(theTutor.getUser().getUserName());
			theTutor.setPassword(theTutor.getUser().getPassword());
			return theTutor;
		} catch (Exception e) {
			System.out.println("TutorDaoImpl :: saveTutor :: ERROR :: " + e);
		}
		System.out.println("TutorDaoImpl :: saveTutor :: end");
		return null;
	}

}
