package ua.itea.signup;

import java.util.regex.Pattern;

public class LoginHandler {
	private static final Pattern PATTERN = Pattern.compile("[A-Za-z0-9\\p{Punct}]{6,14}");
	private boolean isCorrect;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String login) {
		isCorrect = PATTERN.matcher(login).matches();
	}
	
	public String getMessageError() {
		return isCorrect ? "" : getHint();
	}
	
	public String getHint() {
		return isCorrect ? "" 
				: "The login must be present. Login length must be from 6 to 14 characters "
				+ "A-Z, a-z, 0-9 and punctuation characters !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	}
}
