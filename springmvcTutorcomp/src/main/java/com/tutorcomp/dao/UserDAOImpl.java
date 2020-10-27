package com.tutorcomp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tutorcomp.entity.User;

@Repository
@SuppressWarnings("rawtypes")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int findRole(User user) {
		System.out.println("UserDAOImpl :: findRole :: start");
		int role = -1;
		Session session = sessionFactory.getCurrentSession();
		try {
			
			String query = "select new com.tutorcomp.entity.User(model.role) from User model where model.userName = :userName and model.password = :password";
			Query hqlQuery = session.createQuery(query);
			hqlQuery.setParameter("userName", user.getUserName());
			hqlQuery.setParameter("password", user.getPassword());
			System.out.println("role::"+hqlQuery.uniqueResult());
			Object result = hqlQuery.uniqueResult();
			if(result != null) {
				user = (User) result;
				role = user.getRole();
			}
			System.out.println("role::"+role);
			System.out.println("UserDAOImpl :: findRole :: end"+role);
		} catch (Exception e) {
			System.out.println("UserDAOImpl :: findRole :: ERROR :: " + e);

		} finally {
		}
		System.out.println("UserDAOImpl :: findRole :: end");
		
		return role;
	}

}
