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
	public boolean isAuthenticateUserFromUsername(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.isAuthenticateUserFromUsername(username, password);
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

	@Override
	public boolean isAuthenticateUserFromEmail(String email, String password) {
		// TODO Auto-generated method stub
		return userDao.isAuthenticateUserFromEmail(email, password);
	}

	@Override
	public boolean isUserDeactivatedFromUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.isUserDeactivatedFromUsername(username);
	}

	@Override
	public boolean isUserDeactivatedFromEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.isUserDeactivatedFromEmail(email);
	}

	@Override
	public boolean checkEmailExists(String email) {
		// TODO Auto-generated method stub
		return userDao.checkEmailExists(email);
	}

	@Override
	public String getUsername(String email) {
		// TODO Auto-generated method stub
		return userDao.getUsername(email);
	}

	
	
}
