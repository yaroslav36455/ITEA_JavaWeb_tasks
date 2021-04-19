package ua.itea.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInRequest {
	@JsonProperty(value = "login")
	private String login;
	@JsonProperty(value = "password")
	private String password;
	
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
}
