package ua.itea.controller.signup;

public class RetypePasswordHandler {
	private boolean isCorrect;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String password, String retypePassword) {
		isCorrect = password.equals(retypePassword);
	}	
	
	public String getTip() {
		return isCorrect ? "" : "Passwords do not match";
	}
}
