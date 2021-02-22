package ua.itea.controller.signup;

import ua.itea.model.users.Address;
import ua.itea.model.users.Gender;

public class RegisteringUserBuilder {
	private String name = "";
	private String login = "";
	private String password = "";
	private String retypePassword = "";
	private String email = "";
	private String address = Address.DNR.name();
	private String gender = Gender.MALE.name();
	private String comment = "";
	private boolean amigaAgree;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name == null ? "" : name;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login == null ? "" : login;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password == null ? "" : password;
	}
	
	public String getRetypePassword() {
		return retypePassword;
	}
	
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword == null ? "" : retypePassword;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email == null ? "" : email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address == null ? "" : address;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender == null ? "" : gender;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment == null ? "" : comment;
	}
	
	public boolean isAmigaAgree() {
		return amigaAgree;
	}
	
	public void setAmigaAgree(String amigaAgree) {
		this.amigaAgree = amigaAgree != null;
	}

	public void setAmigaAgree(boolean amigaAgree) {
		this.amigaAgree = amigaAgree;
	}

	public RegisteringUser createUser() {
		RegisteringUser regUser = new RegisteringUser();
		
		regUser.setLogin(getLogin());
		regUser.setPassword(getPassword());
		regUser.setName(getName());
		regUser.setEmail(getEmail());
		regUser.setGender(Enum.valueOf(Gender.class, getGender()));
		regUser.setAddress(Enum.valueOf(Address.class, getAddress()));
		regUser.setComment(getComment());
		
		return regUser;
	}
}
