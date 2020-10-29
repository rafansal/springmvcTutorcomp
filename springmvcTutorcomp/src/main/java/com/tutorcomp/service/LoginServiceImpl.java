package com.tutorcomp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorcomp.dao.UserDAO;
import com.tutorcomp.entity.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
    private UserDAO userDAO;
	
	@Override
	@Transactional
	public User checkLogin(User user) {
		User User;
		User = userDAO.findRole(user);
		return User;
	}

}
