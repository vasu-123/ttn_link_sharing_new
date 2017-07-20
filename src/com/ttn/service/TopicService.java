package com.ttn.service;

import java.util.List;

import com.ttn.dto.TopicDetails;
import com.ttn.model.Topic;
import com.ttn.model.User;

public interface TopicService {
	public boolean createTopic(Topic topic);
	public void updateTopicDate(TopicDetails topicDetails);
	public List<Topic> getCreatedTopics(User user);
    public Topic getTopic(String topic_name , String createdBy);
    public void deleteTopic(Topic topic);
	public Long getNumberOfTopicsCreated(String username);
	public Boolean isTopicAlreadyCreated(Topic topic);
	public Topic getTopic(Integer id);


}
