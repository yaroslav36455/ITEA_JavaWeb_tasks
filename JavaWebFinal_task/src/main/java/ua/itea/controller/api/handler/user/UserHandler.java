package ua.itea.controller.api.handler.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.itea.api.UserInfo;
import ua.itea.controller.api.util.SessionAttributeManager;
import ua.itea.model.users.User;

@Component
public class UserHandler {
	@Autowired
	private SessionAttributeManager sam;
	
	public UserInfo getInfo() throws UnauthenticatedUserException {
		User user = sam.getAttributeOrNull(User.class);
		
		if(user == null) {
			throw new UnauthenticatedUserException();
		}
		
		UserInfo userInfo = new UserInfo();
		userInfo.setName(user.getName());
		userInfo.setGender(user.getGender());
		userInfo.setLogin(user.getLogin());
		userInfo.setEmail(user.getEmail());
		userInfo.setAddress(user.getAddress());
		userInfo.setComment(user.getComment());
		
		return userInfo;
	}
	
	public void remove() {		
		sam.removeAttribute(User.class);
	}
}
