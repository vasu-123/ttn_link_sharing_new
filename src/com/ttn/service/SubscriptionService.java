package com.ttn.service;

import java.util.List;

import com.ttn.dto.TopicDetails;
import com.ttn.enums.Seriousness;
import com.ttn.model.Subscription;
import com.ttn.model.Topic;
import com.ttn.model.User;

public interface SubscriptionService {
	public void addSubscription(Subscription subscription , String topic_name , String createdBy);
	public List<Topic> getSubscribedTopics(User user);
	public void updateSeriousness(User user , TopicDetails topicDetails , Seriousness seriousness);
	public Boolean isSubscribed(Topic topic , User user);
	public Long getNumberOfSubscriptions(String username);



}
