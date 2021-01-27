package ua.itea.form;

import ua.itea.AutoCreatableAttribute;
import ua.itea.SessionAttribute;

@AutoCreatableAttribute
public class SignInForm implements SessionAttribute {
	private String login;
	private boolean accessDenied;
	
	public SignInForm() {
		login = "";
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login == null ? "" : login;
	}
	public boolean isAccessDenied() {
		return accessDenied;
	}
	public void setAccessDenied(boolean accessDenied) {
		this.accessDenied = accessDenied;
	}
}
