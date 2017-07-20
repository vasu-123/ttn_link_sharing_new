package com.ttn.controller;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ttn.dto.TopicDetails;
import com.ttn.enums.Visibility;
import com.ttn.exceptions.TopicAlreadyCreatedException;
import com.ttn.model.Topic;
import com.ttn.model.User;
import com.ttn.service.TopicService;
import com.ttn.service.UserService;

@Controller
public class TopicController {

	@Autowired
	private TopicService topicService;
	
    @RequestMapping(value="/api/topic/create" , method = RequestMethod.POST)
    @ResponseBody
    public String createTopic(@RequestBody String json , HttpServletRequest request) throws TopicAlreadyCreatedException {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		System.out.println("iside");
		Topic topic = new Gson().fromJson(json, Topic.class);
		HttpSession session = request.getSession(false);
		User user = new User();
		user.setUsername((String)session.getAttribute("username"));
		topic.setCreatedBy(user);
		topic.setDateCreated(new Date());
		topic.setLastUpdated(new Date());
		
		if(topicService.isTopicAlreadyCreated(topic)){
			throw new TopicAlreadyCreatedException();
		}
		
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> myMap = gson.fromJson(json, type);
		System.out.println(myMap.get("visibility"));
		if(myMap.get("visibility").equalsIgnoreCase("private")){
			topic.setVisibility(Visibility.PRIVATE);
		}
		
		else{
			topic.setVisibility(Visibility.PUBLIC);
		}
		topicService.createTopic(topic);
        return "Topic Created Successfully ! ";
        
   }
    
    @RequestMapping(value="/topic/update" , method = RequestMethod.POST)
    @ResponseBody
    public void updateTopic(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		TopicDetails topicDetails = new Gson().fromJson(json, TopicDetails.class);
		System.out.println(topicDetails.getName() + " " + topicDetails.getCreatedBy());
//		User user = new User();
//		user.setUsername("vasu123");
//		user.setEmail("hgrbefv");
//		t.setCreatedBy(user);
		//t.setDateCreated(new Date());
		
		topicService.updateTopicDate(topicDetails);;
        
        
   }

    
    @RequestMapping(value="/api/created/topics" , method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getCreatedTopics(HttpServletRequest request) {
        
        System.out.print("Reached");
        		System.out.println("iside");
		
        		User user = new User();
        		user.setUsername((String)request.getSession(false).getAttribute("username"));
        		
        		return topicService.getCreatedTopics(user);
        
        
   }
    
    @RequestMapping(value="/topic/delete" , method = RequestMethod.POST)
    @ResponseBody
    public void deleteTopic(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		Topic topic = new Gson().fromJson(json, Topic.class);
		
		topicService.deleteTopic(topic);
        
   }
 
    @RequestMapping(value="/api/topics/total" , method = RequestMethod.GET)
    @ResponseBody
    public Long getTotalTopicsCreatedByUser(HttpServletRequest request) {
        
       HttpSession session = request.getSession(false);
       
		return topicService.getNumberOfTopicsCreated((String)session.getAttribute("username"));
        
   }

    
    
}
