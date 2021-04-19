package ua.itea.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpResponse {
	private static final int NAME_ERROR                              = 0b00000000000000000000000000001111;
	private static final int LOGIN_ERROR                             = 0b00000000000000000000000001110000;
	private static final int PASSWORD_ERROR                          = 0b00000000000000000001111110000000;
	private static final int RETYPE_PASSWORD_ERROR                   = 0b00000000000000000110000000000000;
	private static final int EMAIL_ERROR                             = 0b00000000000000011000000000000000;
	private static final int ADDRESS_ERROR                           = 0b00000000000001100000000000000000;
	private static final int GENDER_ERROR                  			 = 0b00000000000110000000000000000000;
	private static final int COMMENT_ERROR                  		 = 0b00000000011000000000000000000000;
	
	private static final int NAME_NOT_PRESENT                        = 0b00000000000000000000000000000001;
	private static final int NAME_LENGTH_OUT_OF_RANGE                = 0b00000000000000000000000000000010;
	private static final int NAME_NOT_ALLOWED_CHARACTERS_PRESENT     = 0b00000000000000000000000000000100;
	private static final int NAME_NOT_ALLOWED_CHARACTER_CASE_PRESENT = 0b00000000000000000000000000001000;
	
	private static final int LOGIN_NOT_PRESENT                       = 0b00000000000000000000000000010000;
	private static final int LOGIN_LENGTH_OUT_OF_RANGE               = 0b00000000000000000000000000100000;
	private static final int LOGIN_NOT_ALLOWED_CHARACTERS_PRESENT    = 0b00000000000000000000000001000000;
	
	private static final int PASSWORD_NOT_PRESENT                    = 0b00000000000000000000000010000000;
	private static final int PASSWORD_LENGTH_OUT_OF_RANGE            = 0b00000000000000000000000100000000;
	private static final int PASSWORD_UPPERCASE_LETTERS_NOT_PRESENT  = 0b00000000000000000000001000000000;
	private static final int PASSWORD_LOWERCASE_LETTERS_NOT_PRESENT  = 0b00000000000000000000010000000000;
	private static final int PASSWORD_DIGITS_NOT_PRESENT             = 0b00000000000000000000100000000000;
	private static final int PASSWORD_PUNCTUATIONS_NOT_PRESENT       = 0b00000000000000000001000000000000;
	
	private static final int RETYPE_PASSWORD_NOT_PRESENT             = 0b00000000000000000010000000000000;
	private static final int RETYPE_PASSWORD_NOT_MATCH               = 0b00000000000000000100000000000000;
	
	private static final int EMAIL_NOT_PRESENT                      = 0b00000000000000001000000000000000;
	private static final int EMAIL_IS_INCORRECT                     = 0b00000000000000010000000000000000;
	
	private static final int ADDRESS_NOT_PRESENT                    = 0b00000000000000100000000000000000;
	private static final int ADDRESS_IS_INCORRECT                   = 0b00000000000001000000000000000000;
	
	private static final int GENDER_NOT_PRESENT                     = 0b00000000000010000000000000000000;
	private static final int GENDER_IS_INCORRECT                    = 0b00000000000100000000000000000000;
	
	private static final int COMMENT_NOT_PRESENT                    = 0b00000000001000000000000000000000;
	private static final int COMMENT_LENGTH_OUT_OF_RANGE            = 0b00000000010000000000000000000000;

	private static final int AMIGA_AGREE                            = 0b00100000000000000000000000000000;
	private static final int LOGIN_AND_OR_EMAIL_USED                = 0b01000000000000000000000000000000;
	
	@JsonProperty(value = "error")
	private int error;
	
	public void setError(int error) {
		this.error = error;
	}
	public int getError() {
		return error;
	}
	@JsonIgnore
	public boolean isError() {
		return error != 0;
	}
	
	@JsonIgnore
	public boolean isNameError() {
		return get(NAME_ERROR);
	}
	@JsonIgnore
	public void setNameNotPresent(boolean state) {
		set(state, NAME_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isNameNotPresent() {
		return get(NAME_NOT_PRESENT);
	}
	@JsonIgnore
	public void setNameLengthOutOfRange(boolean state) {
		set(state, NAME_LENGTH_OUT_OF_RANGE);
	}
	@JsonIgnore
	public boolean isNameLengthOutOfRange() {
		return get(NAME_LENGTH_OUT_OF_RANGE);
	}
	@JsonIgnore
	public void setNameNotAllowedCharactersPresent(boolean state) {
		set(state, NAME_NOT_ALLOWED_CHARACTERS_PRESENT);
	}
	@JsonIgnore
	public boolean isNameNotAllowedCharactersPresent() {
		return get(NAME_NOT_ALLOWED_CHARACTERS_PRESENT);
	}
	@JsonIgnore
	public void setNameNotAllowedCharacterCasePresent(boolean state) {
		set(state, NAME_NOT_ALLOWED_CHARACTER_CASE_PRESENT);
	}
	@JsonIgnore
	public boolean isNameNotAllowedCharacterCasePresent() {
		return get(NAME_NOT_ALLOWED_CHARACTER_CASE_PRESENT);
	}
	
	
	@JsonIgnore
	public boolean isLoginError() {
		return get(LOGIN_ERROR);
	}
	@JsonIgnore
	public void setLoginNotPreseent(boolean state) {
		set(state, LOGIN_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isLoginNotPreseent() {
		return get(LOGIN_NOT_PRESENT);
	}
	@JsonIgnore
	public void setLoginLengthOutOfRange(boolean state) {
		set(state, LOGIN_LENGTH_OUT_OF_RANGE);
	}
	@JsonIgnore
	public boolean isLoginLengthOutOfRange() {
		return get(LOGIN_LENGTH_OUT_OF_RANGE);
	}
	@JsonIgnore
	public void setLoginNotAllowedCharactersPresent(boolean state) {
		set(state, LOGIN_NOT_ALLOWED_CHARACTERS_PRESENT);
	}
	@JsonIgnore
	public boolean isLoginNotAllowedCharactersPresent() {
		return get(LOGIN_NOT_ALLOWED_CHARACTERS_PRESENT);
	}
	
	@JsonIgnore
	public boolean isPasswordNotPresent() {
		return get(PASSWORD_ERROR);
	}
	@JsonIgnore
	public void setPasswordNotPresent(boolean state) {
		set(state, PASSWORD_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isPasswordIncorrect() {
		return get(PASSWORD_NOT_PRESENT);
	}
	@JsonIgnore
	public void setPasswordLengthOutOfRange(boolean state) {
		set(state, PASSWORD_LENGTH_OUT_OF_RANGE);
	}
	@JsonIgnore
	public boolean isPasswordLengthOutOfRange() {
		return get(PASSWORD_LENGTH_OUT_OF_RANGE);
	}
	@JsonIgnore
	public void setPasswordUppercaseLettersNotPresent(boolean state) {
		set(state, PASSWORD_UPPERCASE_LETTERS_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isPasswordUppercaseLettersNotPresent() {
		return get(PASSWORD_UPPERCASE_LETTERS_NOT_PRESENT);
	}
	@JsonIgnore
	public void setPasswordLowercaseLettersNotPresent(boolean state) {
		set(state, PASSWORD_LOWERCASE_LETTERS_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isPasswordLowercaseLettersNotPresent() {
		return get(PASSWORD_LOWERCASE_LETTERS_NOT_PRESENT);
	}
	@JsonIgnore
	public void setPasswordDigitsNotPresent(boolean state) {
		set(state, PASSWORD_DIGITS_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isPasswordDigitsNotPresent() {
		return get(PASSWORD_DIGITS_NOT_PRESENT);
	}
	@JsonIgnore
	public void setPasswordPunctuationsNotPresent(boolean state) {
		set(state, PASSWORD_PUNCTUATIONS_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isPasswordPunctuationsNotPresent() {
		return get(PASSWORD_PUNCTUATIONS_NOT_PRESENT);
	}
	
	@JsonIgnore
	public boolean isRetypePasswordError() {
		return get(RETYPE_PASSWORD_ERROR);
	}
	@JsonIgnore
	public void setRetypePasswordNotPresent(boolean state) {
		set(state, RETYPE_PASSWORD_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isRetypePasswordNotPresent() {
		return get(RETYPE_PASSWORD_NOT_PRESENT);
	}
	@JsonIgnore
	public void setRetypePasswordNotMatch(boolean state) {
		set(state, RETYPE_PASSWORD_NOT_MATCH);
	}
	@JsonIgnore
	public boolean isRetypePasswordNotMatch() {
		return get(RETYPE_PASSWORD_NOT_MATCH);
	}
	
	@JsonIgnore
	public boolean isEmailError() {
		return get(EMAIL_ERROR);
	}
	@JsonIgnore
	public void setEmailNotPresent(boolean state) {
		set(state, EMAIL_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isEmailNotPresent() {
		return get(EMAIL_NOT_PRESENT);
	}
	@JsonIgnore
	public void setEmailIncorrect(boolean state) {
		set(state, EMAIL_IS_INCORRECT);
	}
	@JsonIgnore
	public boolean isEmailIncorrect() {
		return get(EMAIL_NOT_PRESENT);
	}
	
	@JsonIgnore
	public boolean isAdderssError() {
		return get(ADDRESS_ERROR);
	}
	@JsonIgnore
	public void setAddressNotPresent(boolean state) {
		set(state, ADDRESS_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isAdderssNotPresent() {
		return get(ADDRESS_NOT_PRESENT);
	}
	@JsonIgnore
	public void setAddressIncorrect(boolean state) {
		set(state, ADDRESS_IS_INCORRECT);
	}
	@JsonIgnore
	public boolean isAdderssIncorrect() {
		return get(ADDRESS_IS_INCORRECT);
	}
	
	@JsonIgnore
	public boolean isGenderError() {
		return get(GENDER_ERROR);
	}
	@JsonIgnore
	public void setGenderNotPresent(boolean state) {
		set(state, GENDER_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isGenderNotPresent() {
		return get(GENDER_NOT_PRESENT);
	}
	@JsonIgnore
	public void setGenderIncorrect(boolean state) {
		set(state, GENDER_IS_INCORRECT);
	}
	@JsonIgnore
	public boolean isGenderIncorrect() {
		return get(GENDER_IS_INCORRECT);
	}
	
	@JsonIgnore
	public boolean isCommentError() {
		return get(COMMENT_ERROR);
	}
	@JsonIgnore
	public void setCommentNotPresent(boolean state) {
		set(state, COMMENT_NOT_PRESENT);
	}
	@JsonIgnore
	public boolean isCommentNotPresent() {
		return get(COMMENT_NOT_PRESENT);
	}
	@JsonIgnore
	public void setCommentLengthOutOfRange(boolean state) {
		set(state, COMMENT_LENGTH_OUT_OF_RANGE);
	}
	@JsonIgnore
	public boolean isCommentLengthOutOfRange() {
		return get(COMMENT_LENGTH_OUT_OF_RANGE);
	}
	
	@JsonIgnore
	public void setAmigaNotAgree(boolean state) {
		set(state, AMIGA_AGREE);
	}
	@JsonIgnore
	public boolean isAmigaNotAgree() {
		return get(AMIGA_AGREE);
	}
	
	@JsonIgnore
	public void setLoginAndOrEmailUsed(boolean state) {
		set(state, LOGIN_AND_OR_EMAIL_USED);
	}
	@JsonIgnore
	public boolean isLoginAndOrEmailUsed() {
		return get(LOGIN_AND_OR_EMAIL_USED);
	}
	
	@JsonIgnore
	private void set(boolean state, int code) {
		error = state ? error | code : error & ~code;
	}
	@JsonIgnore
	private boolean get(int code) {
		return (error & code) != 0;
	}
}
