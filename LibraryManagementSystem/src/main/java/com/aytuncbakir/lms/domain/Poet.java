package com.aytuncbakir.lms.domain;

public class Poet extends Book{

	public Poet() {
		super();
	}

	public Poet(Integer bookId, String name, String author, String isbn, String type, Integer total,
			Integer availableCount) {
		super(bookId, name, author, isbn, type, total, availableCount);
		
	}

	
}
