package com.aytuncbakir.lms.service;

import java.util.Date;
import java.util.List;

import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.dto.BarrowingDTO;

public interface BookService {
	
	public void save(Book book);
	public void update(Book book);
	public void delete(Integer bookId);
	public void delete(Integer[] bookIds);
	public List<Book> findUserBook(Integer userId);
	public List<Book> findUserBook(String txt);
	public Book findById(Integer bookId);
	public List<Book> findAll();
	public List<Book> findByProperty(String propName, Object propValue);
	public boolean barrow(Integer bookId, Integer userId);
	public boolean give(Integer bookId, Integer userId);
	public Date getTakenDay(Integer bookId, Integer userId);
	public List<BarrowingDTO> findAllBarrowedBooks();
	

}
