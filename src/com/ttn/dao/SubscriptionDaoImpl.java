package com.ttn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ttn.dto.TopicDetails;
import com.ttn.enums.Seriousness;
import com.ttn.model.Subscription;
import com.ttn.model.Topic;
import com.ttn.model.User;

@Repository("subscriptionDao")
public class SubscriptionDaoImpl implements SubscriptionDao {

	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(subscription);
		session.getTransaction().commit();
		session.close();
	}
	@Override
	public List<Topic> getSubscribedTopics(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("Reached here");
		
//		Query query = session.createQuery("select Topic.name , Topic.id , Topic.visibility , Topic.createdBy , "
//				+ "Topic.dateCreated , Topic.lastUpdated from Topic inner join Subscription on Topic.id=Subscription.topic");
//		
//		Query query = session.createSQLQuery("select topic.name , topic.id , topic.visibility , topic.createdBy ,"
//				+ "topic.dateCreated , topic.lastUpdated from topic inner join "
//				+ "subscription on topic.id = subscription.topic_id");
		
		Query query = session.createQuery("select t from Topic t inner join Subscription session on t.id = session.topic where session.user=? ");
		query.setString(0, user.getUsername());
		List<Topic> topics = (List<Topic>) query.list();
		
		return topics;
	}
	
	@Override   // To-Do
	public void updateSeriousness(User user , TopicDetails topicDetails , Seriousness seriousness){
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("update Seriousness set seriousness=? where user=?");
		query.setString(0, seriousness.toString());
		query.setString(1, user.getUsername());
		
		query.executeUpdate();
	}
	@Override
	public Boolean isSubscribed(Topic topic, User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("Reached here");
		System.out.println(topic.getId() + user.getUsername()+topic.getCreatedBy().getUsername());
		Query query = session.createQuery("select id from Subscription where user=? and topic=?");
		query.setEntity(0, user);
		query.setEntity(1, topic);
		System.out.println("Reached here");

		if(query.list().size() > 0){
			return true;
		}
		
		return false;
	}
	@Override
	public Long getNumberOfSubscriptions(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select count(*) from Subscription where user=?");
		query.setString(0, username);
		List<Long> result = query.list();
		return result.get(0);
		
		
	}
}
