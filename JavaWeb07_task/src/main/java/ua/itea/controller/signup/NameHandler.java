package ua.itea.controller.signup;

import java.util.regex.Pattern;

public class NameHandler {
	private static final int MIN_LENGTH = 2;
	private static final int MAX_LENGTH = 15;
	private static final Pattern PATTERN = Pattern.compile("[\\p{Lu}][\\p{L}&&[^\\p{Lu}]]{"
															+ MIN_LENGTH + ","
															+ MAX_LENGTH + "}");
	private boolean isCorrect;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public static int getMinLength() {
		return MIN_LENGTH;
	}

	public static int getMaxLength() {
		return MAX_LENGTH;
	}
	
	public void check(String name) {
		isCorrect = PATTERN.matcher(name).matches();
	}	
	
	public String getTip() {
		return isCorrect ? "" 
				: "The name must be present and be between "
					+ MIN_LENGTH + " and " + MAX_LENGTH + " letters long. "
				+ "The first letter must be uppercase, all others lowercase";
	}
}
