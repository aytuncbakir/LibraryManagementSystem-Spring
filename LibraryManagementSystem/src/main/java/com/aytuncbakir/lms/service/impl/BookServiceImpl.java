package com.aytuncbakir.lms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aytuncbakir.lms.dao.BaseDAO;
import com.aytuncbakir.lms.dao.BookDAO;
import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.dto.BarrowingDTO;
import com.aytuncbakir.lms.rm.BookRowMapper;
import com.aytuncbakir.lms.service.BookService;
import com.aytuncbakir.lms.util.StringUtil;

@Service
public class BookServiceImpl extends BaseDAO implements BookService{

	@Autowired
	private BookDAO bookDAO;
	
	@Override
	public void save(Book book) {
		bookDAO.save(book);
	}

	@Override
	public void update(Book book) {
		bookDAO.update(book);
		
	}

	@Override
	public void delete(Integer bookId) {
		bookDAO.delete(bookId);
		
	}

	@Override
	public void delete(Integer[] bookIds) {
		bookDAO.delete(bookIds);
		
	}

	@Override
	public List<Book> findUserBook(Integer userId) {	
		return bookDAO.findByProperty("userId", userId);
	}

	@Override
	public List<Book> findUserBook( String txt) {
		return bookDAO.findUserBook(txt);
	}

	@Override
	public Book findById(Integer bookId) {
		
		return bookDAO.findById(bookId);
	}

	@Override
	public List<Book> findAll() {
		return bookDAO.findAll();
	}

	@Override
	public List<Book> findByProperty(String propName, Object propValue) {
		// TODO Auto-generated method stub
		return bookDAO.findByProperty(propName, propValue);
	}

	@Override
	public boolean barrow(Integer bookId,Integer userId) {
		return bookDAO.barrow(bookId, userId);
		
	}

	@Override
	public boolean give(Integer bookId, Integer userId) {
		return bookDAO.give(bookId, userId);
	}

	@Override
	public Date getTakenDay(Integer bookId, Integer userId) {
		return bookDAO.getTakenDay(bookId, userId);
	}

	@Override
	public List<BarrowingDTO> findAllBarrowedBooks() {
		return bookDAO.findAllBarrowedBooks();
	}

}
