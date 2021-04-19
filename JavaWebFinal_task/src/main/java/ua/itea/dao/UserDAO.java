package ua.itea.dao;

import ua.itea.controller.api.handler.user.signup.RegisteringUser;
import ua.itea.model.users.Authentication;
import ua.itea.model.users.User;

public interface UserDAO {
	public boolean insert(RegisteringUser regUser);
	public User select(Authentication authentication);
}
