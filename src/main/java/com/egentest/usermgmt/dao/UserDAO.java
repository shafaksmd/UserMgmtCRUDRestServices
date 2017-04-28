package com.egentest.usermgmt.dao;
import java.util.List;
import com.egentest.usermgmt.model.User;

public interface UserDAO {
	public List<User> list();
	
	public User list(long id);

	public void add(User user);

	public boolean updateUser(long id, User withUpdates);

	public void deleteUser(long id);
}