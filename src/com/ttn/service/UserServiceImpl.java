package com.ttn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttn.dao.UserDao;
import com.ttn.model.User;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	public boolean doesUserExist(String username) {
		// TODO Auto-generated method stub
		return userDao.doesUserExist(username);
	}

	@Override
	public boolean isValidUser(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.isValidUser(username, password);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userDao.deleteUser(user);
	}

	@Override
	public User getSessionDetails(String username) {
		// TODO Auto-generated method stub
		return userDao.getSessionDetails(username);
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userDao.getSessionDetails(username);
	}

	
	
}
