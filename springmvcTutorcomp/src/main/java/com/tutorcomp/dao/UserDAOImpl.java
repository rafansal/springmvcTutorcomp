package com.tutorcomp.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tutorcomp.entity.Student;
import com.tutorcomp.entity.User;

@Repository
@SuppressWarnings("rawtypes")
public class UserDAOImpl extends ParentDAO implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findRole(User user) {
		System.out.println("UserDAOImpl :: findRole :: start");
		int role = -1;
		Session session = sessionFactory.getCurrentSession();
		try {
			
//			Criteria criteria = session.createCriteria(User.class);
//			criteria.add(Restrictions.eqOrIsNull("userName", user.getUserName()));
//			criteria.add(Restrictions.eqOrIsNull("password", user.getPassword()));
//			ProjectionList projectionList = Projections.projectionList();
//			projectionList.add(Projections.property("role"), "role");
//			criteria.setProjection(projectionList);
//			criteria.setResultTransformer(Transformers.aliasToBean(User.class));
//			User resulttest = (User) criteria.uniqueResult();
			if(user.getEmail() != null && user.getPassword() != null) {
			String query = "select new com.tutorcomp.entity.User(model.id,model.role) from User model where model.email = :email and model.password = :password";
			Query hqlQuery = session.createQuery(query);
			hqlQuery.setParameter("email", user.getEmail());
			hqlQuery.setParameter("password", user.getPassword());
//			System.out.println("role::"+hqlQuery.uniqueResult());
			User result = (User) hqlQuery.uniqueResult();
			return result;
			}
		} catch (Exception e) {
			System.out.println("UserDAOImpl :: findRole :: ERROR :: " + e);

		} finally {
		}
		System.out.println("UserDAOImpl :: findRole :: end");
		
		return null;
	}

}
