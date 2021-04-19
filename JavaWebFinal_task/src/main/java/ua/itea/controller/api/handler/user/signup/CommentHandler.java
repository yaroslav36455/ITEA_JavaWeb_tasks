package ua.itea.controller.api.handler.user.signup;

public class CommentHandler {
	private static final int MAX_LENGTH = 256;
	private boolean isPresent;
	private boolean isLengthInRange;
	
	public static int getMaxLength() {
		return MAX_LENGTH;
	}

	public void check(String comment) {
		reset();
		
		isPresent = comment != null && comment.length() > 0;
		if(isPresent) {
			isLengthInRange = comment.length() <= MAX_LENGTH;
		}
	}
	
	private void reset() {
		isPresent = false;
		isLengthInRange = false;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public boolean isLengthInRange() {
		return isLengthInRange;
	}
}
