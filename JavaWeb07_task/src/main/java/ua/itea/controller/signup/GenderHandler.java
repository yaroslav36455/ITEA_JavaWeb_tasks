package ua.itea.controller.signup;

import ua.itea.model.Gender;

public class GenderHandler {
	private static final int MIN_LENGTH = 1;
	private static final int MAX_LENGTH = 10;
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

	public void check(String gender) {
		try {
			Enum.valueOf(Gender.class, gender);
			isCorrect = true;
		} catch(IllegalArgumentException e) {
			isCorrect = false;
		}
	}
	
	public String getTip() {
		return isCorrect ? "" : "Gender selection error";
	}
}
