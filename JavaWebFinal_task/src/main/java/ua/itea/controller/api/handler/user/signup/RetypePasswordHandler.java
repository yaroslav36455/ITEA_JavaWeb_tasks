package ua.itea.controller.api.handler.user.signup;

class RetypePasswordHandler {
	private boolean isPresent;
	private boolean isMatch;
	
	public void check(String password, String retypePassword) {
		reset();
		
		isPresent = retypePassword != null;
		if(isPresent && password != null) {
			isMatch = password.equals(retypePassword);
		}
	}	
	
	private void reset() {
		isPresent = false;
		isMatch = false;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public boolean isMatch() {
		return isMatch;
	}
}
