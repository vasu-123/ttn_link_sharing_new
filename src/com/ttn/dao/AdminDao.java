package com.ttn.dao;
import java.util.List;

import com.ttn.model.User;

public interface AdminDao extends UserDao {

	public List<User> getAllUsers();
	public void deactivateUser(String username);
    public void activateUser(String username);
}
