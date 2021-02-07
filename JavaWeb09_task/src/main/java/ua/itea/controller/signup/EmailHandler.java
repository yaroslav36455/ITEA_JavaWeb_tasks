package ua.itea.controller.signup;

import java.util.regex.Pattern;

public class EmailHandler {
	private static final int MIN_LENGTH = 3;
	private static final int MAX_LENGTH = 50;
	private static final Pattern PATTERN = Pattern.compile(
			// local-part
			"([a-z0-9\\p{Punct}&&[^\",.)(:;<>]]+)(\\.[a-z0-9\\p{Punct}&&[^\",.)(:;<>]]+)*"
			// @domain-part
			+ "@(\\["
			+ "(([0-9]|[1-9][0-9]|[1][1-9][0-9]|[2][0-5][0-6])\\.){3}"
			+ "([0-9]|[1-9][0-9]|[1][1-9][0-9]|[2][0-5][0-6])"
			+ "\\]|"
			+ "[a-z0-9][a-z0-9\\-]+(\\.[a-z0-9][a-z0-9\\-]+)*[\\.]?"
			+ ")");

	private boolean isCorrect = true;
	private boolean isEmpty = true;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public static int getMinLength() {
		return MIN_LENGTH;
	}

	public static int getMaxLength() {
		return MAX_LENGTH;
	}
	
	public void check(String email) {
		isEmpty = email.isEmpty();
		
		if(!isEmpty ) {
			isCorrect = PATTERN.matcher(email).matches();	
		}
	}
	
	public String getTip() {
		String result;
		
		if(isEmpty) {
			result = "Адрес электронной почты является необходимым";
		} else if (!isCorrect) {
			result = "Адрес электронной почты неверный";
		} else {
			result = "";
		}
		
		return result;
	}
}
