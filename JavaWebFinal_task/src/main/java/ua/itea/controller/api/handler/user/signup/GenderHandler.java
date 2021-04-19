package ua.itea.controller.api.handler.user.signup;

import ua.itea.model.users.Gender;

class GenderHandler {
	private boolean isPresent;
	private boolean isCorrect;

	public void check(String gender) {
		reset();
		
		isPresent = gender != null;
		try {
			Enum.valueOf(Gender.class, gender);
			isCorrect = true;
		} catch(IllegalArgumentException | NullPointerException e) {
			isCorrect = false;
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
