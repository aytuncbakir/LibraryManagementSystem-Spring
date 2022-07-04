package com.aytuncbakir.lms.dto;

import java.util.Date;

public class BarrowingDTO {
	
	private int barrowId;
	private Integer userId;
	private Integer bookId;
	private Date barrowDate;
	private long days;
	


	public BarrowingDTO() {
	}

	public BarrowingDTO(int barrowId, Integer userId, Integer bookId, Date barrowDate) {
		super();
		this.barrowId = barrowId;
		this.userId = userId;
		this.bookId = bookId;
		this.barrowDate = barrowDate;
	}

	public int getBarrowId() {
		return barrowId;
	}

	public void setBarrowId(int barrowId) {
		this.barrowId = barrowId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Date getBarrowDate() {
		return barrowDate;
	}

	public void setBarrowDate(Date barrowDate) {
		this.barrowDate = barrowDate;
	}
	
	
	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}
	
	
}
