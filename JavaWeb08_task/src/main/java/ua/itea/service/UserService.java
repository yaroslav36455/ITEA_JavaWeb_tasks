package ua.itea.service;

import ua.itea.controller.signup.RegisteringUser;
import ua.itea.dao.UserDAO;
import ua.itea.db.sqlite.Connector;
import ua.itea.db.sqlite.UserDAOSqlite;
import ua.itea.model.users.Authentication;
import ua.itea.model.users.User;

public class UserService {
	private UserDAO userDAO;
	
	public UserService() {
		userDAO = new UserDAOSqlite(new Connector());
	}
	
	public boolean setUser(RegisteringUser newUser) {
		return userDAO.insert(newUser);
	}
	
	public User getUser(String login, String password) {
		Authentication auth = new Authentication();
		
		auth.setLogin(login);
		auth.setPassword(password);
		
		return userDAO.select(auth);
	}
}
