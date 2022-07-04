package com.aytuncbakir.lms.domain;

public abstract class User{

	

	private Integer userId; 
	private String name; 
	private String surname; 
	private String username; 
	private String password; 
	private String phone; 
	private String address; 
	private String email;
	private Integer type;
	private Integer status;
	
	public User() {
		
	}
	
	public User(String name, String surname, String username, String password, String phone,
			String address, String email, Integer type, Integer status) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.type = type;
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", password=" + password + ", phone=" + phone + ", address=" + address + ", email=" + email
				+ ", type=" + type + ", status=" + status + "]";
	}
	
}
