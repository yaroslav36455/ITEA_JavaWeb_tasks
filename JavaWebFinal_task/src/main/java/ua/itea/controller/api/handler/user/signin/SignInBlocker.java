package ua.itea.controller.api.handler.user.signin;

import java.util.Date;

public class SignInBlocker {
	private static final long BLOCK_TIME_MILLISECONDS = 60000;
	private static final int MAX_ATTEMPTS = 3;
	
	private long lastBlockTime;
	private int loginAttemptCounter;
	private int remainingTimeMilliseconds;
	private float remainingTimeSeconds;
	
	private Captcha generatedCaptcha;
	private boolean isCaptchaValid;
	
	public static final int getAttemptsMax() {
		return MAX_ATTEMPTS;
	}
	
	public SignInBlocker() {
		reset();
	}
	
	private void reset() {
		isCaptchaValid = true;
		loginAttemptCounter = 0;
		lastBlockTime = 0;
		generatedCaptcha = null;
	}
	
	public void captureMistake() {
		++loginAttemptCounter;
		
		if (loginAttemptCounter == getAttemptsMax()) {
			block();
		}
	}
	
	private void block() {
		CaptchaGenerator generator = new CaptchaGenerator();
		
		isCaptchaValid = false;
		generatedCaptcha = generator.generate();
		lastBlockTime = new Date().getTime();
		updateTime();
	}
	
	public void update(Captcha captcha) {
		updateCaptcha(captcha);
		updateTimePrivate();
		
		if(!isBlocked()) {
			reset();
		}
	}
	
	private void updateCaptcha(Captcha captcha) {
		isCaptchaValid |= captcha.isValid(generatedCaptcha);
	}
	
	public void updateTime() {
		updateTimePrivate();
		
		if(!isBlocked()) {
			reset();
		}
	}
	
	private void updateTimePrivate() {
		long currentTime = new Date().getTime();
		long remaining = BLOCK_TIME_MILLISECONDS - (currentTime - lastBlockTime);
		
		remainingTimeMilliseconds = (int) Math.max(remaining, 0L);
		remainingTimeSeconds = remainingTimeMilliseconds / 1000.f;
	}
	
	public boolean isCaptchaValid() {
		return isCaptchaValid;
	}
	
	public Captcha getGeneratedCaptcha() {
		return generatedCaptcha;
	}
	
	public int getRemainingTimeMilliseconds() {
		return remainingTimeMilliseconds;
	}
	
	public float getRemainingTimeSeconds() {	
		return remainingTimeSeconds;
	}

	public boolean isBlocked() {
		return remainingTimeMilliseconds != 0 || !isCaptchaValid;
	}
	
	public int getAttmptsLeft() {
		return getAttemptsMax() - loginAttemptCounter;
	}
}
