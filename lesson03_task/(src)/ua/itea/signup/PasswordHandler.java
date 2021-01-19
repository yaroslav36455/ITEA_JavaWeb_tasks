package ua.itea.signup;

import java.util.regex.Pattern;

public class PasswordHandler {
	private static final Pattern PRIMARY = Pattern.compile("[A-Za-z0-9\\p{Punct}]{6,14}");
	private static final Pattern UPPER_CASE_LATIN = Pattern.compile("[A-Z]+");
	private static final Pattern LOWER_CASE_LATIN = Pattern.compile("[a-z]+");
	private static final Pattern DIGITS = Pattern.compile("[0-9]+");
	private static final Pattern PUNCT = Pattern.compile("[\\p{Punct}]+");
	
	private PassLevel passLevel;
	
	public boolean isCorrect() {
		return passLevel.ordinal() > PassLevel.WEAK.ordinal();
	}
	
	public PassLevel getPassLevel() {
		return passLevel;
	}

	public void check(String password) {
		int score = 0;
		final int testCount = 5;
		
		if(!primaryTest(password)) {
			passLevel = PassLevel.ERROR;
			return;
		}
		
		if(lengthTest(password)) {
			score++;
		}
		
		if(upperCaseTest(password)) {
			score++;
		}
		
		if(lowerCaseTest(password)) {
			score++;
		}
		
		if(digitsTest(password)) {
			score++;
		}
		
		if(punctTest(password)) {
			score++;
		}
		
		passLevel = PassLevel.values()[(int)((float) score / testCount * PassLevel.values().length)];
	}

	private boolean primaryTest(String password) {		
		return PRIMARY.matcher(password).matches();
	}
	
	private boolean lengthTest(String password) {
		return password.length() > 8;
	}
	
	private boolean upperCaseTest(String password) {
		return UPPER_CASE_LATIN.matcher(password).find();
	}

	private boolean lowerCaseTest(String password) {
		return LOWER_CASE_LATIN.matcher(password).find();
	}
	
	private boolean digitsTest(String password) {
		return DIGITS.matcher(password).find();
	}
	
	private boolean punctTest(String password) {
		return PUNCT.matcher(password).find();
	}
	
	public String getHint() {
		return passLevel == PassLevel.EXCELLENT ? ""
				: "The password must be present and composed of characters A-Z, a-z,"
				+ " 0-9 and punctuation characters !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
				+ " (each category of characters must be present)";
	}
	
	private enum PassLevel {
		ERROR, BAD, WEAK, GOOD, EXCELLENT
	}
}
