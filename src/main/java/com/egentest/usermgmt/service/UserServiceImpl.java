package com.egentest.usermgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egentest.usermgmt.dao.UserDAO;
import com.egentest.usermgmt.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDAO;
	
	public List<User> findAllUsers() {
		return userDAO.list();
	}
	
	public User findById(long id) {
		return userDAO.list(id);
	}
	
	public void saveUser(User user) {
		userDAO.add(user);
	}

	public boolean updateUser(long id, User withUpdates) {
		return userDAO.updateUser(id, withUpdates);
	}

	public void deleteUserById(long id) {
		userDAO.deleteUser(id);
	}
}
