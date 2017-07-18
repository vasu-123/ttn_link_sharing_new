package com.ttn.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttn.dao.SubscriptionDao;
import com.ttn.dao.TopicDao;
import com.ttn.dto.TopicDetails;
import com.ttn.enums.Seriousness;
import com.ttn.model.Subscription;
import com.ttn.model.Topic;
import com.ttn.model.User;

@Service("topicService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private SubscriptionDao subscriptionDao ;
	
	@Override
	public boolean createTopic(Topic topic) {
		// TODO Auto-generated method stub
		Subscription subscription = new Subscription();
		subscription.setDateCreated(new Date());
		subscription.setSeriousness(Seriousness.VERY_SERIOUS);
		subscription.setTopic(topic);
		topicDao.createTopic(topic);
		subscriptionDao.addSubscription(subscription);

		return true ;
	}

	@Override
	public void updateTopicDate(TopicDetails topicDetails) {
		// TODO Auto-generated method stub
		topicDao.updateTopicDate(topicDetails);
	}

	@Override
	public List<Topic> getCreatedTopics(User user) {
		// TODO Auto-generated method stub
		return topicDao.getCreatedTopics(user);
	}

	@Override
	public Topic getTopic(String topic_name, String createdBy) {
		// TODO Auto-generated method stub
		return topicDao.getTopic(topic_name, createdBy);
	}

	@Override
	public void deleteTopic(Topic topic) {
		// TODO Auto-generated method stub
		topicDao.deleteTopic(topic);
	}
	
	
	
	

}
