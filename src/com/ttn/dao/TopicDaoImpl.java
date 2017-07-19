package com.ttn.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ttn.dto.TopicDetails;
import com.ttn.model.Topic;
import com.ttn.model.User;

@Repository("topicDao")
public class TopicDaoImpl implements TopicDao {
	
	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public boolean createTopic(Topic topic) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(topic);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}

	@Override
	public void updateTopicDate(TopicDetails topicDetails) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("update Topic set lastUpdated=? where name=? and createdBy=?");
		query.setDate(0, new Date());
		query.setString(1, topicDetails.getName());
		query.setString(2, topicDetails.getCreatedBy());
		int result = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		
	}

	@Override
	public Topic getTopic(String topic_name, String createdBy) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Topic where name=? and createdBy=?");
		query.setString(0, topic_name);
		query.setString(1, createdBy);
		List<Topic> topics = query.list();
		return topics.get(0);
	}

	@Override
	public List<Topic> getCreatedTopics(User user) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Topic where createdBy=?");
		query.setString(0, user.getUsername());
		
		return query.list();
		
	}

	@Override
	public void deleteTopic(Topic topic) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.refresh(topic);
		session.delete(topic);
		session.getTransaction().commit();
		session.close();
		
	}
	@Override
	public Long getNumberOfTopicsCreated(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select count(*) from Topic where createdBy=?");
		query.setString(0, username);
		List<Long> result = query.list();
		return result.get(0);
		
		
	}
	
}
