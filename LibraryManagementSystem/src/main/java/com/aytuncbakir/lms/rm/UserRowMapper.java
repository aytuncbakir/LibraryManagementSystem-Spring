package com.aytuncbakir.lms.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aytuncbakir.lms.domain.Admin;
import com.aytuncbakir.lms.domain.Librarian;
import com.aytuncbakir.lms.domain.Member;
import com.aytuncbakir.lms.domain.User;

public class UserRowMapper implements RowMapper<User>{


	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = createUser(rs);
		if(user != null)
			user = prepareUser(user,rs);
	
		return user;
	}
	
	private User prepareUser(User user,ResultSet rs) {
		try {
			user.setAddress(rs.getString("address"));
			user.setEmail(rs.getString("email"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setStatus(rs.getInt("status"));
			user.setSurname(rs.getString("surname"));
			user.setType(rs.getInt("type"));
			user.setUserId(rs.getInt("userId"));
			user.setUsername(rs.getString("username"));
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	private User createUser(ResultSet rs) {
		User user = null;
		try {
			if(rs.getInt("type") == 1) {
				user = new Admin();
			}else if(rs.getInt("type")==2) {
				user = new Member();
			}else if(rs.getInt("type")==3) {
				user = new Librarian();
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
		
	}

	

}
