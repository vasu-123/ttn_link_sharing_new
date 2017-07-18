package com.ttn.service;

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

@Service("subscriptionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    private SubscriptionDao subscriptionDao;
    
    @Autowired
    private TopicDao topicDao;
   
    
	@Override
	public void addSubscription(Subscription subscription , String topic_name , String createdBy) {
		// TODO Auto-generated method stub
		subscription.setTopic(topicDao.getTopic(topic_name, createdBy));
		subscriptionDao.addSubscription(subscription);
	}


	@Override
	public List<Topic> getSubscribedTopics(User user) {
		// TODO Auto-generated method stub
		return subscriptionDao.getSubscribedTopics(user);
	}


	@Override
	public void updateSeriousness(User user, TopicDetails topicDetails, Seriousness seriousness) {
		// TODO Auto-generated method stub
		subscriptionDao.updateSeriousness(user, topicDetails, seriousness);
	}


	@Override
	public Boolean isSubscribed(Topic topic, User user) {
		// TODO Auto-generated method stub
		return subscriptionDao.isSubscribed(topic, user);
	}
	

}
