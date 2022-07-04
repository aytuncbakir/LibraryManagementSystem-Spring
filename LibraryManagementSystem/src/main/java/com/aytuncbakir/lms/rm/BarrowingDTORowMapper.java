package com.aytuncbakir.lms.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aytuncbakir.lms.domain.Admin;
import com.aytuncbakir.lms.domain.Article;
import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.domain.Member;
import com.aytuncbakir.lms.domain.Novel;
import com.aytuncbakir.lms.domain.Poet;
import com.aytuncbakir.lms.domain.User;
import com.aytuncbakir.lms.dto.BarrowingDTO;

public class BarrowingDTORowMapper implements RowMapper<BarrowingDTO>{

	@Override
	public BarrowingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BarrowingDTO barrow = createBarrowingDTO(rs);	
		if(barrow != null)
			barrow = prepareBarrowingDTO(barrow,rs);
		
		
		return barrow;
	}

	private BarrowingDTO prepareBarrowingDTO(BarrowingDTO barrow, ResultSet rs) {
		try {
			barrow.setBarrowId(rs.getInt("barrowId"));
			barrow.setBookId(rs.getInt("bookId"));
			barrow.setUserId(rs.getInt("userId"));
			barrow.setBarrowDate(rs.getDate("barrowDate"));
			
			return barrow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return barrow;
	}

	private BarrowingDTO createBarrowingDTO(ResultSet rs) {
		BarrowingDTO barrow = null;

		try {
			
			barrow = new BarrowingDTO();
			return  barrow;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return barrow;
	}

}
