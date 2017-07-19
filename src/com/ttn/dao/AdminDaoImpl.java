package com.ttn.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ttn.model.User;

@Repository("adminDao")
public class AdminDaoImpl extends UserDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User");
		List<User> allUsers = query.list();
		session.getTransaction().commit();
		session.close();
		return allUsers;
	}

	@Override
	public void deactivateUser(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("update User set active=? where username=?");
		query.setBoolean(0, false);
		query.setString(1, username);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		
	}

	@Override
	public void activateUser(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("update User set active=? where username=?");
		query.setBoolean(0, true);
		query.setString(1, username);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}

}
