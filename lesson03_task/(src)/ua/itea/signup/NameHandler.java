package ua.itea.signup;

import java.util.regex.Pattern;

public class NameHandler {
	private static final Pattern PATTERN = Pattern.compile("[\\p{Lu}][\\p{L}&&[^\\p{Lu}]]{1,14}");
	private boolean isCorrect;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String name) {
		isCorrect = PATTERN.matcher(name).matches();
	}	
	
	public String getHint() {
		return isCorrect ? "" 
				: "The name must be present and be between 2 and 15 letters long. "
				+ "The first letter must be uppercase, all others lowercase";
	}
}
