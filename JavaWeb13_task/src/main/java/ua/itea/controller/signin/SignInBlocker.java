package ua.itea.controller.signin;

import java.util.Date;

public class SignInBlocker {
	private static final long BLOCK_TIME_MILLISECONDS = 10000;
	private static final int MAX_ATTEMPTS = 3;
	
	private long lastBlockTime;
	private int loginAttemptCounter;
	private int remainingTimeMilliseconds;
	private float remainingTimeSeconds;
	
	private Captcha generatedCaptcha;
	private boolean isCaptchaValid;
	
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
		
		if (loginAttemptCounter == MAX_ATTEMPTS) {
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
		
		remainingTimeMilliseconds = Math.max((int) remaining, 0);
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
}
