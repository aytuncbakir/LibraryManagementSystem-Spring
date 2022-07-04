package com.aytuncbakir.lms.domain;

public class Novel extends Book{
	
	public Novel() {
		super();
	}

	public Novel(Integer bookId, String name, String author, String isbn, String type, Integer total,
			Integer availableCount) {
		super(bookId, name, author, isbn, type, total, availableCount);
		
	}

}
