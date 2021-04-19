package ua.itea.api;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ua.itea.model.users.Address;
import ua.itea.model.users.Gender;

public class UserInfo {
	@Column(name = "name")
	private String name;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	@Enumerated(value = EnumType.ORDINAL)
	private Address address;
	
	@Column(name = "gender")
	@Enumerated(value = EnumType.ORDINAL)
	private Gender gender;
	
	@Column(name = "comment")
	private String comment;

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
