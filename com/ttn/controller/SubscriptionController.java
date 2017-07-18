package com.ttn.controller;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ttn.dto.SubscriptionDetails;
import com.ttn.dto.TopicDetails;
import com.ttn.enums.Seriousness;
import com.ttn.enums.Visibility;
import com.ttn.model.Subscription;
import com.ttn.model.Topic;
import com.ttn.model.User;
import com.ttn.service.SubscriptionService;
import com.ttn.service.TopicService;

@Controller
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping(value="/subscribe" , method = RequestMethod.POST )
    @ResponseBody
    public void createTopic(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		SubscriptionDetails subscriptionDetails = new Gson().fromJson(json, SubscriptionDetails.class);
//		User user = new User();
//		user.setUsername("vasu123");
//		user.setEmail("hgrbefv");
//		subscriptionDetails.setCreatedBy(user);
//		subscriptionDetails.setDateCreated(new Date());
//		subscriptionDetails.setLastUpdated(new Date());
//		
		Subscription subscription = new Subscription();
		if(subscriptionDetails.getSeriousness().equals("Casual")){
			subscription.setSeriousness(Seriousness.CASUAL);
		}
		else if(subscriptionDetails.getSeriousness().equals("Serious")){
			subscription.setSeriousness(Seriousness.SERIOUS);
		}
		else{
			subscription.setSeriousness(Seriousness.VERY_SERIOUS);
		}
		subscription.setDateCreated(new Date());
		subscriptionService.addSubscription(subscription, subscriptionDetails.getName(), subscriptionDetails.getCreatedBy());
        
        
   }

	@RequestMapping(value="/subscribed/topics" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public List<Topic> getSubscribedTopics() {
        
        System.out.print("Reached");
		System.out.println("iside");
		User user = new User();
		user.setUsername("karan_gupta");
		user.setEmail("hgrbefv");

		Subscription subscription = new Subscription();
		subscription.setDateCreated(new Date());
		List<Topic> topics = subscriptionService.getSubscribedTopics(user);
        
		for(Topic topic : topics){
			System.out.println(topic.getName());
			System.out.println(topic.getCreatedBy().getUsername());
			
		}
		return topics;
   }
	
	@RequestMapping(value="/update/seriousness" , method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public void updateSeriousness(@RequestBody String json) {
        
		 System.out.print("Reached");
	        System.out.println(json);
	       json = URLDecoder.decode(json);
			System.out.println("new json" + json);
			Gson gson = new Gson();

			System.out.println("iside");
			SubscriptionDetails subscriptionDetails = new Gson().fromJson(json, SubscriptionDetails.class);

        System.out.print("Reached");
		System.out.println("iside");
		User user = new User();
		user.setUsername("karan_gupta");
		user.setEmail("hgrbefv");

		Subscription subscription = new Subscription();
		subscription.setDateCreated(new Date());
		List<Topic> topics = subscriptionService.getSubscribedTopics(user);
        
		
   }

}
