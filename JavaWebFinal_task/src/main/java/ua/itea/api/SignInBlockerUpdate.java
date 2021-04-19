package ua.itea.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInBlockerUpdate {
	@JsonProperty("captcha")
	private String captcha;
	
	public SignInBlockerUpdate() {
		/* empty */
	}

	public SignInBlockerUpdate(String captcha) {
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
