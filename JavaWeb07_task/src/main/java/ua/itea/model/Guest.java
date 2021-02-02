package ua.itea.model;

import ua.itea.controller.AutoCreatableAttribute;
import ua.itea.controller.SessionAttribute;
import ua.itea.controller.signin.SignInBlocker;

@AutoCreatableAttribute
public class Guest implements SessionAttribute {
	private String login;
	private boolean lastAccessDenied;
	private SignInBlocker signInBlocker;
	
	public Guest() {
		login = "";
		signInBlocker = new SignInBlocker();
	}
	
	public boolean isLastAccessDenied() {
		return lastAccessDenied;
	}

	public void setLastAccessDenied(boolean lastAccessDenied) {
		this.lastAccessDenied = lastAccessDenied;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login == null ? "" : login;
	}

	public SignInBlocker getSignInBlocker() {
		return signInBlocker;
	}
	
	public boolean isBlocked() {
		return signInBlocker.isBlocked();
	}
}
