package ua.itea.signup;

import java.util.regex.Pattern;

public class EmailHandler {
	private static final Pattern PATTERN = Pattern.compile(
			// local-part
			"([a-z0-9\\p{Punct}&&[^\",.)(:;<>]]+)(\\.[a-z0-9\\p{Punct}&&[^\",.)(:;<>]]+)*"
			// @domain-part
			+ "@(\\["
			+ "(([0-9]|[1-9][0-9]|[1][1-9][0-9]|[2][0-5][0-6])\\.){3}"
			+ "([0-9]|[1-9][0-9]|[1][1-9][0-9]|[2][0-5][0-6])"
			+ "\\]|"
			+ "[a-z0-9][a-z0-9\\-]+(\\.[a-z0-9][a-z0-9\\-]+)*[\\.]?"
			+ ")");

	private boolean isCorrect;
	private boolean isEmpty;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String email) {
		isEmpty = email.isEmpty();
		
		if(!isEmpty ) {
			isCorrect = PATTERN.matcher(email).matches();	
		}
	}
	
	public String getHint() {
		String result;
		
		if(isEmpty) {
			result = "e-mail must be present";
		} else if (!isCorrect) {
			result = "e-mail is incorrect";
		} else {
			result = "";
		}
		
		return result;
	}
}
