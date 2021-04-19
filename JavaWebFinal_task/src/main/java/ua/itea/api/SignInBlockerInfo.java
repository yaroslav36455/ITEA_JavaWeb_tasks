package ua.itea.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInBlockerInfo {
	@JsonProperty(value = "millisecondsLeft")
	private int millisecondsLeft;
	@JsonProperty(value = "generatedCaptcha")
	private String generatedCaptcha;
	@JsonProperty(value = "captchaRequired")
	private boolean captchaRequired;
	
	public SignInBlockerInfo() {
		/* empty */
	}
	

	public int getMillisecondsLeft() {
		return millisecondsLeft;
	}

	public void setMillisecondsLeft(int millisecondsLeft) {
		this.millisecondsLeft = millisecondsLeft;
	}

	public String getGeneratedCaptcha() {
		return generatedCaptcha;
	}

	public void setGeneratedCaptcha(String generatedCaptcha) {
		this.generatedCaptcha = generatedCaptcha;
	}


	public boolean isCaptchaRequired() {
		return captchaRequired;
	}


	public void setCaptchaRequired(boolean captchaRequired) {
		this.captchaRequired = captchaRequired;
	}
}
