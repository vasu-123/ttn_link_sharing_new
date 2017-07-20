package com.ttn.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.ttn.model.User;


@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		
	}

	@Override
	public boolean doesUserExist(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
	//	System.out.println(username);
		Query query = session.createQuery("select username FROM User where username=?");
		query.setString(0, username);
		List<String> userNames = query.list();
		
		if(userNames.size() > 0)
			return true;
		
		return false;
	}

	@Override
	public boolean isAuthenticateUserFromUsername(String username, String password) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select username from User where username = ? and password = ?");
		query.setString(0, username);
		query.setString(1, password);
		
		List<String> userNames = query.list();
		
		if(userNames.size() > 0)
			return true;
		
		return false;
	}

	@Override
	public boolean isActiveUser(User user) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select username from User where username=? and isActive=true");
		List<String> userNames = query.list();
		
		if(userNames.size() > 0)
			return true;
		
		return false;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.refresh(user);
		System.out.println(user.getUsername());
		session.delete(user);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public User getSessionDetails(String username) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("In hibernate : "+username);
		User user = session.get(User.class, username);
		session.getTransaction().commit();
		session.close();
		return user;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = session.get(User.class, username);
		session.getTransaction().commit();
		session.close();
		return user;

	}

	@Override
	public boolean isAuthenticateUserFromEmail(String email, String password) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select username from User where email = ? and password = ?");
		query.setString(0, email);
		query.setString(1, password);
		
		List<String> userNames = query.list();
		
		if(userNames.size() > 0)
			return true;
		
		return false;
		}

	@Override
	public boolean isUserDeactivatedFromUsername(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select username from User where username=? and active=?");
		query.setString(0,username);
		query.setBoolean(1, false);
		
		if(query.list().size() > 0)
			return true;
		
		return false ;
		
	}

	@Override
	public boolean isUserDeactivatedFromEmail(String email) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select username from User where email=? and active=?");
		query.setString(0,email);
		query.setBoolean(1, false);
		
		if(query.list().size() > 0)
			return true;
		
		return false ;
		
	}

	@Override
	public String getUsername(String email) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select username from User where email=?");
		query.setString(0,email);
		

return (String)query.list().get(0); 
		
	}

	@Override
	public boolean checkEmailExists(String email) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select username from User where email=? ");
		query.setString(0,email);
		
		
		if(query.list().size() > 0)
			return true;
		
		return false ;
		
	}

	
		
	

}
