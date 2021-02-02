package ua.itea.controller.signup;

import ua.itea.model.Address;
import ua.itea.model.Gender;

public class SignUpBuilder {
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
		if(name != null) {
			this.name = name;	
		}
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if(login != null) {
			this.login = login;	
		}
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if(password != null) {
			this.password = password;	
		}
	}
	
	public String getRetypePassword() {
		return retypePassword;
	}
	
	public void setRetypePassword(String retypePassword) {
		if(retypePassword != null) {
			this.retypePassword = retypePassword;	
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		if(email != null) {
			this.email = email;	
		}
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		if(address != null) {
			this.address = address;	
		}
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		if(gender != null) {
			this.gender = gender;	
		}
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		if(comment != null) {
			this.comment = comment;	
		}
	}
	
	public boolean isAmigaAgree() {
		return amigaAgree;
	}
	
	public void setAmigaAgree(String amigaAgree) {
		if(amigaAgree != null) {
			this.amigaAgree = true;	
		}
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
