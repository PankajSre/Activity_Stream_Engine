package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.User;

public interface UserDAO {

	public boolean saveUser(User user);
	public User getUserByUsername(String username);
	public boolean deleteUser(User user);
	public boolean updateUser(User user);
	public List<User> getAllUsers();
	public User validateUser(String username, String password);
}
