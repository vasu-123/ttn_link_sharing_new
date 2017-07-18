package com.ttn.controller;

import java.net.URLDecoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ttn.model.User;
import com.ttn.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/register" , method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		User user = new Gson().fromJson(json, User.class);
		System.out.println(user.getEmail());
		System.out.println(user.getFirstName());
		System.out.println(user.getUsername());
		user.setDateCreated(new Date());
		user.setLastUpdated(new Date());
		user.setAdmin(false);
		user.setActive(false);
		
       
		if(!userService.doesUserExist(user.getUsername())){
            userService.addUser(user);
            System.out.println("Added");
            return "User registered successfully";
        }
        
        else{
        	System.out.println("User already exists");
        	return "User already exists" ;
        }
        
        
   }
 
	@RequestMapping(value="/user/delete" , method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		User user = new Gson().fromJson(json, User.class);
		
		userService.deleteUser(user);
        
   }
	
    
}