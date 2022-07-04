package com.aytuncbakir.lms.domain;

public class Member extends User{

	public Member() {
		super();
	}

	public Member(String name, String surname, String username, String password, String phone,
			String address, String email, Integer type, Integer status) {
		super(name, surname, username, password, phone, address, email, type, status);
	}
}
