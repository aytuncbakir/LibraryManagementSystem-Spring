package com.aytuncbakir.lms.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.aytuncbakir.lms.dao.BaseDAO;
import com.aytuncbakir.lms.dao.UserDAO;
import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.domain.User;
import com.aytuncbakir.lms.exception.UserBlockedException;
import com.aytuncbakir.lms.rm.BookRowMapper;
import com.aytuncbakir.lms.rm.UserRowMapper;
import com.aytuncbakir.lms.service.UserService;
import com.aytuncbakir.lms.util.StringUtil;

@Repository()
public class UserDAOImpl extends BaseDAO implements UserDAO{

	
	@Override
	public User login(String username, String password) throws UserBlockedException {
		String sql = "SELECT userId, name, surname, username, password, phone, address, email, type, status "
				+ " FROM user WHERE username=:username AND password =:password ";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		
		try {
			User user = getNamedParameterJdbcTemplate().queryForObject(sql, map, new UserRowMapper());
			if(user.getStatus().equals(UserService.LOGIN_STATUS_BLOCKED)) {
				throw new UserBlockedException("Your account is blocked, contact to admin");
			}else {
				return user;
			}
			
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
		
	}

	
	@Override
	public void save(User user) {
		
		String sql = "INSERT INTO user(name, surname, username, password, phone, address, email, type, status) "
				+ "VALUES(:name, :surname, :username, :password, :phone, :address, :email, :type, :status)";
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", user.getName());
		map.put("surname", user.getSurname());
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		map.put("address", user.getAddress());
		map.put("email", user.getEmail());
		map.put("type", user.getType());
		map.put("status", user.getStatus());
		
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource paramSource = new MapSqlParameterSource(map);
		
		super.getNamedParameterJdbcTemplate().update(sql, paramSource, generatedKeyHolder);
		Integer userId = generatedKeyHolder.getKey().intValue();
		user.setUserId(userId);
		
		
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE user SET name=:name,"
				+ " surname=:surname,"
				+ " username=:username,"
				+ " password=:password,"
				+ " phone=:phone,"
				+ " address=:address,"
				+ " email=:email,"
				+ " type=:type,"
				+ " status=:status "
				+ " WHERE userId=:userId";
		
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", user.getName());
		map.put("surname", user.getSurname());
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		map.put("address", user.getAddress());
		map.put("email", user.getEmail());
		map.put("type", user.getType());
		map.put("status", user.getStatus());
		map.put("userId", user.getUserId());
		
		getNamedParameterJdbcTemplate().update(sql, map);
		
	}

	@Override
	public void delete(User user) {
		
		this.delete(user.getUserId());
	}

	@Override
	public void delete(Integer userId) {
		String sql = "DELETE FROM user WHERE userId=?";
		getJdbcTemplate().update(sql, userId);
		
	}

	@Override
	public User findById(Integer userId) {
		String sql = "SELECT userId,name,surname,username,password,phone,address,email,type,status FROM user WHERE userId=?";
		
		User user = getJdbcTemplate().queryForObject(sql, new UserRowMapper(), userId);
		return user;
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT userId,name,surname,username,password,phone,address,email,type,status FROM user";
		return getJdbcTemplate().query(sql, new UserRowMapper());
	}

	@Override
	public List<User> findByProperty(String propName, Object propValue) {
		String sql = "SELECT userId,name,surname,username,password,phone,address,email,type,status FROM user "
				+ "WHERE "+propName+"=?";
		
		List<User> users = getJdbcTemplate().query(sql, new UserRowMapper(),propValue);
		return users;
	}
	
	@Override
	public List<User> findUser(String txt) {
		String sql = "SELECT  userId,name,surname,username,password,phone,address,email,type,status FROM user  WHERE "
				+ "name LIKE '%"+txt+"%' OR "
				+ "surname LIKE '%"+txt+"%' OR "
				+ "username LIKE '%"+txt+"%'";
						
		return getJdbcTemplate().query(sql, new UserRowMapper());
	}


	@Override
	public void delete(Integer[] userIds) {
		String ids = StringUtil.toCommaSeperatedString(userIds);
		String sql = "DELETE FROM  user  WHERE userId IN("+ids+")";
		getJdbcTemplate().update(sql);
			
	}


	@Override
	public void changeUserStatus(Integer userId, Integer status) {
		String sql = "UPDATE user SET status=:status WHERE userId=:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("status", status);
		getNamedParameterJdbcTemplate().update(sql,map);
	}


	@Override
	public void changeUserType(Integer userId, Integer type) {
		String sql = "UPDATE user SET type=:type WHERE userId=:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type", type);
		getNamedParameterJdbcTemplate().update(sql,map);
		
	}


	@Override
	public Boolean isUsernameExist(String username) {
		
		String sql = "SELECT count(username) FROM user WHERE username=?";
		Integer count = getJdbcTemplate().queryForObject(sql, new String[] {username}, Integer.class);
		
		if(count == 1)
			return true;
		else
			return false;
	}

	
}
