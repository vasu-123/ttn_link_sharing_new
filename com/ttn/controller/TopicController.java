package com.ttn.controller;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.ttn.model.Topic;
import com.ttn.model.User;
import com.ttn.service.TopicService;
import com.ttn.service.UserService;

@Controller
public class TopicController {

	@Autowired
	private TopicService topicService;
	
    @RequestMapping(value="/topic/create" , method = RequestMethod.POST)
    @ResponseBody
    public void createTopic(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		Topic topic = new Gson().fromJson(json, Topic.class);
		User user = new User();
		user.setUsername("karan_gupta");
		user.setEmail("hgrbefv");
		topic.setCreatedBy(user);
		topic.setDateCreated(new Date());
		topic.setLastUpdated(new Date());
		
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> myMap = gson.fromJson(json, type);
		System.out.println(myMap.get("visibility"));
		if(myMap.get("visibility").equals("Private")){
			topic.setVisibility(Visibility.PRIVATE);
		}
		
		else{
			topic.setVisibility(Visibility.PUBLIC);
		}
		topicService.createTopic(topic);
        
        
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

    
    @RequestMapping(value="/created/topics" , method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getCreatedTopics() {
        
        System.out.print("Reached");
        		System.out.println("iside");
		
        		User user = new User();
        		user.setUsername("karan_gupta");
		
        		return topicService.getCreatedTopics(user);
        
        
   }
    
    @RequestMapping(value="/topic/delete" , method = RequestMethod.POST)
    @ResponseBody
    public void addUser(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		Topic topic = new Gson().fromJson(json, Topic.class);
		
		topicService.deleteTopic(topic);
        
   }
 

    
    
}
