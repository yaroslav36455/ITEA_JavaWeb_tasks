package ua.itea.model.users;

import ua.itea.controller.SessionAttribute;

public class User implements SessionAttribute {
	private long id;
	private String name;
	private String login;
	private String email;
	private Address address;
	private Gender gender;
	private String comment;
	
	public User(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
