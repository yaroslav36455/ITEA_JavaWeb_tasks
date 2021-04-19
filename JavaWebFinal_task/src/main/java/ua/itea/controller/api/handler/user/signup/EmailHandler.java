package ua.itea.controller.api.handler.user.signup;

import java.util.regex.Pattern;

public class EmailHandler {
	private static final int MAX_LENGTH = 100;
	private static final Pattern PATTERN
			= Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	
	private boolean isPresent;
	private boolean isCorrect;
	
	public static int getMaxLength() {
		return MAX_LENGTH;
	}
	
	public void check(String email) {
		reset();
		
		isPresent = email != null;
		if(isPresent) {
			isCorrect = PATTERN.matcher(email).matches();
		}
	}
	
	private void reset() {
		isPresent = false;
		isCorrect = false;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public boolean isCorrect() {
		return isCorrect;
	}
}
