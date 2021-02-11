package ua.itea.model.users;

import java.util.Objects;

public class Authentication {
	private String login;
	private String password;
	
	public Authentication() {
		/* empty */
	}
	
	public Authentication(String login, String password) {
		this.login = login;
		this.password = password;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(login);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Authentication other = (Authentication) obj;
		return Objects.equals(login, other.login);
	}
}
