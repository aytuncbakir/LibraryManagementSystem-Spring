package com.aytuncbakir.lms.dao;

import java.util.List;

import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.domain.User;
import com.aytuncbakir.lms.exception.UserBlockedException;

public interface UserDAO {
	
	public User login(String username, String password) throws UserBlockedException;
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public void delete(Integer userId);
	public void delete(Integer[] users);
	public User findById(Integer userId);
	public List<User> findAll();
	public List<User> findByProperty(String propName, Object propValue);
	public List<User> findUser(String txt);
	public void changeUserStatus(Integer userId, Integer status);
	public void changeUserType(Integer userId, Integer type);
	public Boolean isUsernameExist(String username);

}
