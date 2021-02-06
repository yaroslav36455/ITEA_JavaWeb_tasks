package ua.itea.controller.signup;

import java.util.regex.Pattern;

public class LoginHandler {
	private static final int MIN_LENGTH = 6;
	private static final int MAX_LENGTH = 14;
	private static final Pattern PATTERN = Pattern.compile("[A-Za-z0-9\\p{Punct}]{"
														   + MIN_LENGTH + ","
														   + MAX_LENGTH + "}");
	private boolean isCorrect;
	
	public static int getMinLength() {
		return MIN_LENGTH;
	}

	public static int getMaxLength() {
		return MAX_LENGTH;
	}

	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String login) {
		isCorrect = PATTERN.matcher(login).matches();
	}
	
	public String getMessageError() {
		return isCorrect ? "" : getTip();
	}
	
	public String getTip() {
		return isCorrect ? ""
						 : "Логин является необходимым."
						 		+ " Длина логина должна находиться в пределах от "
						 		+ MIN_LENGTH + " до " + MAX_LENGTH + " символов. Разрешены символы "
						 		+ "A-Z, a-z, 0-9 и символы пунктуации !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	}
}
