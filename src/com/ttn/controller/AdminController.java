package com.ttn.controller;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ttn.dto.Credentials;
import com.ttn.service.AdminService;
import com.ttn.model.User;


@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/api/users/all" , method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        
       return adminService.getAllUsers();
   }
	
	@RequestMapping(value="/api/user/deactivate" , method = RequestMethod.POST)
    @ResponseBody
    public void deactivateUser (@RequestBody String json) {
        
	    json = URLDecoder.decode(json);
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		Credentials credentials = new Gson().fromJson(json, Credentials.class);

       adminService.deactivateUser(credentials.getUsername());
   }
	
	@RequestMapping(value="/api/user/activate" , method = RequestMethod.POST)
    @ResponseBody
    public void activateUser (@RequestBody String json) {
        
	    json = URLDecoder.decode(json);
		if(json.charAt(json.length()-1) == '='){
			json = json.substring(0, json.length() - 1);
	      }
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		Credentials credentials = new Gson().fromJson(json, Credentials.class);

       adminService.activateUser(credentials.getUsername());
   }

    
}
