package com.services;

import java.io.IOException;
import java.util.List;

import com.models.User;

public interface UserService {

	public void save(User user) throws IOException;
	
	public User getUserByUserName(User user);
	
	public boolean isLoggedIn(User user);
	
	public List<User> getUsers();

	boolean setLoggedOut(User user) throws IOException;

	boolean setLoggedIn(User user) throws IOException;
}
