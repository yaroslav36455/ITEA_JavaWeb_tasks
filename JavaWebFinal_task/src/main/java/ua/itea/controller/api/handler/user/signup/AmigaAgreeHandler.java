package ua.itea.controller.api.handler.user.signup;

class AmigaAgreeHandler {
	private boolean isAgree;
	
	public void check(boolean agree) {
		isAgree = agree;
	}

	public boolean isAgree() {
		return isAgree;
	}
}
