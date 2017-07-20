package com.ttn.service;

import com.ttn.model.User;

public interface UserService {

	public boolean doesUserExist(String username);
	public void addUser(User user);
	public boolean isAuthenticateUserFromUsername(String username , String password);
	public void deleteUser(User user);
	public User getSessionDetails(String username);
	public User getUser(String username);
	public boolean isAuthenticateUserFromEmail(String email , String password);
	public boolean isUserDeactivatedFromUsername(String username);
	public boolean isUserDeactivatedFromEmail(String email);
    public boolean checkEmailExists(String email);
    public String getUsername(String email);


}
