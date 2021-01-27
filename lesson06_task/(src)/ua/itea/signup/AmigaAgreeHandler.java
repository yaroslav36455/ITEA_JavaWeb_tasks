package ua.itea.signup;

public class AmigaAgreeHandler {
	private boolean isCorrect;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(boolean agree) {
		isCorrect = agree;
	}	
	
	public String getTip() {
		return isCorrect ? "" : "Browser installation is required";
	}
}
