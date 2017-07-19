package com.ttn.service;

import com.ttn.model.User;

public interface UserService {

	public boolean doesUserExist(String username);
	public void addUser(User user);
	public boolean isValidUser(String username , String password);
	public void deleteUser(User user);
	public User getSessionDetails(String username);
	public User getUser(String username);


}
