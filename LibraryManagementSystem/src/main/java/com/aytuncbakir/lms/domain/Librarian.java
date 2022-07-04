package com.aytuncbakir.lms.domain;

public class Librarian extends User{

	public Librarian() {
		super();
	}

	public Librarian(String name, String surname, String username, String password, String phone,
			String address, String email, Integer type, Integer status) {
		super(name, surname, username, password, phone, address, email, type, status);
	}

}
