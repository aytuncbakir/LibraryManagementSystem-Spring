package com.aytuncbakir.lms.service;

import java.util.List;

import com.aytuncbakir.lms.domain.User;
import com.aytuncbakir.lms.exception.UserBlockedException;

public interface UserService {
	
	public static final Integer LOGIN_STATUS_ACTIVE = 1;
	public static final Integer LOGIN_STATUS_BLOCKED = 2;
	
	public static final Integer ROLE_ADMIN = 1;
	public static final Integer ROLE_LIBRARIAN = 2;
	public static final Integer ROLE_USER = 3;
	
	public void register(User user);
	public User login(String username, String password) throws UserBlockedException;
	public List<User> findAll();
	public void changeUserStatus(Integer userId, Integer status);
	public List<User> findUser(String freeText);
	public void delete(Integer userId);
	public void delete(Integer[] users);
	public User find(Integer userId);
	public void save(User user);
	public void update(User user);
	public void changeUserType(Integer userId, Integer type);
	public Boolean isUsernameExist(String username);

}
