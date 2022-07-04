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

public class BookRowMapper implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = createBook(rs);	
		if(book != null)
			book = prepareBook(book,rs);
		
		
		return book;
	}

	private Book prepareBook(Book book, ResultSet rs) {
		try {
			book.setAuthor(rs.getString("author"));
			book.setAvailableCount(rs.getInt("availableCount"));
			book.setBookId(rs.getInt("bookId"));
			book.setIsbn(rs.getString("isbn"));
			book.setName(rs.getString("name"));
			book.setTotal(rs.getInt("total"));
			book.setType(rs.getString("type"));
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return book;
	}

	private Book createBook(ResultSet rs) {
		Book book = null;

		try {
			
			book = new Novel();
			
			if(rs.getString("type") == "Novel") {
				book = new Novel();
			}else if(rs.getString("type")=="Poet") {
				book = new Poet();
			}
			
			
			return  book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

}
