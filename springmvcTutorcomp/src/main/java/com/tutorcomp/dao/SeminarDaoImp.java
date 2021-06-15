package com.tutorcomp.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tutorcomp.entity.Seminar;
import com.tutorcomp.entity.Student;
import com.tutorcomp.entity.Tutor;

@Repository
@SuppressWarnings("rawtypes")
public class SeminarDaoImp extends ParentDAO implements SeminarDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private TutorDao tutorDao;

	@Override
	public List<Seminar> getSeminars() {
		System.out.println("SeminarDaoImpl :: getSeminars :: start");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Seminar> SeminarList;
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Seminar> cq = cb.createQuery(Seminar.class);
			Root<Seminar> root = cq.from(Seminar.class);
			cq.select(root);
//			cq.where(cb.equal(root.get("status"), serverConstants.active));	
			Query query = session.createQuery(cq);
			SeminarList = query.getResultList();
			for (Seminar semi : SeminarList) {
				semi.setDateString((semi.getDate() != null ? semi.getDate().toString() : ""));
				semi.setStudentName(semi.getStudent().getFirstName());
				semi.setTutorName(semi.getTutor().getFirstName());
			}

			return SeminarList;
		} catch (Exception e) {
			System.out.println("SeminarDaoImpl :: getSeminars :: ERROR :: " + e);
		}
		System.out.println("SeminarDaoImpl :: getSeminars :: end");
		return null;
	}

	@Override
	public void deleteSeminar(int id) {
		System.out.println("SeminarDaoImpl :: deleteSeminar :: start");
		try {
			Session session = sessionFactory.getCurrentSession();
			String query = "update Seminar model set model.status = :status where model.id = :id";
			Query createQuery = session.createQuery(query);
			createQuery.setParameter("id", id);
			createQuery.setParameter("status", serverConstants.deleted);
			createQuery.executeUpdate();
			session.flush();
		} catch (Exception e) {
			System.out.println("SeminarDaoImpl :: deleteSeminar :: ERROR :: " + e);
		}
		System.out.println("SeminarDaoImpl :: deleteSeminar :: end");
	}

	@Override
	public void saveSeminar(Seminar theSeminar) {
		System.out.println("SeminarDaoImpl :: saveSeminar :: start");
		Session currentSession = sessionFactory.getCurrentSession();
		try {
//			String sDate1="31/12/1998 23:50";
			if (theSeminar.getDateString() != "") {
				theSeminar.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(theSeminar.getDateString()));
			}
			Student student = new Student();
			student.setId(theSeminar.getStudentId());
			theSeminar.setStudent(student);
			Tutor tutor = new Tutor();
			tutor.setId(theSeminar.getTutorId());
			theSeminar.setTutor(tutor);
			theSeminar.getStudent().setId(theSeminar.getStudentId());
			theSeminar.setStatus(serverConstants.active);
			currentSession.saveOrUpdate(theSeminar);
		} catch (Exception e) {
			System.out.println("SeminarDaoImpl :: saveSeminar :: ERROR :: " + e);
		}
		System.out.println("SeminarDaoImpl :: saveSeminar :: end");
	}

	@Override
	public Seminar getSeminar(int theId) {
		System.out.println("SeminarDaoImpl :: saveSeminar :: start");
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Seminar theSeminar = currentSession.get(Seminar.class, theId);
			return theSeminar;
		} catch (Exception e) {
			System.out.println("SeminarDaoImpl :: saveSeminar :: ERROR :: " + e);
		}
		System.out.println("SeminarDaoImpl :: saveSeminar :: end");
		return null;
	}

	@Override
	public List<Seminar> getSeminarsForId(int userId, String type) {
		System.out.println("SeminarDaoImpl :: getSeminarsForId :: start");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Seminar> SeminarList;
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Seminar> cq = cb.createQuery(Seminar.class);
			Root<Seminar> root = cq.from(Seminar.class);
			cq.select(root);
			if (type == "student") {
				cq.where(cb.equal(root.get("student").get("userId"), userId));
			} else if (type == "tutor") {
				cq.where(cb.equal(root.get("tutor").get("userId"), userId));
			}
			cq.where(cb.equal(root.get("status"), serverConstants.active));			
			Query query = session.createQuery(cq);
			SeminarList = query.getResultList();
			for (Seminar semi : SeminarList) {
				semi.setDateString(semi.getDate().toString());
				semi.setStudentName(semi.getStudent().getFirstName());
				semi.setTutorName(semi.getTutor().getFirstName());
			}

			return SeminarList;
		} catch (Exception e) {
			System.out.println("SeminarDaoImpl :: getSeminarsForId :: ERROR :: " + e);
		}
		System.out.println("SeminarDaoImpl :: getSeminarsForId :: end");
		return null;
	}

	@Override
	public void deleteStudentSeminars(int studentId,Session session) {
		System.out.println("SeminarDaoImpl :: deleteStudentSeminars :: start");
		try {
			String query = "update Seminar model set model.status = :status where model.studentId = :studentId";
			Query createQuery = session.createQuery(query);
			createQuery.setParameter("studentId", studentId);
			createQuery.setParameter("status", serverConstants.deleted);
			createQuery.executeUpdate();
		} catch (Exception e) {
			System.out.println("SeminarDaoImpl :: deleteStudentSeminars :: ERROR :: " + e);
		}
		System.out.println("SeminarDaoImpl :: deleteStudentSeminars :: end");

	}

	@Override
	public void deleteTutorSeminars(int tutorId,Session session) {
		System.out.println("SeminarDaoImpl :: deleteStudentSeminars :: start");
		try {
			String query = "update Seminar model set model.status = :status where model.tutorId = :tutorId";
			Query createQuery = session.createQuery(query);
			createQuery.setParameter("tutorId", tutorId);
			createQuery.setParameter("status", serverConstants.deleted);
			createQuery.executeUpdate();
		} catch (Exception e) {
			System.out.println("SeminarDaoImpl :: deleteStudentSeminars :: ERROR :: " + e);
		}
		System.out.println("SeminarDaoImpl :: deleteStudentSeminars :: end");

	}

}
