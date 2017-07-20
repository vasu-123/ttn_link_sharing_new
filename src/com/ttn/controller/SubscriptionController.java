package com.ttn.controller;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(value="/api/subscribe/topic" , method = RequestMethod.POST )
    @ResponseBody
    public String subscribeUser(@RequestBody String json , HttpServletRequest request) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		
		System.out.println("iside");
		SubscriptionDetails subscriptionDetails = new Gson().fromJson(json, SubscriptionDetails.class);

		HttpSession session = request.getSession(false);
		User user = new User();
		user.setUsername((String)session.getAttribute("username"));
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		if(subscriptionDetails.getSeriousness().equalsIgnoreCase("Casual")){
			subscription.setSeriousness(Seriousness.CASUAL);
		}
		else if(subscriptionDetails.getSeriousness().equalsIgnoreCase("Serious")){
			subscription.setSeriousness(Seriousness.SERIOUS);
		}
		else{
			subscription.setSeriousness(Seriousness.VERY_SERIOUS);
		}
		subscription.setDateCreated(new Date());
		subscriptionService.addSubscription(subscription, subscriptionDetails.getTopic_id());
        return "You have been successfully subscribed to the topic";
        
   }

	@RequestMapping(value="/api/subscribed/topics" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public List<Topic> getSubscribedTopics(HttpServletRequest request) {
        
        System.out.print("Reached");
		System.out.println("iside");
		
		HttpSession session = request.getSession(false);
		User user = new User();
		user.setUsername((String)session.getAttribute("username"));
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
	
	@RequestMapping(value="/api/subscriptions/total" , method = RequestMethod.GET)
    @ResponseBody
    public Long getTotalSubscriptionsOfUser(HttpServletRequest request) {
        
       HttpSession session = request.getSession(false);
       
		return subscriptionService.getNumberOfSubscriptions((String)session.getAttribute("username"));
        
   }
	
	@RequestMapping(value="/api/subscriptions/topic" , method = RequestMethod.POST)
    @ResponseBody
    public Long getTotalSubscriptionsOfTopic(@RequestBody String json , HttpServletRequest request) {
        
       HttpSession session = request.getSession(false);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		Topic topic = new Gson().fromJson(json, Topic.class);

       
		return subscriptionService.getNumberOfSubscriptionsOfTopic(topic);
   }

	@RequestMapping(value="/api/check/user/subscribed" , method = RequestMethod.POST)
    @ResponseBody
    public Boolean isUserSubscribedToTopic(@RequestBody String json , HttpServletRequest request) {
        
       HttpSession session = request.getSession(false);
       User user = new User();
       user.setUsername((String)session.getAttribute("username"));
       
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		Topic topic = new Gson().fromJson(json, Topic.class);

		System.out.println(topic.getId()+"  "+user.getUsername());
       
		return subscriptionService.isSubscribed(topic, user);
   }

	@RequestMapping(value="/api/unsubscribe/topic" , method = RequestMethod.POST)
    @ResponseBody
    public String unsubscribeUserFromTopic(@RequestBody String json , HttpServletRequest request) {
        
       HttpSession session = request.getSession(false);
       User user = new User();
       user.setUsername((String)session.getAttribute("username"));
       
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		Topic topic = new Gson().fromJson(json, Topic.class);

		System.out.println(topic.getId()+"  "+user.getUsername());
       
		subscriptionService.unsubscribeTopic(topic, user);

		return "Unsubscribed from topic successfully!";
	}


}
