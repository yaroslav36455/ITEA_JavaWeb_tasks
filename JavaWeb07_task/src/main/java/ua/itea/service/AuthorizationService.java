package ua.itea.service;

import java.sql.SQLException;

import ua.itea.db.Connector;
import ua.itea.db.UsersDB;
import ua.itea.model.Authentication;
import ua.itea.model.User;

public class AuthorizationService {
	private Connector conn;
	private UsersDB db;
	
	public AuthorizationService() {
		conn = new Connector();
		
		try {
			db = new UsersDB(conn.getConnection());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public User getUser(String login, String password) {
		User user = null;
		Authentication auth = new Authentication();
		
		auth.setLogin(login);
		auth.setPassword(password);
		
		try {
			user = db.getUser(conn.getConnection(), auth);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
