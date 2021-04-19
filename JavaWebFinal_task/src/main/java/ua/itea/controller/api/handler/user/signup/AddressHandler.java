package ua.itea.controller.api.handler.user.signup;

import ua.itea.model.users.Address;
class AddressHandler {
	private boolean isPresent;
	private boolean isCorrect;
	
	public void check(String address) {
		reset();
		
		isPresent = address != null;
		try {
			Enum.valueOf(Address.class, address);
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
