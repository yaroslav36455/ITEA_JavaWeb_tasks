package ua.itea.controller.signup;

import ua.itea.model.Address;
public class AddressHandler {
	private static final int MIN_LENGTH = 1;
	private static final int MAX_LENGTH = 20;
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
	
	public void check(String address) {
		try {
			Enum.valueOf(Address.class, address);
			isCorrect = true;
		} catch(IllegalArgumentException e) {
			isCorrect = false;
		}
	}
	
	public String getTip() {
		return isCorrect ? "" : "Address selection error";
	}
}
