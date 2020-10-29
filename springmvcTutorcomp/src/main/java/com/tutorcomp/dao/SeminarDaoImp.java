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
public class SeminarDaoImp implements SeminarDao {

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
			Query query = session.createQuery(cq);
			SeminarList = query.getResultList();
			for (Seminar semi : SeminarList) {
				semi.setDateString((semi.getDate() != null? semi.getDate().toString():""));
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
			Seminar book = session.byId(Seminar.class).load(id);
			session.remove(book);
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
			if(theSeminar.getDateString() != "") {
				theSeminar.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(theSeminar.getDateString()));
			}
			Student student = new Student();
			student.setId(theSeminar.getStudentId());
			theSeminar.setStudent(student);
			Tutor tutor = new Tutor();
			tutor.setId(theSeminar.getTutorId());
			theSeminar.setTutor(tutor);
			theSeminar.getStudent().setId(theSeminar.getStudentId());
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
			if(type=="student") {
				cq.where(cb.equal(root.get("student").get("userId"), userId));
			}
			else if (type=="tutor") {
				cq.where(cb.equal(root.get("tutor").get("userId"), userId));
			}
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
	
	
}
