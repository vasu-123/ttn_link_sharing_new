package com.ttn.service;

import java.util.List;

import com.ttn.model.LinkResource;
import com.ttn.model.Resource;
import com.ttn.model.ResourceItem;

public interface ResourceService {
	public void addResource(LinkResource linkResource);
	public void setResourceScore(ResourceItem resourceItem);
	public List<Resource> getTopResources();
	public List<Resource> getRecentResources();

}
