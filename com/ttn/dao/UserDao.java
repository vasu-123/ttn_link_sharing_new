package com.ttn.dao;

import com.ttn.model.User;

public interface UserDao {
	
	public boolean doesUserExist(String username);
	public void addUser(User user);
	public boolean validateUser(String username , String password);
	public boolean isActiveUser(User user);
	public void deleteUser(User user);
}
