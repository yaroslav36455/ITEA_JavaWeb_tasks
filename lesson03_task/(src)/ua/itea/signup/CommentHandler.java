package ua.itea.signup;

public class CommentHandler {
	private boolean isCorrect;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String comment) {
		isCorrect = !comment.trim().isEmpty();
	}
	
	public String getHint() {
		return isCorrect ? "" : "Tell us something, it is necessary!";
	}
}
