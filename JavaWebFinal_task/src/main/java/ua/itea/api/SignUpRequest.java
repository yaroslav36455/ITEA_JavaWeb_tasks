package ua.itea.api;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SignUpRequest {
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "login")
	private String login;
	@JsonProperty(value = "password")
	private String password;
	@JsonProperty(value = "retypePassword")
	private String retypePassword;
	@JsonProperty(value = "email")
	private String email;
	@JsonProperty(value = "address")
	private String address;
	@JsonProperty(value = "gender")
	private String gender;
	@JsonProperty(value = "comment")
	private String comment;
	@JsonProperty(value = "amigaAgree")
	private boolean amigaAgree;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRetypePassword() {
		return retypePassword;
	}
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isAmigaAgree() {
		return amigaAgree;
	}
	public void setAmigaAgree(boolean amigaAgree) {
		this.amigaAgree = amigaAgree;
	}
}
