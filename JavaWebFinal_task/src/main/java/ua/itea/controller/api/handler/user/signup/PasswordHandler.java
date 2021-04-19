package ua.itea.controller.api.handler.user.signup;

import java.util.regex.Pattern;

class PasswordHandler {
	private static final int MIN_LENGTH = 8;
	private static final int MAX_LENGTH = 15;
	private static final Pattern UPPER_CASE_LATIN = Pattern.compile("[\\p{Lu}]+");
	private static final Pattern LOWER_CASE_LATIN = Pattern.compile("[\\p{Ll}]+");
	private static final Pattern DIGITS = Pattern.compile("[0-9]+");
	private static final Pattern PUNCTUATIONS = Pattern.compile("[\\p{Punct}]+");
	
	private boolean isPresent;
	private boolean isLengthInRange;
	private boolean isUppercaseLettersPresent;
	private boolean isLowercaseLettersPresent;
	private boolean isDigitsPresent;
	private boolean isPunctuationsPresent;
	
	public static int getMinLength() {
		return MIN_LENGTH;
	}

	public static int getMaxLength() {
		return MAX_LENGTH;
	}

	public void check(String password) {
		reset();
		
		isPresent = password != null;
		if(isPresent) {
			isLengthInRange = password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH;
			isUppercaseLettersPresent = UPPER_CASE_LATIN.matcher(password).find();
			isLowercaseLettersPresent = LOWER_CASE_LATIN.matcher(password).find();
			isDigitsPresent = DIGITS.matcher(password).find();
			isPunctuationsPresent = PUNCTUATIONS.matcher(password).find();
			
		}
	}
	
	private void reset() {
		isPresent = false;
		isLengthInRange = false;
		isUppercaseLettersPresent = false;
		isLowercaseLettersPresent = false;
		isDigitsPresent = false;
		isPunctuationsPresent = false;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public boolean isLengthInRange() {
		return isLengthInRange;
	}

	public boolean isUppercaseLettersPresent() {
		return isUppercaseLettersPresent;
	}

	public boolean isLowercaseLettersPresent() {
		return isLowercaseLettersPresent;
	}

	public boolean isDigitsPresent() {
		return isDigitsPresent;
	}

	public boolean isPunctuationsPresent() {
		return isPunctuationsPresent;
	}
}
