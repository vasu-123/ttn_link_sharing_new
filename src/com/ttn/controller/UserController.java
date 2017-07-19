package com.ttn.controller;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ttn.dto.Credentials;
import com.ttn.exceptions.InvalidCredentialsException;
import com.ttn.exceptions.SessionDoesNotExistException;
import com.ttn.exceptions.UsernameAlreadyExistsException;
import com.ttn.model.User;
import com.ttn.service.UserService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/api/user/register" , method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody String json) throws UsernameAlreadyExistsException {
        
        System.out.print("Reached");
        System.out.println(json);
        json = URLDecoder.decode(json);
		json = json.substring(0, json.length() - 1);
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
            return "You have been registered successfully ! Please login to continue !";
        }
        
        else{
        	throw new UsernameAlreadyExistsException();
        }
        
        
   }
 
	@RequestMapping(value="/api/user/delete" , method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
        json = URLDecoder.decode(json);
		json = json.substring(0, json.length() - 1);
System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		User user = new Gson().fromJson(json, User.class);
		
		userService.deleteUser(user);
        
   }

	@RequestMapping(value="/api/user/login" , method = RequestMethod.POST)
    @ResponseBody
    public Boolean isValidUser(@RequestBody String json , HttpServletRequest request, HttpServletResponse response) throws InvalidCredentialsException {
        
        System.out.print("Reached");
        System.out.println("json : "+json);
      json = URLDecoder.decode(json);
      System.out.println("new json" + json);
		
      if(json.charAt(json.length()-1) == '='){
		json = json.substring(0, json.length() - 1);
      }

		Gson gson = new Gson();

		System.out.println("iside");
		Credentials credentials = new Gson().fromJson(json, Credentials.class);
		
		if(userService.isValidUser(credentials.getUsername(), credentials.getPassword())){
		HttpSession session = request.getSession(true);
			session.setAttribute("username", credentials.getUsername());
//			session.invalidate();
//			session = request.getSession(false);
//			System.out.println("Session still exists : "+ session);
		//	response.setHeader("username", credentials.getUsername());
			return true;
		}
		else{
			System.out.println("In else block");
			throw new InvalidCredentialsException();
		}
   }
	
	@RequestMapping(value="/api/user/session" , method = RequestMethod.GET)
    @ResponseBody
    public User getSessionDetails(HttpServletRequest request) throws InvalidCredentialsException , SessionDoesNotExistException {
      
      HttpSession session = request.getSession(false);
      System.out.println("Session check : "+session);
      if(session == null || session.getAttribute("username") == null){
    	  throw new SessionDoesNotExistException();
      }
      
//      if(request.getHeader("username") == null){
//    	  throw new SessionDoesNotExistException();
//      }
		return userService.getSessionDetails((String)session.getAttribute("username"));
		
   }

	@RequestMapping(value="/api/user/get" , method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestBody String json ) throws InvalidCredentialsException {
        
        System.out.print("Reached");
        System.out.println(json);
      json = URLDecoder.decode(json);
		json = json.substring(0, json.length() - 1);

		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		Credentials credentials = new Gson().fromJson(json, Credentials.class);
		
		return userService.getUser(credentials.getUsername());
		
   }

	
    public User getUserAfterLogin(String username )  {
        
        		
		return userService.getUser(username);
		
   }

    

    
}