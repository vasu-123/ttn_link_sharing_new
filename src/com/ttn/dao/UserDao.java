package com.ttn.dao;

import com.ttn.model.User;

public interface UserDao {
	
	public boolean doesUserExist(String username);
	public void addUser(User user);
	public boolean isAuthenticateUserFromUsername(String username , String password);
	public boolean isActiveUser(User user);
	public void deleteUser(User user);
	public User getSessionDetails(String username);
	public User getUser(String username);
	public boolean isAuthenticateUserFromEmail(String email , String password);    // not needed
	public boolean isUserDeactivatedFromUsername(String username);
	public boolean isUserDeactivatedFromEmail(String email);   // not needed
    public String getUsername(String email);
    public boolean checkEmailExists(String email);
}
