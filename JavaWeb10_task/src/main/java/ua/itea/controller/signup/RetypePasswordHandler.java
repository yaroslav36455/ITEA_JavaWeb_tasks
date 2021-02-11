package ua.itea.controller.signup;

public class RetypePasswordHandler {
	private boolean isCorrect = true;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String password, String retypePassword) {
		System.out.println("password " + password);
		System.out.println("retype password " + retypePassword);
		isCorrect = password.equals(retypePassword);
	}	
	
	public String getTip() {
		return isCorrect ? "" : "Пароли не совпадают";
	}
}
