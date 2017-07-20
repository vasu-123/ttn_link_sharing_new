package com.ttn.service;

import java.util.List;

import com.ttn.model.User;

public interface AdminService {
	public List<User> getAllUsers();
	public void deactivateUser(String username);
    public void activateUser(String username);
}
