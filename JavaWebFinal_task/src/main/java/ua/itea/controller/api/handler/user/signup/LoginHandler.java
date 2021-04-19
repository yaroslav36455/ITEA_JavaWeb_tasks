package ua.itea.controller.api.handler.user.signup;

import java.util.regex.Pattern;

public class LoginHandler {
	private static final int MIN_LENGTH = 6;
	private static final int MAX_LENGTH = 14;
	private static final Pattern ALLOWED_CHARACTERS = Pattern.compile("[A-Za-z0-9\\p{Punct}]{"
																	  + MIN_LENGTH + ","
																	  + MAX_LENGTH + "}");
	
	private boolean isPresent;
	private boolean isLengthInRange;
	private boolean isAllowedCharactersPresent;
	
	public static int getMinLength() {
		return MIN_LENGTH;
	}

	public static int getMaxLength() {
		return MAX_LENGTH;
	}
	
	public void check(String login) {
		reset();
		
		isPresent = login != null;
		if(isPresent) {
			isLengthInRange = login.length() >= MIN_LENGTH && login.length() <= MAX_LENGTH;
			isAllowedCharactersPresent = ALLOWED_CHARACTERS.matcher(login).matches();
		}
	}
	
	private void reset() {
		isPresent = false;
		isLengthInRange = false;
		isAllowedCharactersPresent = false;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public boolean isLengthInRange() {
		return isLengthInRange;
	}

	public boolean isAllowedCharactersPresent() {
		return isAllowedCharactersPresent;
	}
}
