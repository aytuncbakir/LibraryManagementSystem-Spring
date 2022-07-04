package com.aytuncbakir.lms.domain;

public class Admin extends User{

	public Admin() {
		super();
	}

	public Admin(String name, String surname, String username, String password, String phone,
			String address, String email, Integer type, Integer status) {
		super(name, surname, username, password, phone, address, email, type, status);
	}
}
