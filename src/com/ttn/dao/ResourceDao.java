package com.ttn.dao;

import java.util.List;

import com.ttn.model.LinkResource;
import com.ttn.model.Resource;
import com.ttn.model.ResourceItem;
import com.ttn.model.Topic;

public interface ResourceDao {

	public void addResource(LinkResource linkResource);
	public void setResourceScore(ResourceItem resourceItem);
	public List<Resource> getTopResources();
	public List<Resource> getRecentResources();
	public List<Topic> getTrendingTopics();
	public Long getNumberOfResourcesOfTopic(Topic topic);

}
