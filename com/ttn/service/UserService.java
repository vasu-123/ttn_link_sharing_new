package com.ttn.service;

import com.ttn.model.User;

public interface UserService {

	public boolean doesUserExist(String username);
	public void addUser(User user);
	public boolean validateUser(String username , String password);
	public void deleteUser(User user);

}
