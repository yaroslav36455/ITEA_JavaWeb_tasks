package ua.itea.signup;

public class GenderHandler {
	private boolean isCorrect;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String gender) {
		isCorrect = gender.equals("M") || gender.equals("F");
	}
	
	public String getHint() {
		return isCorrect ? "" : "Gender selection error";
	}
}
