package ua.itea.controller.api.handler.user.signup;

import ua.itea.api.SignUpResponse;

public class SignUpException extends Exception {
	private static final long serialVersionUID = 507676747633923461L;
	private SignUpResponse signUpResponse;
	
	public SignUpException(SignUpResponse signUpResponse) {
		this.signUpResponse = signUpResponse;
	}

	public SignUpResponse getSignUpResponse() {
		return signUpResponse;
	}

	public void setSignUpResponse(SignUpResponse signUpResponse) {
		this.signUpResponse = signUpResponse;
	}
}
