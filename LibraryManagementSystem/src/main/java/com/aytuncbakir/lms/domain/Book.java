package com.aytuncbakir.lms.domain;

public abstract class Book{

	private Integer bookId; 
	private String name; 
	private String author; 
	private String isbn; 
	private String type; 
	private Integer total; 
	private Integer availableCount;
	public Book() {
		
	}
	public Book(Integer bookId, String name, String author, String isbn, String type, Integer total,
			Integer availableCount) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		this.type = type;
		this.total = total;
		this.availableCount = availableCount;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(Integer availableCount) {
		this.availableCount = availableCount;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", author=" + author + ", isbn=" + isbn + ", type=" + type
				+ ", total=" + total + ", availableCount=" + availableCount + "]";
	}
	
	
	
}
