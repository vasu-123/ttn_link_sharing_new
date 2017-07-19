package com.ttn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttn.dao.ResourceDao;
import com.ttn.dao.SubscriptionDao;
import com.ttn.enums.Visibility;
import com.ttn.model.LinkResource;
import com.ttn.model.Resource;
import com.ttn.model.ResourceItem;

@Service("resourceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private SubscriptionDao subscriptionDao;
	
	@Override
	public void addResource(LinkResource linkResource) {
		// TODO Auto-generated method stub
		System.out.println("Topic id : "+linkResource.getTopic().getId());
		System.out.println("Username : "+linkResource.getCreatedBy().getUsername());
		if(subscriptionDao.isSubscribed(linkResource.getTopic(), linkResource.getCreatedBy())){
			System.out.println("Reachec here");
			resourceDao.addResource(linkResource);
		}
		
		else{
			System.out.println("User not subscribed");
		}
	}

	@Override
	public void setResourceScore(ResourceItem resourceItem) {
		// TODO Auto-generated method stub
		resourceDao.setResourceScore(resourceItem);
	}

	@Override
	public List<Resource> getTopResources() {
		// TODO Auto-generated method stub
//		List<Resource> resources =  resourceDao.getTopResources();
//		List<Resource> top5Resources = new ArrayList<Resource>();
//		int i = 0;
//		for(Resource r : resources){
//			if(r.getTopic().getVisibility() == Visibility.PUBLIC){
//				top5Resources.add(r);
//				++i;
//			}
//			if(i==5)
//				break;
//		}
//		
		return resourceDao.getTopResources();
	}

	@Override
	public List<Resource> getRecentResources() {
		// TODO Auto-generated method stub
		return resourceDao.getRecentResources();
	}

}
