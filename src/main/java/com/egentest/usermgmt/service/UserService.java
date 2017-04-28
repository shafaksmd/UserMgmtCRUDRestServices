package com.egentest.usermgmt.service;

import java.util.List;

import com.egentest.usermgmt.model.User;



public interface UserService {
	
	User findById(long id);
	
	void saveUser(User user);
	
	boolean updateUser(long id, User updatedUser);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
}
