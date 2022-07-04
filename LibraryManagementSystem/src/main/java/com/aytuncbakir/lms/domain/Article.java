package com.aytuncbakir.lms.domain;

public class Article extends Book{

	public Article() {
		super();
	}

	public Article(Integer bookId, String name, String author, String isbn, String type, Integer total,
			Integer availableCount) {
		super(bookId, name, author, isbn, type, total, availableCount);
	}

	
}
