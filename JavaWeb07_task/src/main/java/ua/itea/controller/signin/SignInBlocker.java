package ua.itea.controller.signin;

import java.util.Date;

import ua.itea.controller.AutoCreatableAttribute;
import ua.itea.controller.SessionAttribute;

@AutoCreatableAttribute
public class SignInBlocker implements SessionAttribute {
	private static final long BLOCK_TIME_MILLISECONDS = 10000;
	private static final int MAX_ATTEMPTS = 3;
	
	private long lastBlockTime;
	private int loginAttemptCounter;
	private int remainingTimeMilliseconds;
	private float remainingTimeSeconds; /* dummy for jstl */
	
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
		updateTime();
		
		if(!isBlocked()) {
			reset();
		}
	}
	
	private void updateCaptcha(Captcha captcha) {
		isCaptchaValid |= captcha.isValid(generatedCaptcha);
	}
	
	public void updateTime() {
		long currentTime = new Date().getTime();
		long remaining = BLOCK_TIME_MILLISECONDS - (currentTime - lastBlockTime);
		
		remainingTimeMilliseconds = Math.max((int) (remaining), 0);
		System.out.println("currentTime " + currentTime);
		System.out.println("lastBlockTime " + lastBlockTime);
		System.out.println("remaining " + remaining);
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
		return getRemainingTimeMilliseconds() / 1000.0f;
	}

	public boolean isBlocked() {
		return remainingTimeMilliseconds != 0 || !isCaptchaValid;
	}
}
