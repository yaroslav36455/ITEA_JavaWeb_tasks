package ua.itea.controller.api.handler.user.signup;

import java.util.regex.Pattern;

public class NameHandler {
	private static final int MIN_LENGTH = 2;
	private static final int MAX_LENGTH = 15;
	private static final Pattern ALLOWED_CHARACTERS
						= Pattern.compile("[A-Za-z]{" + MIN_LENGTH + "," + MAX_LENGTH + "}");
	private static final Pattern VALID_CHARACTER_CASE
						= Pattern.compile("[\\p{Lu}][\\p{Ll}]{" + (MIN_LENGTH - 1) + ","
			  												    + (MAX_LENGTH - 1) + "}");
	
	private boolean isPresent;
	private boolean isLengthInRange;
	private boolean isAllowedCharactersPresent;
	private boolean isValidCharacterCase;
	
	public static int getMinLength() {
		return MIN_LENGTH;
	}

	public static int getMaxLength() {
		return MAX_LENGTH;
	}
	
	public void check(String name) {
		reset();
		
		isPresent = name != null;
		if(isPresent) {
			isLengthInRange = name.length() >= MIN_LENGTH && name.length() <= MAX_LENGTH;
			if(isLengthInRange) {
				isAllowedCharactersPresent = ALLOWED_CHARACTERS.matcher(name).matches();
				isValidCharacterCase = VALID_CHARACTER_CASE.matcher(name).matches();
			}
		}
	}
	
	private void reset() {
		isPresent = false;
		isLengthInRange = false;
		isAllowedCharactersPresent = false;
		isValidCharacterCase = false;
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

	public boolean isValidCharacterCase() {
		return isValidCharacterCase;
	}
}
