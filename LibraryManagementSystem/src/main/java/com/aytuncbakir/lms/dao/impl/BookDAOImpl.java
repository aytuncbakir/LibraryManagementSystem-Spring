package com.aytuncbakir.lms.dao.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.aytuncbakir.lms.dao.BaseDAO;
import com.aytuncbakir.lms.dao.BookDAO;
import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.dto.BarrowingDTO;
import com.aytuncbakir.lms.rm.BarrowingDTORowMapper;
import com.aytuncbakir.lms.rm.BookRowMapper;
import com.aytuncbakir.lms.util.StringUtil;

@Repository
public class BookDAOImpl extends BaseDAO implements BookDAO{
	
	
	@Override
	public void save(Book book) {
		
		String sql = "INSERT INTO book(name, author, isbn, type, total, availableCount) "
				+ " VALUES(:name, :author, :isbn, :type, :total, :availableCount)";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", book.getName());
		map.put("author",book.getAuthor());
		map.put("isbn",book.getIsbn());
		map.put("type", book.getType());
		map.put("total", book.getTotal());
		map.put("availableCount", book.getAvailableCount());
		
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource paramSource = new MapSqlParameterSource(map);
		
		getNamedParameterJdbcTemplate().update(sql, paramSource, generatedKeyHolder);
		book.setBookId(generatedKeyHolder.getKey().intValue());
		
	}

	@Override
	public void update(Book book) {
		
		String sql = "UPDATE book SET name=:name, author=:author, isbn=:isbn, type=:type, total=:total, availableCount=:availableCount "
				+ "WHERE bookId=:bookId";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookId", book.getBookId());
		map.put("name", book.getName());
		map.put("author",book.getAuthor());
		map.put("isbn",book.getIsbn());
		map.put("type", book.getType());
		map.put("total", book.getTotal());
		map.put("availableCount", book.getAvailableCount());
		
		getNamedParameterJdbcTemplate().update(sql, map);
		
	}

	@Override
	public void delete(Book book) {	
		this.delete(book.getBookId());
	}

	@Override
	public void delete(Integer bookId) {
		String sql = "DELETE FROM book WHERE bookId=?";
		getJdbcTemplate().update(sql, bookId );
	}

	@Override
	public Book findById(Integer bookId) {
		String sql = "SELECT bookId, name, author, isbn, type, total, availableCount FROM book WHERE bookId=?";	
		return getJdbcTemplate().queryForObject(sql, new BookRowMapper(), bookId);
	}

	@Override
	public List<Book> findAll() {
		String sql = "SELECT bookId, name, author, isbn, type, total, availableCount FROM book";	
		return getJdbcTemplate().query(sql, new BookRowMapper());
	}

	@Override
	public List<Book> findByProperty(String propName, Object propValue) {
		String sql = "SELECT bookId, name, author, isbn, type, total, availableCount FROM book WHERE "+propName+"=?";	
		return getJdbcTemplate().query(sql, new BookRowMapper(),propValue);
	}

	@Override
	public void delete(Integer[] bookIds) {
		String ids = StringUtil.toCommaSeperatedString(bookIds);
		String sql = "DELETE FROM  book  WHERE bookId IN("+ids+")";
		getJdbcTemplate().update(sql);
		
	}

	@Override
	public List<Book> findUserBook(Integer userId) {
		
		return null;
	}

	@Override
	public List<Book> findUserBook(String txt) {
		String sql = "SELECT bookId, name, author, isbn, type, total, availableCount FROM book WHERE "
				+ "name LIKE '%"+txt+"%' OR "
				+ "author LIKE '%"+txt+"%'";
						
		return getJdbcTemplate().query(sql, new BookRowMapper());
	}

	@Override
	public boolean barrow(Integer bookId,Integer userId) {
		
		Book book = findById( bookId);
		
		if(book != null && book.getAvailableCount() > 0) {
		
			String sql = "UPDATE book SET name=:name, author=:author, isbn=:isbn, type=:type, total=:total, availableCount=:availableCount "
					+ "WHERE bookId=:bookId";
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bookId", book.getBookId());
			map.put("name", book.getName());
			map.put("author",book.getAuthor());
			map.put("isbn",book.getIsbn());
			map.put("type", book.getType());
			map.put("total", book.getTotal());
			map.put("availableCount", book.getAvailableCount()-1);
			
			getNamedParameterJdbcTemplate().update(sql, map);
			insertBarrow(book.getBookId(), userId);
			
		
			return true;
		}
		return false;
	}
	
	
	
	
	private void insertBarrow(Integer bookId, Integer userId) {
		
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		String sql = "INSERT INTO barrowed_books (bookId ,userId, barrowDate) VALUES ( '"+bookId+"', '"+userId +"', '"+sqlDate+"')";
		getJdbcTemplate().update(sql);
	}
	
	@Override
	public boolean give(Integer bookId, Integer userId) {
		
		Book book = findById(bookId);
		
		if(book != null && book.getAvailableCount() < book.getTotal()) {
		
			String sql = "UPDATE book SET name=:name, author=:author, isbn=:isbn, type=:type, total=:total, availableCount=:availableCount "
					+ "WHERE bookId=:bookId";
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bookId", book.getBookId());
			map.put("name", book.getName());
			map.put("author",book.getAuthor());
			map.put("isbn",book.getIsbn());
			map.put("type", book.getType());
			map.put("total", book.getTotal());
			map.put("availableCount", book.getAvailableCount()+1);
			
			getNamedParameterJdbcTemplate().update(sql, map);
			deleteBarrow( bookId,  userId);
			return true;
		}
		return false;
		
	}
	
	private void deleteBarrow(Integer bookId, Integer userId) {
		String sql = "DELETE FROM  barrowed_books WHERE bookId='"+bookId+"' AND userId='"+userId+"'";
		getJdbcTemplate().update(sql);
	}

	@Override
	public Date getTakenDay(Integer bookId, Integer userId) {
		String sql = "SELECT barrowDate FROM  barrowed_books WHERE bookId='"+bookId+"' AND userId='"+userId+"'";
		SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(sql);
		Date date = null;
		while(rowSet.next())
			date  = rowSet.getDate("barrowDate");
		  
		return date;
	}

	@Override
	public List<BarrowingDTO> findAllBarrowedBooks() {
		String sql = "SELECT barrowId, bookId, userId, barrowDate FROM barrowed_books";	
		return getJdbcTemplate().query(sql, new BarrowingDTORowMapper());
	}

	
}
