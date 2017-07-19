package com.ttn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttn.dao.AdminDao;
import com.ttn.model.User;

@Service("adminService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return adminDao.getAllUsers();
	}

	@Override
	public void deactivateUser(String username) {
		// TODO Auto-generated method stub
		adminDao.deactivateUser(username);
	}

	@Override
	public void activateUser(String username) {
		// TODO Auto-generated method stub
	adminDao.activateUser(username);	
	}

}
