package com.ttn.dao;

import java.util.List;

import com.ttn.dto.TopicDetails;
import com.ttn.model.Topic;
import com.ttn.model.User;

public interface TopicDao {
public boolean createTopic(Topic topic);
public void updateTopicDate(TopicDetails topicDetails);
public Topic getTopic(String topic_name , String createdBy);
public List<Topic> getCreatedTopics(User user);
public void deleteTopic(Topic topic);

}
