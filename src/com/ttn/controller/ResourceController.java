package com.ttn.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ttn.dto.LinkResourceWithTopicDetails;
import com.ttn.dto.ResourceScore;
import com.ttn.model.LinkResource;
import com.ttn.model.Resource;
import com.ttn.model.ResourceItem;
import com.ttn.model.Topic;
import com.ttn.model.User;
import com.ttn.service.ResourceService;
import com.ttn.service.TopicService;
import com.ttn.service.UserService;

@Controller
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private TopicService topicService;
	
    @RequestMapping(value="/api/resource/add" , method = RequestMethod.POST)
    @ResponseBody
    public String addLinkResource(@RequestBody String json , HttpServletRequest request) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		LinkResourceWithTopicDetails linkResourceWithTopicDetails = new Gson().fromJson(json, LinkResourceWithTopicDetails.class);
		System.out.println("Debugging");
		System.out.println(linkResourceWithTopicDetails.getTopic_name().length() + " "+linkResourceWithTopicDetails.getTopicCreatedBy().length());
		Topic topic = topicService.getTopic(linkResourceWithTopicDetails.getTopic_name(), linkResourceWithTopicDetails.getTopicCreatedBy());
        LinkResource linkResource = prepareLinkResource(linkResourceWithTopicDetails,topic);
        User user = new User();
        user.setUsername((String)session.getAttribute("username"));
        linkResource.setCreatedBy(user);
        System.out.println(linkResource.getTopic().getName());
        System.out.println(linkResource.getTopic().getCreatedBy().getUsername());
       resourceService.addResource(linkResource);
		System.out.println("iside");
		
		return "Link shared successfully!";
        
   }
    
//    public Topic prepareTopic(LinkResourceWithTopicDetails l){
//    	Topic topic = topicService.getTopic(l.getTopic_name(), l.getTopicCreatedBy());
//    	return topic;
//    }
    
    @RequestMapping(value="/resource/score" , method = RequestMethod.POST)
    @ResponseBody
    public void setLinkResourceScore(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();
		
		ResourceScore resourceScore = new Gson().fromJson(json, ResourceScore.class);
		Resource resource = new Resource();
		resource.setId(resourceScore.getResource_id());
		User user = new User();
		user.setUsername("vasu123");
		ResourceItem resourceItem = new ResourceItem();
		resourceItem.setResource(resource);
		resourceItem.setScore(resourceScore.getScore());
		resourceItem.setUser(user);
		
		resourceService.setResourceScore(resourceItem);
		
        
   }

 
    public LinkResource prepareLinkResource(LinkResourceWithTopicDetails linkResourceWithTopicDetails , Topic topic){
    	LinkResource linkResource = new LinkResource();
    	linkResource.setDateCreated(new Date());
    	linkResource.setDescription(linkResourceWithTopicDetails.getDescription());
    	linkResource.setLastUpdated(new Date());
    	linkResource.setTopic(topic);
    	linkResource.setUrl(linkResourceWithTopicDetails.getUrl());
    	return linkResource ;
    }
    
    @RequestMapping(value="/api/resources/top" , method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> getTopResources() {
        
        System.out.print("Reached");
       
        return resourceService.getTopResources();
		
        
   }

    @RequestMapping(value="/api/resources/recent" , method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> getRecentResources() {
        
        System.out.print("Reached");
       
        return resourceService.getRecentResources();
		
        
   }

    @RequestMapping(value="/api/topics/trending" , method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getTrendingTopics() {
        
        System.out.print("Reached");
       
        return resourceService.getTrendingTopics();
		
        
   }
    
    @RequestMapping(value="/api/resources/topic" , method = RequestMethod.POST)
    @ResponseBody
    public Long getTotalResourcesOfTopic(@RequestBody String json , HttpServletRequest request) {
        
       HttpSession session = request.getSession(false);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		Topic topic = new Gson().fromJson(json, Topic.class);

       
		return resourceService.getNumberOfResourcesOfTopic(topic);
   }


}
