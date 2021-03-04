package ua.itea.controller.signup;

public class CommentHandler {
	private static final int MIN_LENGTH = 1;
	private static final int MAX_LENGTH = 256;
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
	
	public void check(String comment) {
		isCorrect = !comment.trim().isEmpty();
	}
	
	public String getTip() {
		return isCorrect ? "" : "Расскажите нам что-нибудь, это необходимо!";
	}
}
