package ua.itea.controller.api.handler.user.signin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.itea.api.SignInRequest;
import ua.itea.controller.api.util.SessionAttributeManager;
import ua.itea.dao.UserDAO;
import ua.itea.model.users.Authentication;
import ua.itea.model.users.User;

@Component
public class SignInHandler {
	@Autowired
	private SessionAttributeManager sam;
	@Autowired
	private UserDAO userDAO;

	public void signIn(SignInRequest signInRequest) throws SignInException {
		handleBlocker();
		handleFieldCorrectness(signInRequest);
		
		try {
			handleAlreadySignedIn(signInRequest);
			handleAuthentication(signInRequest);
		} catch (AlreadyAuthenticatedAsSameUserException ex) {
			/* nothing to do */
		}
	}
	
	private void handleBlocker() throws BlockerException {
		SignInBlocker sib = sam.getAttributeOrNull(SignInBlocker.class);
		
		if(sib != null) {
			
			if(sib.isBlocked()) {
				sib.updateTime();
				
				if(sib.isBlocked()) {
					throw new BlockerException();
				}
			}	
		}
	}
	
	private void handleFieldCorrectness(SignInRequest signInRequest)
											throws IncorrectFilledFieldsException {
		int loginLength = signInRequest.getLogin().length();
		int passwordLength = signInRequest.getPassword().length();
		int strippedLoginLength = signInRequest.getLogin().strip().length();
		int strippedPasswordLength = signInRequest.getPassword().strip().length();
		
		if(!(loginLength == strippedLoginLength && loginLength > 0
				&& passwordLength == strippedPasswordLength && passwordLength > 0)) {
			
			sam.getAttribute(SignInBlocker.class).captureMistake();
			
			throw new IncorrectFilledFieldsException();
		}
	}
	
	private void handleAlreadySignedIn(SignInRequest signInRequest)
								throws AlreadyAuthenticatedAsSameUserException,
									   AlreadyAuthenticatedAsAnotherUserException {
		User user = sam.getAttributeOrNull(User.class);
		
		if(user != null) {
			String loginCurrentUser = user.getLogin();
			String loginNewUser = signInRequest.getLogin();
			
			if(loginCurrentUser.equals(loginNewUser)) {
				throw new AlreadyAuthenticatedAsSameUserException();
			} else {
				throw new AlreadyAuthenticatedAsAnotherUserException();
			}
		}
	}
	
	private void handleAuthentication(SignInRequest signInRequest) throws UserNotFoundException {
		Authentication auth = new Authentication(signInRequest.getLogin(),
												 signInRequest.getPassword());
		User user = userDAO.select(auth);

		if(user != null) {
			sam.setAttribute(user);
			sam.removeAttribute(SignInBlocker.class);
		} else {
			sam.getAttribute(SignInBlocker.class).captureMistake();
			throw new UserNotFoundException();
		}
	}
}
