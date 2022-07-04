package com.aytuncbakir.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aytuncbakir.lms.dao.UserDAO;
import com.aytuncbakir.lms.domain.User;
import com.aytuncbakir.lms.exception.UserBlockedException;
import com.aytuncbakir.lms.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO  userDAO;
	
	@Override
	public void register(User user) {
		userDAO.save(user);
	}

	@Override
	public User login(String username, String password) throws UserBlockedException {
		return userDAO.login(username, password);
		
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	

	@Override
	public List<User> findUser(String freeText) {
		return userDAO.findUser(freeText);
	}

	@Override
	public void delete(Integer userId) {
		userDAO.delete(userId);
	}

	@Override
	public void delete(Integer[] users) {
		userDAO.delete(users);
		
	}

	@Override
	public User find(Integer userId) {
		return userDAO.findById(userId);
	}

	@Override
	public void save(User user) {
		userDAO.save(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
		
	}

	@Override
	public void changeUserStatus(Integer userId, Integer status) {
		userDAO.changeUserStatus( userId,  status);
		
	}
	
	@Override
	public void changeUserType(Integer userId, Integer type) {
		userDAO.changeUserType( userId,  type);
		
	}

	@Override
	public Boolean isUsernameExist(String username) {
		return userDAO.isUsernameExist(username);
	}
	
	

}
